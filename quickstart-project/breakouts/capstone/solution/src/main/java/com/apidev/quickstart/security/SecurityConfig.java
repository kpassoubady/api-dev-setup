package com.apidev.quickstart.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, RateLimitFilter rateLimitFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v3/**").hasRole("UNDERWRITER")
                .requestMatchers("/api/v1/**", "/api/v2/**").hasAnyRole("MEMBER", "UNDERWRITER")
                .requestMatchers("/api-docs/**", "/api-docs.yaml", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .addFilterAfter(rateLimitFilter, AuthorizationFilter.class);
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(
        PasswordEncoder passwordEncoder,
        @Value("${capstone.member.password}") String memberPassword,
        @Value("${capstone.underwriter.password}") String underwriterPassword
    ) {
        return new InMemoryUserDetailsManager(
            User.withUsername("member").password(passwordEncoder.encode(memberPassword)).roles("MEMBER").build(),
            User.withUsername("underwriter").password(passwordEncoder.encode(underwriterPassword)).roles("UNDERWRITER").build()
        );
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
