package com.project.mini.peer2peer.repository;

import com.project.mini.peer2peer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductByProductId(Integer integer);
    List<Product> findAllByOrderByLenderId();
//    @Modifying(clearAutomatically = true)
//    @Query("update Product p set p.deleteStatus=1 where p.productId=:productId")
//    void deleteByProductId(Integer productId);
}
