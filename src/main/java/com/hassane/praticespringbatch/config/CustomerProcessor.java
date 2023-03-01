package com.hassane.praticespringbatch.config;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.hassane.praticespringbatch.domain.Customer;


@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    @Override
    public Customer process(Customer customer) {
        customer.setId(customer.getId());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        customer.setTelephone(customer.getTelephone());
        
        return customer;
    }
}
