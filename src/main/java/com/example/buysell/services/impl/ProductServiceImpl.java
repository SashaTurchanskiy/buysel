package com.example.buysell.services.impl;

import com.example.buysell.exception.ProductDoesNotExistException;
import com.example.buysell.model.Image;
import com.example.buysell.model.Product;
import com.example.buysell.model.User;
import com.example.buysell.repos.ProductRepo;
import com.example.buysell.repos.UserRepo;
import com.example.buysell.services.ProductService;
import com.example.buysell.utils.ImageConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    private final ImageConverter imageConverter;

    @Override
    public List<Product> listProduct(String title) {
        if (title != null) return productRepo.findByTitle(title);
        return productRepo.findAll();
    }

    @Override
    public void saveProduct(Principal principal, Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = imageConverter.toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = imageConverter.toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = imageConverter.toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDB = productRepo.save(product);
        productFromDB.setPreviewImageId(productFromDB.getImages().get(0).getId());
        productRepo.save(product);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null)
            return new User();
        return userRepo.findByEmail(principal.getName());
    }

    @Override
    public List<Product> listProducts(String title) {
        if (title != null) return productRepo.findByTitle(title);
        return productRepo.findAll();
    }



    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(User user, Long id) {
        Product product = productRepo.findById(id)
                .orElse(null);
        if (product != null) {
            if (product.getUser().getId().equals(user.getId())) {
                productRepo.delete(product);
                log.info("Product with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this product with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Product with id = {} is not found", id);
        }
    }
}
