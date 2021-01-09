package cn.luxury;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LuxuryRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuxuryRegistryApplication.class,args);
    }
}
