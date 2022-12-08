package com.bstek.ureport.console;

import com.bstek.ureport.console.config.DataReportListener;
import com.sinosoft.ie.booster.common.feign.annotation.EnableBoosterFeignClients;
import com.sinosoft.ie.booster.common.security.annotation.EnableBoosterResourceServer;
import com.sinosoft.ie.booster.common.swagger.annotation.EnableBoosterSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:ureport.xml")
@EnableBoosterSwagger2
@EnableBoosterFeignClients
@EnableBoosterResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class DataReportApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(DataReportApplication.class);
        //添加监听器
        springApplication.addListeners(new DataReportListener());
        springApplication.run(args);
    }

    @Bean
    public ServletRegistrationBean buildUreportServlet(){
        return new ServletRegistrationBean(new UReportServlet(), "/report/*");
    }

}
