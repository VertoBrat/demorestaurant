package ru.photorex.demorestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemorestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemorestaurantApplication.class, args);
    }
}
