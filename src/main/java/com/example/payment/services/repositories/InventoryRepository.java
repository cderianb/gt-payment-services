package com.example.payment.services.repositories;

import com.example.payment.services.entities.Inventory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryRepository extends ReactiveCrudRepository<Inventory, Long> {
    Mono<Inventory> findInventoryById(Long id);
    Flux<Inventory> findAllByItemNameContaining(String itemName, Pageable pageable);
}
