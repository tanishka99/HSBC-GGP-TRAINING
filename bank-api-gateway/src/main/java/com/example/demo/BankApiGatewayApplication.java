package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApiGatewayApplication.class, args);
	}
	
	@Autowired
	private TokenRelayGatewayFilterFactory filterFactory;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route(p -> p.path("/tanubank/accounts/**")
				.filters(f -> f.rewritePath("/tanubank/accounts/(?<segment>.*)", "/${segment}")
						.removeRequestHeader("Cookie"))
				.uri("lb://ACCOUNTS"))
				.build();

	}

}
