package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.entity.Product;
import com.nexus.nexus_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findByIdOrThrow(id);
    }

    @GetMapping("/name/{name}")
    public Product finProductByName(@PathVariable String name){
        return productService.findByproductName(name);
    }

    @GetMapping("/brand/{brand}")
    public Product findProductByBrand(@PathVariable String brand){
        return productService.findByproductBrand(brand);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {

        Product existingProduct = productService.findByIdOrThrow(id);

        existingProduct.setProductBrand(product.getProductBrand());
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription(product.getProductDescription());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setStockQuantity(product.getStockQuantity());

        return productService.save(existingProduct);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteByid(id);
    }
}
