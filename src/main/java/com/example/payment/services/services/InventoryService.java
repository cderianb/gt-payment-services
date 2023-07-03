package com.example.payment.services.services;

import com.example.payment.services.entities.Inventory;
import com.example.payment.services.models.service.inventory.CreateInventoryRequest;
import com.example.payment.services.models.service.inventory.GetListInventoryRequest;
import com.example.payment.services.models.service.inventory.UpdateInventoryRequest;
import com.example.payment.services.repositories.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public Mono<Inventory> createInventory(CreateInventoryRequest request){
        return Mono.just(Inventory.builder()
                            .itemName(request.getItemName())
                            .quantity(request.getQuantity())
                            .price(request.getPrice())
                            .build())
                    .map(inventoryRepository::save);
    }

    public Mono<Inventory> getInventoryById(Long id){
        return Mono.just(id)
                .map(inventoryRepository::findInventoryById);
    }

    public Mono<Inventory> updateInventory(UpdateInventoryRequest request){
        return Mono.just(request.getId())
                .map(inventoryRepository::findInventoryById)
                .map(inventory -> updateValue(inventory, request));
    }

    public Mono<Void> deleteInventory(Long id){
        inventoryRepository.deleteById(id);
        return Mono.empty();
    }

    private Inventory updateValue(Inventory inventory, UpdateInventoryRequest request){
        inventory.setItemName(request.getItemName());
        inventory.setQuantity(request.getQuantity());
        inventory.setPrice(request.getPrice());
        return inventoryRepository.save(inventory);
    }

    public Flux<Inventory> getInventoryList(GetListInventoryRequest request){
        return Mono.just(request)
                .publishOn(Schedulers.boundedElastic())
                .map(req -> inventoryRepository.findAllByFilter(req.getItemName(), req.getMinPrice(), req.getMaxPrice()
                , req.getPage(), req.getPageSize()))
                .doOnEach(a -> System.out.println("processing"))
                .flatMapMany(Flux::fromIterable);
    }
}
