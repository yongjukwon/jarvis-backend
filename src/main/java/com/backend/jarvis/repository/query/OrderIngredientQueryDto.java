package com.backend.jarvis.repository.query;

import com.backend.jarvis.domain.Ingredient.Ingredient;
import com.backend.jarvis.domain.OrderIngredient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderIngredientQueryDto {

    @JsonIgnore
    private Long orderIngredientId;
    private Ingredient ingredient;
    private Long totalPriceInCent;
    private int count;

    public OrderIngredientQueryDto(OrderIngredient orderIngredient) {
        this.orderIngredientId = orderIngredient.getId();
        this.ingredient = orderIngredient.getIngredient();
        this.totalPriceInCent = orderIngredient.getTotalPriceInCent();
        this.count = orderIngredient.getQuantity();
    }
}
