package com.project.mini.peer2peerproduct.repository;

import com.project.mini.peer2peerproduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByProductId(Integer integer);
    List<Product> findAllByOrderByLenderId();
}
