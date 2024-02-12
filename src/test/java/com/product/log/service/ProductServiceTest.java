package com.product.log.service;

import com.product.log.dto.ProductRequest;
import com.product.log.model.Product;
import jakarta.el.PropertyNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private static ProductService productService  = new ProductService();

    ProductRequest productRequestBuilder(){

        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("test");
        productRequest.setDescription("testing description");
        productRequest.setPrice(123.00);
        productRequest.setQuantityAvailable(10);

        return productRequest;
    }

    @BeforeAll
    static void addedProduct(){

        ProductRequest productRequest = new ProductRequest();
        productRequest.setQuantityAvailable(10);
        productRequest.setName("test 1");
        productRequest.setDescription("test description 1");
        productRequest.setPrice(10.00);

        productService.createProduct(productRequest);
    }

    @Test
    @DisplayName("junit test for createProduct")
    void createProduct(){
        Product product = productService.createProduct(productRequestBuilder());

        assertThat(product.getName()).isEqualTo(productRequestBuilder().getName());
        assertThat(product.getDescription()).isEqualTo(productRequestBuilder().getDescription());
        assertThat(product.getPrice().doubleValue()).isEqualTo(productRequestBuilder().getPrice().doubleValue());
        assertThat(product.getQuantityAvailable().intValue()).isEqualTo(productRequestBuilder().getQuantityAvailable().intValue());
    }

    @Test
    @DisplayName("junit test for getProduct")
    void getProduct(){
        List<Product> productList = productService.getAllProduct();
        Product product = productService.getProduct(productList.get(0).getProductId());

        assertThat(product.getProductId()).isEqualTo(productList.get(0).getProductId());
    }

    @Test
    @DisplayName("junit test for getAllProduct")
    void getAllProduct(){
        List<Product> productList = productService.getAllProduct();

        assertThat(productList.size()).isOne();
    }

    @Test
    @DisplayName("junit test for updateProduct throws exception")
    void updateProduct_throwsException(){
        String message = "";
        try{
            Product product = new Product();
            product.setProductId("test");

            productService.updateProduct(product);
        } catch (PropertyNotFoundException e){
            message = e.getMessage();
        }

        assertThat(message).isEqualToIgnoringCase("data not found");
    }

    @Test
    @DisplayName("junit test for updateProduct")
    void updateProduct(){
        Product product = productService.getAllProduct().get(0);
        product.setPrice(456.00);

        productService.updateProduct(product);

        Product productVal = productService.getProduct(product.getProductId());

        assertThat(productVal.getPrice().doubleValue()).isEqualTo(product.getPrice().doubleValue());
    }
}
