package com.hassane.praticespringbatch.domain;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {
    @Override
    public Customer mapFieldSet(FieldSet fieldSet) {
        return new Customer()
        .id(fieldSet.readLong(0))
        .firstName(fieldSet.readString(1))
        .lastName(fieldSet.readString(2))
        .email(fieldSet.readString(3))
        .telephone(fieldSet.readString(4));

        
    }
}

