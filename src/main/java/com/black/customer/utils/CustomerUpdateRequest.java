package com.black.customer.utils;

public record CustomerUpdateRequest(
        String email,
        String name,
        Integer age
) {
}
