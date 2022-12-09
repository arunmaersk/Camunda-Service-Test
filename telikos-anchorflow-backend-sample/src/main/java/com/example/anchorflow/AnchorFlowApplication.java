package com.example.anchorflow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class AnchorFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnchorFlowApplication.class, args);
	}

}
