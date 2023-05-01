package com.project.mini.peer2peer.controller;

import com.project.mini.peer2peer.model.Product;
import com.project.mini.peer2peer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public @ResponseBody List<Product> selectAllProduct() {
        return productService.allProducts();
    }
    @GetMapping("/id")
    public @ResponseBody Product selectUniqueProduct(@RequestParam("id") int id) {
        return productService.findTheProduct(id);
    }
    @GetMapping("/add")
    public String addProduct(@RequestParam("product_id") int productId,
                             @RequestParam("user_id_lender") int lenderId,
                             @RequestParam("interest") Float interest,
                             @RequestParam("due_time") int dueTime) {
        Product model = new Product(productId, lenderId, interest, dueTime, false);
        productService.insert(model);
        return "redirect:/product/";
    }
    @GetMapping("/update")
    public String updateProduct(@RequestParam("product_id") int productId,
                                @RequestParam("user_id_lender") int lenderId,
                                @RequestParam("interest") Float interest,
                                @RequestParam("due_time") int dueTime,
                                @RequestParam("delete_status") Boolean deleteStatus) {
        Product model = new Product(productId, lenderId, interest, dueTime, deleteStatus);
        productService.update(model);
        return "redirect:/product/";
    }
}
