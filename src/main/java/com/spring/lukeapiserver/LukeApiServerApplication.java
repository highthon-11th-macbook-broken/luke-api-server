package com.spring.lukeapiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LukeApiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LukeApiServerApplication.class, args);
    }

}
