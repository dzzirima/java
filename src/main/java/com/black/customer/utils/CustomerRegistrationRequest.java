package com.black.customer.utils;

public record CustomerRegistrationRequest (
        String name,
        String email,
        Integer age
){
}
