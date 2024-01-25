package com.example.buysell.services;

import com.example.buysell.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<Product> listProduct(String title);

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    public Product getProductById(Long id);

    public void deleteProduct(Long id);
}
