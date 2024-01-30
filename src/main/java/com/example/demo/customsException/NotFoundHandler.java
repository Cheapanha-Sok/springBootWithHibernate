package com.example.demo.customsException;

public class NotFoundHandler extends RuntimeException{
    public NotFoundHandler(){
        super();
    }
    public NotFoundHandler(String message , Throwable cause){
        super(message, cause);
    }
    public NotFoundHandler(String message){
        super(message);
    }
    public NotFoundHandler(Throwable cause){
        super(cause);
    }
}
