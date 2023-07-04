package com.example.payment.services.repositories;

import com.example.payment.services.entities.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Inventory findInventoryById(Long id);

    @Query(nativeQuery = true,
            countQuery = "SELECT COUNT(i.*) " +
                    "FROM inventories i " +
                    "WHERE (item_name = :itemName or :itemName is null) " +
                    "   AND (price >= :minPrice or :minPrice is null) " +
                    "   AND (price <= :maxPrice or :maxPrice is null) ",
            value = "SELECT i.* " +
                    "FROM inventories i " +
                    "WHERE (i.item_name = :itemName or :itemName is null) " +
                    "   AND (i.price >= :minPrice or :minPrice is null) " +
                    "   AND (i.price <= :maxPrice or :maxPrice is null) ")
    Page<Inventory> findAllByFilter(String itemName, Double minPrice, Double maxPrice, Pageable pageable);
}
