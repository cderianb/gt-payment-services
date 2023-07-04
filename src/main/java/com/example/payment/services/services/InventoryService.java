package com.example.payment.services.services;

import com.example.payment.services.entities.Inventory;
import com.example.payment.services.models.service.inventory.CreateInventoryRequest;
import com.example.payment.services.models.service.inventory.GetListInventoryRequest;
import com.example.payment.services.models.service.inventory.UpdateInventoryRequest;
import com.example.payment.services.repositories.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory createInventory(CreateInventoryRequest request){
        Inventory inventory = Inventory.builder()
                .itemName(request.getItemName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
        return inventoryRepository.save(inventory);
    }

    public Inventory getInventoryById(Long id){
        return inventoryRepository.findInventoryById(id);
    }

    public Inventory updateInventory(UpdateInventoryRequest request){
        Inventory inventory = inventoryRepository.findInventoryById(request.getId());
        return updateValue(inventory, request);
    }

    public void deleteInventory(Long id){
        inventoryRepository.deleteById(id);
    }

    private Inventory updateValue(Inventory inventory, UpdateInventoryRequest request){
        inventory.setItemName(request.getItemName());
        inventory.setQuantity(request.getQuantity());
        inventory.setPrice(request.getPrice());
        return inventoryRepository.save(inventory);
    }

    public Page<Inventory> getInventoryList(GetListInventoryRequest request){
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageSize(), Sort.by("item_id").ascending());
        return inventoryRepository.findAllByFilter(request.getItemName(), request.getMinPrice(), request.getMaxPrice()
                , pageable);
    }
}
