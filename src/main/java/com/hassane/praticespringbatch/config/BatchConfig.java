package com.hassane.praticespringbatch.config;




import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hassane.praticespringbatch.domain.Customer;
import lombok.RequiredArgsConstructor;


@Configuration
@EnableBatchProcessing
@EnableScheduling
@RequiredArgsConstructor
public class BatchConfig {


    private static final String JOB_NAME = "listCustomerJob";
    private static final String STEP_NAME = "processingStep";

    @Value("${header.names}")
    private String names;

    @Value("${line.delimiter}")
    private String delimiter;

    

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomerWriterInDatabase customerWriterInDatabase;
    private final CustomerWriteInFile customerWriteInFile;
    private final CustomerReader customerReader;
    private final CustomerProcessor customerProcessor;



    @Bean
    public Step customerStep() throws Exception {
        return stepBuilderFactory.get(STEP_NAME)
                .<Customer, Customer>chunk(5)
                .reader(customerReader.cuostomerItemReader())
                .processor(customerProcessor)
                .writer(customerWriterInDatabase)
                // .writer(customerWriteInFile.itemWriterFromFile())
                .build();
    }

    @Bean
    public Job customerJob(Step step1) {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1)
                .build();
    }

}
