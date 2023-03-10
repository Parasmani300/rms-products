package com.github.parasmani300.rmsproducts.controller;

import com.github.parasmani300.rmsproducts.dto.ErrorMessage;
import com.github.parasmani300.rmsproducts.dto.Updater;
import com.github.parasmani300.rmsproducts.model.Product;
import com.github.parasmani300.rmsproducts.services.inrfc.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductServiceInterface productService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> findAllProduct(
            @RequestParam(value = "pageNo",defaultValue = "1",required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "1",required = false) Integer pageSize
    )
    {
        List<Product> allPosts = productService.viewProductByPage(pageNo,pageSize);
        return new ResponseEntity<List<Product>>(allPosts,HttpStatus.OK);
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Integer productId)
    {
        Product product = productService.viewProduct(productId);
        if(product == null)
        {
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage("No such product found");
            return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.OK);
        }
        return  new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        Product product1 = productService.addProduct(product);
        return new ResponseEntity<Product>(product1,HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Updater[] updaterArray)
    {
        Product myProduct = null;
        for(int i = 0;i<updaterArray.length;i++)
        {
            System.out.println(updaterArray[i].getKey());
            myProduct = productService.updateProduct(updaterArray[i].getKey(),updaterArray[i].getValue(),productId);
        }
        return new ResponseEntity<Product>(myProduct,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId)
    {
        Product product = productService.deleteProduct(productId);
        if(product == null){
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage("No such product,cannot delete");
            return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.OK);
        }
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }
}
