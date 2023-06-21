package com.black;
import com.black.customer.domain.Customer;
import com.black.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;
@SpringBootApplication

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
    }
    //bootstrapping the application
    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        Customer david = new Customer("David", "davidtzirima@gmail.com", 12);
        Customer tafadzwa = new Customer("tafadzwa", "tafadzwa@gmail.com", 12);
        return args -> {
            List<Customer> david1 = List.of(david, tafadzwa);
            customerRepository.saveAll(david1);

        };
    }


}
