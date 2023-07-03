package com.example.payment.services.repositories;

import com.example.payment.services.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findInventoryById(Long id);

    @Query(nativeQuery = true,
            value = "SELECT i.* " +
            "FROM inventories i " +
            "WHERE (item_name = :itemName or :itemName is null) " +
            "   AND (price >= :minPrice or :minPrice is null) " +
            "   AND (price <= :maxPrice or :maxPrice is null) " +
            "ORDER BY i.item_id asc " +
            "OFFSET :page*:pageSize " +
            "LIMIT :pageSize ;")
    List<Inventory> findAllByFilter(String itemName, Double minPrice, Double maxPrice, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
}
