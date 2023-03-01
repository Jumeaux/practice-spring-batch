package com.hassane.praticespringbatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@Service
public class Scheduler {

    private final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private BatchLauncher batchLauncher;

   // @Scheduled(fixedDelay = 5000)
    public void perform() throws Exception {
        log.info("Batch programmed to run every 5 seconds");
        batchLauncher.run();
    }
}
