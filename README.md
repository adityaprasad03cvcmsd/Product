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
Response: </br>
```
{
    "name": "testProduct",
    "description": "testDescription",
    "quantityAvailable": 5,
    "productId": "c615ec1c-62f8-4ab5-b8ea-ff419c8f75a3",
    "price": 56.36
}

```
