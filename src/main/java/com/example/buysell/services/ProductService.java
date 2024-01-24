package com.example.buysell.services;

import com.example.buysell.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<Product> listProduct(String title);

    public void saveProduct(Product product);

    public Product getProductById(Long id);

    public void deleteProduct(Long id);
}
