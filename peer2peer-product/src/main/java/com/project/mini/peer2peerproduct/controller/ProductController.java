package com.project.mini.peer2peerproduct.controller;

import com.project.mini.peer2peerproduct.model.Product;
import com.project.mini.peer2peerproduct.request.ProductRequest;
import com.project.mini.peer2peerproduct.response.MessageResponse;
import com.project.mini.peer2peerproduct.service.ProductService;
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
        System.out.println("Request productId: "+productId);
        Map<String, Object> res = new HashMap<>();
        Product aa = productService.findTheProduct(productId);
//        System.out.println("data: " + aa.getProductId() + aa.getAmount() + " "+ aa.getInterest() + " "+aa.getDeleteStatus());
        res.put("message", "success find product data");
        res.put("data", productService.findTheProduct(productId));
        System.out.println(new ResponseEntity(res, HttpStatus.OK));
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
