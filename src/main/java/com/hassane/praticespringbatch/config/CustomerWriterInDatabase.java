package com.hassane.praticespringbatch.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;


import org.springframework.stereotype.Component;

import com.hassane.praticespringbatch.domain.Customer;
import com.hassane.praticespringbatch.service.CustomerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerWriterInDatabase implements ItemWriter<Customer> {

    private final CustomerService customerService;

    @Override
    public void write(List<? extends Customer> customers) {
        customers.forEach(customer -> {
            log.info("Registration of cusomer in database {}", customer);
            customerService.save(customer);
        });
    }


}
