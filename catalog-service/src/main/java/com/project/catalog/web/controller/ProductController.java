package com.project.catalog.web.controller;

import com.project.catalog.domain.dto.PagedResult;
import com.project.catalog.domain.dto.Product;
import com.project.catalog.domain.exception.ProductNotFoundException;
import com.project.catalog.domain.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<Product> getProducts (@RequestParam(name="page", defaultValue = "1") int pageNumber){
        return productService.getProducts(pageNumber);

    }
    @GetMapping("/{code}")
    ResponseEntity<Product> getProductsByCode(@PathVariable String code) {
        return productService.getProductsByCode(code).map(ResponseEntity::ok).orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}


