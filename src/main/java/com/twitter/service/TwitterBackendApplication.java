package com.twitter.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties
@EnableSwagger2
public class TwitterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterBackendApplication.class, args);
	}

}
