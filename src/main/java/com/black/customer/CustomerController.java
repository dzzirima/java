package com.black.customer;
// this is a controller class which intercepts all the http requests

import com.black.customer.domain.Customer;
import com.black.customer.utils.CustomerRegistrationRequest;
import com.black.customer.utils.CustomerUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();

    }

    @GetMapping("{customerId}")
    public Customer getCustomers(
            @PathVariable("customerId") Integer customerId
    ) {

        return customerService.getCustomerById(customerId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void reqisterCustomer(
            @RequestBody CustomerRegistrationRequest request
    ) {
        customerService.addCustomer(request);
    }

    @DeleteMapping("{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public  void deleteUser(
            @PathVariable("customerId") Integer customerId
    ){
      customerService.deleteCustomer(customerId);
    }

    @PutMapping("{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public  void updateCustomer(
           @PathVariable("customerId") Integer customerId,
           @RequestBody CustomerUpdateRequest update

    ){
        customerService.updateCustomer(customerId , update);
    }

}
