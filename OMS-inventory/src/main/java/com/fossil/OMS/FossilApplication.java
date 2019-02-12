package com.fossil.OMS;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fossil.OMS.Daos.SFCCInventoryJdbcRepository;

@SpringBootApplication
public class FossilApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    SFCCInventoryJdbcRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(FossilApplication.class, args);
    }
    @Override
    public void run(String...args) throws Exception {
        logger.info("Catentry id 120001 -> {}", repository.findByCatentryId(120001L));
    }

}

