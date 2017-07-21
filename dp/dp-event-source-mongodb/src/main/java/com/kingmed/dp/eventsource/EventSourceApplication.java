package com.kingmed.dp.eventsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.kingmed.dp.dto.TestDTO;

@EnableDiscoveryClient
@SpringBootApplication
public class EventSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventSourceApplication.class, args);
		
		TestDTO d = new TestDTO();
		d.dumy();
	}

}
