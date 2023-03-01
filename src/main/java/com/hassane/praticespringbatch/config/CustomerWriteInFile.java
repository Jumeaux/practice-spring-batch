package com.hassane.praticespringbatch.config;

import org.springframework.stereotype.Component;

import com.hassane.praticespringbatch.domain.Customer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerWriteInFile {


    private static final String FILE_NAME = "output-data/customer-output.csv";

    @Value("${header.names}")
    private String names;


    public FlatFileItemWriter<Customer> itemWriterFromFile() throws Exception {
        
        log.info("Registration of cusomer in file{}");
        
        BeanWrapperFieldExtractor<Customer> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(names.split(","));
        fieldExtractor.afterPropertiesSet();

        DelimitedLineAggregator<Customer> lineAggregator = new DelimitedLineAggregator<>();

        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);

        return new FlatFileItemWriterBuilder<Customer>()
                .name("CustomerWriterInFile")
                .resource(new FileSystemResource(FILE_NAME))
                .lineAggregator(lineAggregator)
                .headerCallback(write -> write.write(names))
                .footerCallback(write -> write.write("com.hassane"))
                .build();
    }

}
