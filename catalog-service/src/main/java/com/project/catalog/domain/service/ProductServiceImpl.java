package com.project.catalog.domain.service;

import com.project.catalog.domain.config.CatalogProperties;
import com.project.catalog.domain.dto.PagedResult;
import com.project.catalog.domain.dto.Product;
import com.project.catalog.domain.repo.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CatalogProperties catalogProperties;

    ProductServiceImpl(ProductRepository productRepository, CatalogProperties catalogProperties) {
        this.productRepository = productRepository;
        this.catalogProperties = catalogProperties;
    }

    @Override
    public PagedResult<Product> getProducts(int pageNumber) {
        Sort sort = Sort.by("name").ascending();
        pageNumber = pageNumber <= 1 ? 0 : pageNumber - 1;
        Pageable pageable = PageRequest.of(pageNumber,catalogProperties.pageSize(),sort);
        Page<Product> productsPage  = productRepository.findAll(pageable).map(val ->
                new Product(val.getCode(), val.getName(), val.getDescription(), val.getImageUrl(), val.getPrice()));

        return new PagedResult<>(productsPage.getContent(),productsPage.getTotalElements(),productsPage.getNumber()+1,productsPage.getTotalPages(),
                productsPage.isFirst(),productsPage.isLast(),productsPage.hasNext(),productsPage.hasPrevious());
    }

    @Override
    public Optional<Product> getProductsByCode(String code) {
        return productRepository.findByCode(code).map(val -> new Product(val.getCode(), val.getName(), val.getDescription(), val.getImageUrl(), val.getPrice()));
    }


}
