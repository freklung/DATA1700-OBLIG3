package com.example.data1700oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Data1700Oblig3Application {

    @Autowired
    BillettRepository billettRepository;
    public static void main(String[] args) {
        SpringApplication.run(Data1700Oblig3Application.class, args);
    }

}
