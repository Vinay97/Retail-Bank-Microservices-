package com.tcs.ilp.DemoZuulGateway.DemoZuulGateway_1450967_SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
 class DemoZuulGateway1450967SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoZuulGateway1450967SpringBootApplication.class, args);
	}
}
