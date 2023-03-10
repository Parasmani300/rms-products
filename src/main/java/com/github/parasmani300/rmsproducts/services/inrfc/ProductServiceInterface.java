package com.github.parasmani300.rmsproducts.services.inrfc;

import com.github.parasmani300.rmsproducts.model.Product;

import java.util.List;

public interface ProductServiceInterface {
//    Single
    public Product addProduct(Product product);
    public Product updateProduct(String columnName,String columnValue,Integer id);
    public Product deleteProduct(Integer productId);
    public Product viewProduct(Integer productId);
    public List<Product> viewProductByPage(Integer pageNo,Integer pageSize);
}
