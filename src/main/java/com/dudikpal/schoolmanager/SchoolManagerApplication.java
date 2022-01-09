package com.dudikpal.schoolmanager;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;

@SpringBootApplication
public class SchoolManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolManagerApplication.class, args);
    }

}
