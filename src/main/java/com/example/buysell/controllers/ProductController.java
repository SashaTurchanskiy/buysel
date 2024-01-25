package com.example.buysell.controllers;

import com.example.buysell.model.Product;
import com.example.buysell.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false)String title, Model model) {
        model.addAttribute("products", productService.listProduct(title));
        return "products";
    }
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id,  Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product-info";

    }

    @PostMapping("/product/create")
    public String createProduct(Product product, @RequestParam("file1")MultipartFile file1,
                                @RequestParam("file2")MultipartFile file2,
                                @RequestParam("file3")MultipartFile file3)throws IOException {
        productService.saveProduct(product, file1, file2, file3);
        return "redirect:/";
    }
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/";
    }
    }

