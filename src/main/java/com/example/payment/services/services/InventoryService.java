package com.example.payment.services.services;

import com.example.payment.services.entities.Inventory;
import com.example.payment.services.models.service.inventory.CreateInventoryRequest;
import com.example.payment.services.models.service.inventory.GetListInventoryRequest;
import com.example.payment.services.models.service.inventory.UpdateInventoryRequest;
import com.example.payment.services.repositories.InventoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public Mono<Inventory> createInventory(CreateInventoryRequest request){
        Inventory inventory = Inventory.builder()
                .itemName(request.getItemName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
        return inventoryRepository.save(inventory);
    }

    public Mono<Inventory> getInventoryById(Long id){
        return inventoryRepository.findInventoryById(id);
    }

    public Mono<Inventory> updateInventory(UpdateInventoryRequest request){
        return inventoryRepository.findInventoryById(request.getId())
                .map(inventory -> updateValue(inventory, request))
                .flatMap(inventoryRepository::save);
    }

    public Mono<Void> deleteInventory(Long id){
        return inventoryRepository.deleteById(id);
    }

    private Inventory updateValue(Inventory inventory, UpdateInventoryRequest request){
        inventory.setItemName(request.getItemName());
        inventory.setQuantity(request.getQuantity());
        inventory.setPrice(request.getPrice());

        return inventory;
    }

    public Flux<Inventory> getInventoryList(GetListInventoryRequest request){
        return inventoryRepository.findAllByItemNameContaining(request.getItemName(), PageRequest.of(request.getPage(), request.getPageSize()));
    }
}
