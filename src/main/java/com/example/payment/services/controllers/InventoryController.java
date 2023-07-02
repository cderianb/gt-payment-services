package com.example.payment.services.controllers;

import com.example.payment.services.entities.Inventory;
import com.example.payment.services.helper.ResponseHelper;
import com.example.payment.services.models.Response;
import com.example.payment.services.models.service.inventory.CreateInventoryRequest;
import com.example.payment.services.models.service.inventory.UpdateInventoryRequest;
import com.example.payment.services.models.web.requests.PagingRequest;
import com.example.payment.services.models.web.requests.inventory.PostInventoryWebRequest;
import com.example.payment.services.services.InventoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = "/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Mono<Response<Inventory>> postInventory(@RequestBody PostInventoryWebRequest webRequest)  {
        //TODO: validation for invalid name
        //TODO: validation for invalid price <0
        CreateInventoryRequest request = CreateInventoryRequest.builder()
                .itemName(webRequest.getItemName())
                .quantity(webRequest.getQuantity())
                .price(webRequest.getPrice())
                .build();
        return inventoryService.createInventory(request)
                .map(ResponseHelper::ok);
    }

    @GetMapping(path = "{id}")
    public Mono<Response<Inventory>> getPInventoryById(@PathVariable Long id){
        //TODO: validation for existing type
        return inventoryService.getInventoryById(id)
                .map(ResponseHelper::ok);
    }

    @GetMapping
    public Response<List<Inventory>> getPaymentList(@RequestParam PagingRequest pagingRequest){
        return ResponseHelper.ok(null);
    }

    @PutMapping(path = "{id}")
    public Mono<Response<Inventory>> updateInventory(@PathVariable Long id, @RequestBody UpdateInventoryRequest webRequest){
        //TODO: validation for existing type
        //TODO: validation for existing payment
        UpdateInventoryRequest request = UpdateInventoryRequest.builder()
                .id(id)
                .itemName(webRequest.getItemName())
                .quantity(webRequest.getQuantity())
                .price(webRequest.getPrice())
                .build();
        return inventoryService.updateInventory(request)
                .map(ResponseHelper::ok);
    }

    @DeleteMapping(path = "{id}")
    public Mono<Response<Long>> deleteInventory(@PathVariable Long id){
        return Mono.just(inventoryService.deleteInventory(id))
                .thenReturn(ResponseHelper.ok(id));
    }
}
