package com.black.customer;

import com.black.customer.domain.Customer;
import com.black.customer.utils.CustomerRegistrationRequest;
import com.black.customer.utils.CustomerUpdateRequest;
import com.black.exception.DuplicateResourceException;
import com.black.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomers() {
        return customerDao.selectAllCustomers();
    }

    public Customer getCustomerById(Integer id) {
        return customerDao.selectCustomerById(id)
                .orElseThrow(() -> new ResourceNotFound(
                        "Customer with id %s does not exist  ".formatted(id)));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        //some logic here
        /*
         * 1. check if email exist
         * 2.
         */
        String email = customerRegistrationRequest.email();

        if (customerDao.existsPersonWithEmail(email)) {

            throw new DuplicateResourceException("email already exists");
        }
        //add
        customerDao.insertUser(
                new Customer(customerRegistrationRequest.name(),
                        customerRegistrationRequest.email(),
                        customerRegistrationRequest.age()
                )
        );
    }

    public void deleteCustomer(Integer customerId) {

        if (!customerDao.customerExistById(customerId)) {
            throw new ResourceNotFound("User with id [  %s ] not found !!".formatted(customerId));
        }

        customerDao.deleteCustomer(customerId);

    }

    public void updateCustomer(Integer customerId, CustomerUpdateRequest customerUpdateRequest) {
        // check if id exist
        if (!customerDao.customerExistById(customerId)) {
            throw new ResourceNotFound("not found ");
        }

        Customer customer = getCustomerById(customerId);

        //check if email is the same

        boolean changes = false;
        /*
         * if the updateName is  not null
         * if name has changed
         */

        if (customerUpdateRequest.name() != null && !customerUpdateRequest.name().equals(customer.getName())) {
            customer.setName(customerUpdateRequest.name());
            changes = true;
        }

        customer.setAge(customerUpdateRequest.age());

        if (customerUpdateRequest.email() != null && !customerUpdateRequest.email().equals(customer.getEmail())) {
            // check if email is not taken
            if (customerDao.existsPersonWithEmail(customerUpdateRequest.email())) {
                throw new DuplicateResourceException("email already taken");
            }
            customer.setEmail(customerUpdateRequest.email());
        }


        //save new customer
        customerDao.updateCustomerDetails(customer);


    }
}
