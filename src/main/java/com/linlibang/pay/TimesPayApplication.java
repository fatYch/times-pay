package com.linlibang.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 肥美的洪洪哥
 * @version 2019-07-15
 */

@SpringBootApplication
@EnableCaching
//@EnableEurekaClient
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class TimesPayApplication {
	public static void main(String[] args) {
		SpringApplication.run(TimesPayApplication.class, args);
	}
}
