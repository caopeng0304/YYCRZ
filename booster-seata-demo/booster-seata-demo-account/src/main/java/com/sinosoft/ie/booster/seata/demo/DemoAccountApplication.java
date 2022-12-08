package com.sinosoft.ie.booster.seata.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author dpy
 */
@EnableDiscoveryClient
@MapperScan(value = "com.sinosoft.ie.booster.seata.demo.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAccountApplication.class, args);
    }

}
