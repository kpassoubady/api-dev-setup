package com.apidev.quickstart.controller;

import com.apidev.quickstart.model.InventoryItem;
import com.apidev.quickstart.service.InventoryService;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryItem> findAll() {
        // TODO: Return all items supplied by InventoryService.
        return List.of();
    }

    @GetMapping("/{sku}")
    public ResponseEntity<InventoryItem> findBySku(@PathVariable String sku) {
        // TODO: Return the item when found, or HTTP 404 when the SKU is absent.
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InventoryItem> create(@RequestBody InventoryItem item) {
        // TODO: Return HTTP 201 for a new item and HTTP 409 for a duplicate SKU.
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
