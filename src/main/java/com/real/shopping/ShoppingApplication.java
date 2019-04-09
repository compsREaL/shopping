package com.real.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication(scanBasePackages = "com.real.shopping")
@Controller
@MapperScan(basePackages = "com.real.shopping.dao")
public class ShoppingApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ShoppingApplication.class, args);
    }

}
