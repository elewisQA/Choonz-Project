package com.qa.choonz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.choonz.utils.AuthUtils;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ChoonzApplication {

    public static void main(String[] args) {
    	AuthUtils authManger = new AuthUtils(); // TODO find out if better place to initialize
        ApplicationContext context = SpringApplication.run(ChoonzApplication.class, args);
    }

}
