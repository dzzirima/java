package com.black.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer , Integer> {

    // adding custom repository
    boolean existsCustomerByEmail(String email);

}
