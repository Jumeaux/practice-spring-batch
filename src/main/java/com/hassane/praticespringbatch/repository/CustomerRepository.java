package com.hassane.praticespringbatch.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.hassane.praticespringbatch.domain.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    
}
