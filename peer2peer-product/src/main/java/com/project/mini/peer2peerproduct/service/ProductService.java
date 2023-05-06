package com.project.mini.peer2peerproduct.service;

import com.project.mini.peer2peerproduct.model.Product;
import com.project.mini.peer2peerproduct.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void insert(Product product) { productRepository.save(product); }

    public void update(Product product) {
        productRepository.save(product);
    }

    public Product findTheProduct(Integer integer) {
        return productRepository.findProductByProductId(integer);
    }

    public List<Product> allProducts() {
        return productRepository.findAllByOrderByLenderId();
    }
}
