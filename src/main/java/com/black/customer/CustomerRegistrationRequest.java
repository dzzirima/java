package com.black.customer;

public record CustomerRegistrationRequest (
        String name,
        String email,
        Integer age
){
}
