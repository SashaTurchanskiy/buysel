package com.example.buysell.exception;

public class ProductDoesNotExistException extends RuntimeException{


        public ProductDoesNotExistException(){
        super("Product does not exist");
        }
}
