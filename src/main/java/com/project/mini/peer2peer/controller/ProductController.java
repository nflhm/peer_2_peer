package com.project.mini.peer2peer.controller;

import com.project.mini.peer2peer.model.Product;
import com.project.mini.peer2peer.request.ProductRequest;
import com.project.mini.peer2peer.response.MessageResponse;
import com.project.mini.peer2peer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<List<Product>>> selectAllProduct() {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success get all product data");
        res.put("data", productService.allProducts());
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @GetMapping("/{product_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<Product>> selectUniqueProduct(@PathVariable("product_id") int productId) {
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success find product data");
        res.put("data", productService.findTheProduct(productId));
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse> addProduct (@RequestBody ProductRequest productRequest) {
        Product product = new Product(productRequest);
        productService.insert(product);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success add product data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MessageResponse<Product>> updateProduct (
            @RequestBody Product product
    ) {
        productService.update(product);
        Map<String, Object> res = new HashMap<>();
        res.put("message", "success update product data");
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
