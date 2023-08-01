package org.bedu.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.bedu.testing"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}