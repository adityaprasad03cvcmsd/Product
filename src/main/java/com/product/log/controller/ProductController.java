package com.product.log.controller;

import com.product.log.dto.CustomResponse;
import com.product.log.dto.DiscountOrTax;
import com.product.log.dto.ProductRequest;
import com.product.log.model.Product;
import com.product.log.service.DiscountOrTaxService;
import com.product.log.service.ProductService;
import jakarta.el.PropertyNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscountOrTaxService discountOrTaxService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        CustomResponse customResponse = new CustomResponse();
        try{
            Product product = productService.createProduct(productRequest);

            return ResponseEntity.status(HttpStatus.CREATED.value()).body(product);
         } catch(Exception e){

            customResponse.setMessage("Something went wrong, please try later");
            return ResponseEntity.internalServerError().body(customResponse);
         }
    }


    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        CustomResponse customResponse = new CustomResponse();

        try{
            List<Product> allProducts = productService.getAllProduct();

            return ResponseEntity.ok().body(allProducts);
        } catch (Exception e){

            customResponse.setMessage("Something went wrong, please try after sometime");
            return ResponseEntity.internalServerError().body(customResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String id){
        CustomResponse customResponse = new CustomResponse();
        try{
            Product product  = productService.getProduct(id);
            if(product == null){
                throw new PropertyNotFoundException("data not found");
            }

            return ResponseEntity.ok().body(product);
        } catch(PropertyNotFoundException e){
            customResponse.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(customResponse);
        } catch(Exception e){
            customResponse.setMessage("Something went wrong, please try after sometime");

            return ResponseEntity.internalServerError().body(customResponse);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product productReq){
        CustomResponse customResponse = new CustomResponse();
         try{
             productService.updateProduct(productReq);

             customResponse.setSuccess(true);
             customResponse.setMessage("Product updated");

             return ResponseEntity.ok(customResponse);
         } catch(PropertyNotFoundException e){

             customResponse.setSuccess(false);
             customResponse.setMessage("Product not found");

             return ResponseEntity.badRequest().body(customResponse);
        }
         catch (Exception e){

             customResponse.setSuccess(false);
             customResponse.setMessage("Something went wrong, please try after sometime");

             return ResponseEntity.internalServerError().body(customResponse);
         }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id){
        CustomResponse customResponse = new CustomResponse();
        try{
            productService.deleteProduct(id.trim());

            customResponse.setSuccess(true);
            customResponse.setMessage("Product deleted");

            return ResponseEntity.ok(customResponse);
        } catch(PropertyNotFoundException e){

            customResponse.setSuccess(false);
            customResponse.setMessage("Product not found");

            return ResponseEntity.badRequest().body(customResponse);
        }
        catch (Exception e){

            customResponse.setSuccess(false);
            customResponse.setMessage("Something went wrong, please try after sometime");

            return ResponseEntity.internalServerError().body(customResponse);
        }
    }

    @PatchMapping("/discount-tax")
    public ResponseEntity<?> applyDiscountOrTax(@RequestBody DiscountOrTax discountOrTax) {
        CustomResponse customResponse = new CustomResponse();
        try{
            Product product = discountOrTaxService.applyDiscountOrTaxOnProduct(discountOrTax);

            customResponse.setSuccess(true);
            customResponse.setMessage("discount or tax applied");
            customResponse.setData(product);

            return ResponseEntity.ok(customResponse);
        } catch(PropertyNotFoundException e){

            customResponse.setSuccess(false);
            customResponse.setMessage("Product not found");

            return ResponseEntity.badRequest().body(customResponse);
        }
        catch (BadRequestException e){

            customResponse.setSuccess(false);
            customResponse.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(customResponse);
        }
    }
}
