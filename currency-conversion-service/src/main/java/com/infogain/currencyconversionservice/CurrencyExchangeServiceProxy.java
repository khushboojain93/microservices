package com.infogain.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8000")
/*We have removed url from Feign below because we don't need to mention the instance(uri) with which we want to interact. 
We want to interact with multiple instances of same service(load balancing) and that's why we are using Ribbon. 
We define the instances in application.properties and that's where the problem arise because we need to manually make changes in configuration everytime there will be change in instances of application with whom we are interacting.
. That problem is resolved by Naming Server(Eureka)
*/
//@FeignClient(name="currency-exchange-service")
//We have commented above annotation because we aren't calling currency-exchange directly now. We are going through zuul gateway
@FeignClient(name="netflix-zuul-api-gateway-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	//In the following method, we specify the method that needs to be called(retrieveExchangeValue from CurrencyExchangeController)
	//But make sure the response type is modified as per current microservice(we modified ExchangeValue type to CurrencyConversionBean in following method)
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	//We have commented above Get Mapping because now the request involves name of service being called(currency-exchange-service)
	//because of Zuul gateway
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
