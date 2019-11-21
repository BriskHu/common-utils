package com.briskhu.util.db.admin.serverimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class DbAdminServerImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbAdminServerImplApplication.class, args);
    }

}
