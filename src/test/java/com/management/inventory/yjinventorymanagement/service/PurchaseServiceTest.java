package com.management.inventory.yjinventorymanagement.service;

import com.management.inventory.yjinventorymanagement.constant.ItemCatalog;
import com.management.inventory.yjinventorymanagement.domain.Menu;
import com.management.inventory.yjinventorymanagement.domain.Person;
import com.management.inventory.yjinventorymanagement.exception.NotEnoughStockException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class PurchaseServiceTest {

    @Autowired
    InventoryService inventoryService;
    @Autowired
    PersonService personService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    MenuService menuService;

    private Person customer;

    @BeforeAll
    void addMenu() {
        customer = new Person("Yongju", "Kwon", "yongjuKwon@gmail.com");
        personService.register(customer);

        ItemCatalog[] itemCatalogs = ItemCatalog.values();
        for (ItemCatalog ic : itemCatalogs)
            menuService.add(new Menu(ic.getFormattedName(), ic.getDescription(), ic.getPriceInCent()));
    }

    @Test
    void purchaseBeforeInitializeIngredient() {
        assertThrows
                (NullPointerException.class,
                        () -> purchaseService.purchase(customer, "hamburger", "cheese burger", "big mac"));
    }

    @Test
    void returnNotEnoughStockExceptionWhenStockIsOut() {
        inventoryService.addStock("bun", 2);
        inventoryService.addStock("cheese", 50);
        inventoryService.addStock("lettuce", 30);
        inventoryService.addStock("patty", 30);

        // big mac -> "bun", "lettuce", "cheese", "patty", "bun", "lettuce", "cheese", "patty", "bun"
        assertThrows(
                NotEnoughStockException.class,
                () -> purchaseService.purchase(customer, "big mac")
        );
    }
}