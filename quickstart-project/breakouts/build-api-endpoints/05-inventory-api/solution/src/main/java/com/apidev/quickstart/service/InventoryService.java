package com.apidev.quickstart.service;

import com.apidev.quickstart.model.InventoryItem;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final Map<String, InventoryItem> items = new LinkedHashMap<>();

    public InventoryService() {
        items.put("BOOK-1", new InventoryItem("BOOK-1", "API Fundamentals", 8));
        items.put("MUG-1", new InventoryItem("MUG-1", "Developer Mug", 12));
    }

    public synchronized List<InventoryItem> findAll() {
        return List.copyOf(items.values());
    }

    public synchronized Optional<InventoryItem> findBySku(String sku) {
        return Optional.ofNullable(items.get(sku));
    }

    public synchronized boolean create(InventoryItem item) {
        if (items.containsKey(item.sku())) {
            return false;
        }
        items.put(item.sku(), item);
        return true;
    }
}
