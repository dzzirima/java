package com.black.customer;

import com.black.customer.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerJPAAccessService implements CustomerDao{

    private  final  CustomerRepository customerRepository;

    public CustomerJPAAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public void insertUser(Customer customer) {
        customerRepository.save(customer);

    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }

    @Override
    public void deleteCustomer(Integer customerId) {

        customerRepository.deleteById(customerId);
    }

    @Override
    public boolean customerExistById(Integer customerId) {
       return customerRepository.existsById(customerId);
    }



    @Override
    public void updateCustomerDetails(Customer customer) {
        customerRepository.save(customer);

    }

}
