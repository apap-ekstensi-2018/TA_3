package com.tugasakhir.sisurat;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tugasakhir.sisurat.storage.StorageService;

@SpringBootApplication
public class SisuratApplication implements CommandLineRunner{

	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(SisuratApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
