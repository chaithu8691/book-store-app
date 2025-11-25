package com.project.catalog.domain.service;

import com.project.catalog.domain.dto.PagedResult;
import com.project.catalog.domain.dto.Product;

import java.util.Optional;

public interface ProductService {

    PagedResult<Product> getProducts(int pageNumber);
    Optional<Product> getProductsByCode(String code);
}
