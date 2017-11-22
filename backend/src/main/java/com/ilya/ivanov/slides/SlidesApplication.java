package com.ilya.ivanov.slides;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Slf4j
public class SlidesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlidesApplication.class, args);
	}

	@Bean
    @Profile("dev")
	public CommandLineRunner dev() {
		return args -> {
            val property = System.getProperty("spring.devtools.restart.enabled");
            log.info("spring.devtools.restart.enabled: " + property);
        };
	}
}
