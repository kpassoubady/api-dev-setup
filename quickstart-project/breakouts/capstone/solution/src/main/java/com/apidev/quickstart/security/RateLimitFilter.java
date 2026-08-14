package com.apidev.quickstart.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private static final int LIMIT = 3;
    private static final long WINDOW_SECONDS = 60;
    private final ConcurrentHashMap<String, Window> fWindows = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
            || authentication instanceof AnonymousAuthenticationToken
            || !request.getRequestURI().startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String key = authentication.getName() + ":" + request.getRequestURI();
        long now = Instant.now().getEpochSecond();
        Window window = fWindows.compute(key, (ignored, current) -> {
            if (current == null || now - current.startedAt() >= WINDOW_SECONDS) return new Window(now, 1);
            return new Window(current.startedAt(), current.count() + 1);
        });

        response.setHeader("RateLimit-Limit", String.valueOf(LIMIT));
        response.setHeader("RateLimit-Remaining", String.valueOf(Math.max(0, LIMIT - window.count())));
        if (window.count() > LIMIT) {
            response.setStatus(429);
            response.setHeader("Retry-After", String.valueOf(WINDOW_SECONDS));
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":429,\"error\":\"rate_limit_exceeded\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    public void reset() {
        fWindows.clear();
    }

    private record Window(long startedAt, int count) {
    }
}
