package com.infogain.netflixzuulapigatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatewayServerApplication.class, args);
	}

	
	/* We have written following code to implement sleuth so that an id is assigned
	 * to our request and we can trace it across multiple microservices to know the
	 * flow and error, if any occurs. But the problem with sleuth is that we need to
	 * check logs of all the services. That's where Zipkin(Distributed
	 * Tracing) comes as a solution to trace flow across all the services for a
	 * request at single place.
	 */	
	@Bean
	public Sampler defaultSampler()
	{
		return Sampler.ALWAYS_SAMPLE;
	}

}
