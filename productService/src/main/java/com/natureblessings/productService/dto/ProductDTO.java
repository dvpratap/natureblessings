package com.natureblessings.productService.dto;

import jakarta.persistence.Column;
import lombok.*;

import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is mandatory")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    private String description;

    @Positive(message = "Price must be greater than 0")
    @Column(nullable = false)
    private double price;

    @Min(value = 0, message = "Quantity in stock must be zero or more")
    private int quantityInStock;
}
