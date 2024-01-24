package com.example.buysell.services.impl;

import com.example.buysell.exception.ProductDoesNotExistException;
import com.example.buysell.model.Product;
import com.example.buysell.repos.ProductRepo;
import com.example.buysell.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public List<Product> listProduct(String title) {
        if (title != null) return productRepo.findByTitle(title);
        return productRepo.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        log.info("Saving new {}", product);
        productRepo.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductDoesNotExistException();
        }
        return optionalProduct.get();
    }

    @Override
    public void deleteProduct(Long id) {
         productRepo.deleteById(id);
    }
}
