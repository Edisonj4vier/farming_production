package com.farming_production.farming_production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FarmingProductionApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(FarmingProductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password="programador2";	
		String bcryptPassword=passwordEncoder.encode(password);
		System.out.println(bcryptPassword);
		
	}

}
