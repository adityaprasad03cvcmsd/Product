# Product App

Backend of the product app in Springboot with POST, PUT, GET and DELETE requests and their jUnit test

# Installation
To run the code run the file ProductApplication.java file, it runs on port 8001.

# Curls and Response

to create product </br>
```
curl --location 'localhost:8001/product' \
--header 'Content-Type: application/json' \
--data '{
    "name":"testProduct",
    "description":"testDescription",
    "price":56.36,
    "quantityAvailable":5
}'
```
</br>

to get product </br>
```
curl --location 'localhost:8001/product/c615ec1c-62f8-4ab5-b8ea-ff419c8f75a3'
```
</br>

to get all products

```
curl --location 'localhost:8001/product'
```

to update the product </br>

```
curl --location --request PUT 'localhost:8001/product' \
--header 'Content-Type: application/json' \
--data '{
    "name": "testProduct",
    "description": "testDescription123",
    "quantityAvailable": 5,
    "productId": "c615ec1c-62f8-4ab5-b8ea-ff419c8f75a3",
    "price": 56.36
}'

```

to apply tax </br>

```
curl --location --request PATCH 'localhost:8001/product/discount-tax' \
--header 'Content-Type: application/json' \
--data '{
    "productId": "c615ec1c-62f8-4ab5-b8ea-ff419c8f75a3",
    "discountOrTax":"tax",
    "discountOrTaxPercentage": 5.32
}'

```

to apply discount </br>

```
curl --location --request PATCH 'localhost:8001/product/discount-tax' \
--header 'Content-Type: application/json' \
--data '{
    "productId": "c615ec1c-62f8-4ab5-b8ea-ff419c8f75a3",
    "discountOrTax":"discount",
    "discountOrTaxPercentage": 5.32
}'
```

to delete a product </br>

```
curl --location --request DELETE 'localhost:8001/product/c615ec1c-62f8-4ab5-b8ea-ff419c8f75a3' \
--header 'Content-Type: application/json' \
--data '{
    "description": "test123",
    "name": "test",
    "price": 56.36,
    "productId": "84547a87-61e2-4dd2-8301-313e741563dd",
    "quantityAvailable": 5
}'
```


