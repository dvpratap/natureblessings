package com.natureblessings.productService.service;

import com.natureblessings.productService.dto.ProductDTO;
import com.natureblessings.productService.entity.Product;

import java.util.List;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    void deleteProduct(Long id);
}
