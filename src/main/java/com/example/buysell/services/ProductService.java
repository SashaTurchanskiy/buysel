package com.example.buysell.services;

import com.example.buysell.model.Product;
import com.example.buysell.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<Product> listProduct(String title);

    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    public Product getProductById(Long id);

    public void deleteProduct(Long id);

    public User getUserByPrincipal(Principal principal);
}
