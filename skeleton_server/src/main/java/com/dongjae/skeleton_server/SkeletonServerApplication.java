package com.dongjae.skeleton_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class SkeletonServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkeletonServerApplication.class, args);
	}

}
