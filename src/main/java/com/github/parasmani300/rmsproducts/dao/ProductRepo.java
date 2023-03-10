package com.github.parasmani300.rmsproducts.dao;

import com.github.parasmani300.rmsproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
