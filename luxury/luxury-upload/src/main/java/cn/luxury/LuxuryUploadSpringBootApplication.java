package cn.luxury;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LuxuryUploadSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuxuryUploadSpringBootApplication.class,args);
    }
}
