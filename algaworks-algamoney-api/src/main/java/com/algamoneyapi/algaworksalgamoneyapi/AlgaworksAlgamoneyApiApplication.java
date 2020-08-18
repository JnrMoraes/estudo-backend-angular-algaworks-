package com.algamoneyapi.algaworksalgamoneyapi;

import com.algamoneyapi.algaworksalgamoneyapi.config.property.AlgamoneyApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgaworksAlgamoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgaworksAlgamoneyApiApplication.class, args);
	}

}
