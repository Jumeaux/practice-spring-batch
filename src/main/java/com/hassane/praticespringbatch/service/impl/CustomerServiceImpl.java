package com.hassane.praticespringbatch.service.impl;

import org.springframework.stereotype.Service;

import com.hassane.praticespringbatch.domain.Customer;
import com.hassane.praticespringbatch.repository.CustomerRepository;
import com.hassane.praticespringbatch.service.CustomerService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
       customerRepository.save(customer);
    }

    

}
