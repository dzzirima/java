package com.black.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {
    public CustomerListDataAccessService() {
    }

    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();

        Customer david = new Customer(1, "David", "davidtzirima@gmail.com", 12);
        customers.add(david);
        Customer tafadzwa = new Customer(2, "tafadzwa", "tafadzwa@gmail.com", 12);
        customers.add(tafadzwa);

    }
    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream().
                filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertUser(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public void deleteCustomer(Integer customerId) {


    }

    @Override
    public boolean customerExistById(Integer customerId) {
        return false;
    }

    @Override
    public void updateCustomerDetails(Customer customer) {

    }
}
