package com.black.customer;

public record CustomerUpdateRequest(
        String email,
        String name,
        Integer age
) {
}
