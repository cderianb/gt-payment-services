package com.example.payment.services.controllers;

import com.example.payment.services.entities.Inventory;
import com.example.payment.services.helper.ResponseHelper;
import com.example.payment.services.models.Response;
import com.example.payment.services.models.service.inventory.CreateInventoryRequest;
import com.example.payment.services.models.service.inventory.GetListInventoryRequest;
import com.example.payment.services.models.service.inventory.UpdateInventoryRequest;
import com.example.payment.services.models.web.requests.inventory.PostInventoryWebRequest;
import com.example.payment.services.services.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Response<Inventory> postInventory(@RequestBody PostInventoryWebRequest webRequest)  {
        //TODO: validation for invalid name
        //TODO: validation for invalid price <0
        CreateInventoryRequest request = CreateInventoryRequest.builder()
                .itemName(webRequest.getItemName())
                .quantity(webRequest.getQuantity())
                .price(webRequest.getPrice())
                .build();
        Inventory inventory = inventoryService.createInventory(request);
        return ResponseHelper.ok(inventory);
    }

    @GetMapping(path = "{id}")
    public Response<Inventory> getPInventoryById(@PathVariable Long id){
        //TODO: validation for existing type
        Inventory inventory = inventoryService.getInventoryById(id);
        return ResponseHelper.ok(inventory);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Inventory> getPaymentList(@RequestParam(value = "page", defaultValue = "1") Integer page
                                            , @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
                                            , @RequestParam(value = "itemName", required = false) String itemName
                                            , @RequestParam(value = "minPrice", required = false) Double minPrice
                                            , @RequestParam(value = "maxPrice", required = false) Double maxPrice){
        GetListInventoryRequest request = GetListInventoryRequest.builder()
                .itemName(itemName)
                .minPrice(minPrice)
                .maxPrice(maxPrice)
                .page(page-1)
                .pageSize(pageSize)
                .build();
        return inventoryService.getInventoryList(request);
    }

    @PutMapping(path = "{id}")
    public Response<Inventory> updateInventory(@PathVariable Long id, @RequestBody UpdateInventoryRequest webRequest){
        //TODO: validation for existing type
        //TODO: validation for existing payment
        UpdateInventoryRequest request = UpdateInventoryRequest.builder()
                .id(id)
                .itemName(webRequest.getItemName())
                .quantity(webRequest.getQuantity())
                .price(webRequest.getPrice())
                .build();
        Inventory inventory = inventoryService.updateInventory(request);
        return ResponseHelper.ok(inventory);
    }

    @DeleteMapping(path = "{id}")
    public Response<Long> deleteInventory(@PathVariable Long id){
        inventoryService.deleteInventory(id);
        return ResponseHelper.ok(id);
    }
}
