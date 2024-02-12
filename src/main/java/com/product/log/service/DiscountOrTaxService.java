package com.product.log.service;

import com.product.log.dto.DiscountOrTax;
import com.product.log.model.Product;
import jakarta.el.PropertyNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountOrTaxService {

    private List<DiscountOrTax> discountOrTaxList;

    @Autowired
    private ProductService productService;

    public DiscountOrTaxService() {
        this.discountOrTaxList = new ArrayList<>();
    }

    public void addDiscountOrTax(DiscountOrTax discountOrTax){
        discountOrTaxList.add(discountOrTax);
    }

    public Product applyDiscountOrTaxOnProduct(DiscountOrTax discountOrTax) throws BadRequestException {
        Product product = productService.getProduct(discountOrTax.getProductId());

        if(product == null){
            throw new PropertyNotFoundException("Product not found");
        }

        if(discountOrTax.getDiscountOrTax().trim().equalsIgnoreCase("tax")){

            double newPrice = product.getPrice().doubleValue()*(1+discountOrTax.getDiscountOrTaxPercentage().doubleValue()/100);
            product.setPrice(newPrice);

        } else if(discountOrTax.getDiscountOrTax().trim().equalsIgnoreCase("discount")){

            double newPrice = product.getPrice().doubleValue()*(1-discountOrTax.getDiscountOrTaxPercentage().doubleValue()/100);
            product.setPrice(newPrice);

        } else {
            throw new BadRequestException("correct type not provided");
        }

        productService.updateProduct(product);
        addDiscountOrTax(discountOrTax);

        return product;
    }
}
