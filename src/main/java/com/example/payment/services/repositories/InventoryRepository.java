package com.example.payment.services.repositories;

import com.example.payment.services.entities.Inventory;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Long> {
    Mono<Inventory> findInventoryById(Long id);

    @Query(value = "SELECT i.* " +
            "FROM inventories i " +
            "WHERE (item_name = :itemName or :itemName is null) " +
            "   AND (price >= :minPrice or :minPrice is null) " +
            "   AND (price <= :maxPrice or :maxPrice is null) " +
            "ORDER BY i.item_id asc " +
            "OFFSET :page*:pageSize " +
            "LIMIT :pageSize;")
    Flux<Inventory> findAllByFilter(String itemName, Double minPrice, Double maxPrice, Integer page, Integer pageSize);
}
