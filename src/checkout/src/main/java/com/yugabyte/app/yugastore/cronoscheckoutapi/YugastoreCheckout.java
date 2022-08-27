package com.yugabyte.app.yugastore.cronoscheckoutapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(excludeName = {
 "org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration",
  "org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration"
})
@EnableFeignClients
public class YugastoreCheckout {

	public static void main(String[] args) {
		SpringApplication.run(YugastoreCheckout.class, args);
	}

}
