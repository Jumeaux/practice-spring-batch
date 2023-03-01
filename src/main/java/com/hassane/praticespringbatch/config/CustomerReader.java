package com.hassane.praticespringbatch.config;


import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.hassane.praticespringbatch.domain.Customer;
import com.hassane.praticespringbatch.domain.CustomerFieldSetMapper;




@Service
@RequiredArgsConstructor
public class CustomerReader{

    private static final String FILE_NAME = "customer.csv";
    private static final String READER_NAME = "customerItemReader";

    @Value("${header.names}")
    private String names;

    @Value("${line.delimiter}")
    private String delimiter;

   
    public FlatFileItemReader<Customer> cuostomerItemReader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        
        reader.setResource(new ClassPathResource(FILE_NAME));
        reader.setName(READER_NAME);
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;

    }



    public LineMapper<Customer> lineMapper() {

        final DefaultLineMapper<Customer> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(names.split(delimiter));

        final CustomerFieldSetMapper fieldSetMapper = new CustomerFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }


}
