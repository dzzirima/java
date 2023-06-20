package com.black.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerDao {


    List<Customer> selectAllCustomers ();
    Optional<Customer> selectCustomerById (Integer id);
    void insertUser(Customer customer);

    boolean existsPersonWithEmail(String email);
    void  deleteCustomer(Integer customerId);

    boolean customerExistById(Integer customerId);

    void updateCustomerDetails(Customer customer);



}
