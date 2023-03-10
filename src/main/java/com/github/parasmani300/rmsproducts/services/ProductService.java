package com.github.parasmani300.rmsproducts.services;

import com.github.parasmani300.rmsproducts.dao.ProductRepo;
import com.github.parasmani300.rmsproducts.model.Product;
import com.github.parasmani300.rmsproducts.services.inrfc.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductRepo productRepo;

    @Override
    public Product addProduct(Product product) {
        Product saveProdcut = productRepo.save(product);
        return saveProdcut;
    }

    @Override
    public Product updateProduct(String columnName, String columnValue,Integer id) {
        Optional<Product> product = productRepo.findById(id);
        if(!product.isPresent())
        {
            System.out.println("Product not present");
            return null;
        }
        Product myProduct = product.get();
        if(columnName.equals("color")){
            myProduct.setColor(columnValue);
        }else if(columnName.equals("size")){
            myProduct.setSize(Integer.parseInt(columnValue));
        }else if(columnName.equals("costPrice")){
            myProduct.setCostPrice(Double.parseDouble(columnValue));
        }else if(columnName.equals("upc")){
            myProduct.setUpc(columnValue);
        }else if(columnName.equals("countryOfOrigin")){
            myProduct.setCountryOfOrigin(columnValue);
        }else if(columnName.equals("name")){
            myProduct.setName(columnValue);
        }else if(columnName.equals("shortDesc")){
            myProduct.setShortDesc(columnValue);
        }else if(columnName.equals("longDesc")){
            myProduct.setLongDesc(columnValue);
        }

        productRepo.save(myProduct);
        return myProduct;
    }

    @Override
    public Product deleteProduct(Integer productId) {
        Optional<Product> product = productRepo.findById(productId);
        if(!product.isPresent())
        {
            System.out.println("Product not present");
            return null;
        }
        productRepo.deleteById(productId);
        return product.get();
    }

    @Override
    public Product viewProduct(Integer productId) {
        Optional<Product> product = productRepo.findById(productId);
        if(!product.isPresent()){
            System.out.println("Product not present");
            return null;
        }
        return product.get();
    }

    @Override
    public List<Product> viewProductByPage(Integer pageNo, Integer pageSize) {
        Pageable p = PageRequest.of(pageNo,pageSize);
        Page<Product> pagePost = productRepo.findAll(p);
        List<Product> allPosts = pagePost.getContent();
        return allPosts;
    }
}
