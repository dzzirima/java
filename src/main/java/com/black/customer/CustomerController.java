package com.black.customer;
// this is a controller class which intercepts all the http requests

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/api/v1/customers")
    public List<Customer> getCustomers(){
        return  customerService.getAllCustomers();
    };
    @GetMapping("/api/v1/customers/{customerId}")
    public  Customer getCustomers(
            @PathVariable("customerId") Integer customerId
    ){

return  customerService.getCustomerById(customerId);


    };


}
