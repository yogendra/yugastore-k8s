package com.yugabyte.app.yugastore.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class YugastoreCart {

	public static void main(String[] args) {
		SpringApplication.run(YugastoreCart.class, args);
	}

}
