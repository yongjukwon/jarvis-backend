package com.backend.jarvis.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Inventory {

    @Id
    @GeneratedValue
    @Column(name = "inventory_id")
    private Long Id;

    @NotNull
    private String ingredientName;
    private int stockQuantity;

    protected Inventory() {
    }

    public Inventory(@NotNull String ingredientName, int stockQuantity) {
        this.ingredientName = ingredientName;
        this.stockQuantity = stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
