package com.product.log.service;

import com.product.log.dto.ProductRequest;
import com.product.log.model.Product;
import jakarta.el.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {

    private final HashMap<String,Product> productList;

    public ProductService() {
        this.productList = new HashMap<>();
    }

    public Product createProduct(ProductRequest productRequest) {

        Product product = new Product(productRequest);
        productList.put(product.getProductId(), product);

        return product;
    }

    public Product getProduct(String id) {
        try{
            return productList.get(id.trim());
        } catch ( Exception e){
            throw new PropertyNotFoundException("data not found");
        }
    }

    public List<Product> getAllProduct() {

       List<Product> allProducts = new ArrayList<>();
       for(String productKey : productList.keySet()){
            Product product = productList.get(productKey);

            allProducts.add(product);
       }

       return allProducts;
    }

    public void updateProduct(Product productReq) {
        try{
            Product product = productList.get(productReq.getProductId());

            if(product == null){
                throw new Exception("product not found");
            }

            if(productReq.getName()!=null && !product.getName().contentEquals(productReq.getName())){
                product.setName(productReq.getName());
            }

            if(productReq.getDescription()!=null && !product.getDescription().contentEquals(productReq.getDescription())){
                product.setDescription(productReq.getDescription());
            }

            if(productReq.getPrice()!=null && product.getPrice().doubleValue() != productReq.getPrice().doubleValue()){
                product.setPrice(productReq.getPrice());
            }

            if(productReq.getQuantityAvailable()!=null && product.getQuantityAvailable().intValue() != productReq.getQuantityAvailable().intValue()){
                product.setQuantityAvailable(productReq.getQuantityAvailable());
            }

            productList.replace(product.getProductId(), product);

        } catch (Exception e){
            throw new PropertyNotFoundException("data not found");
        }
    }

    public void deleteProduct(String id) {

        try{
            Product product = productList.get(id);

            if(product == null){
                throw new PropertyNotFoundException("Product not found");
            }
            productList.remove(id);

        } catch (Exception e){
            throw new PropertyNotFoundException("data not  found");
        }
    }
}
