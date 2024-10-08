package uk.zwfield.jx3bot;

import jx3api.api.config.EnableJX3Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication(scanBasePackages = {"uk.zwfield.jx3bot", "jx3api.api"})
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableJX3Api
public class Jx3botApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jx3botApplication.class, args);
    }

}



