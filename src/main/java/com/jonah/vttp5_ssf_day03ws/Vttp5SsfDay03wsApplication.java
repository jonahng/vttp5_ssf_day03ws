package com.jonah.vttp5_ssf_day03ws;

import java.io.File;

import java.io.IOException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Vttp5SsfDay03wsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication app = new SpringApplication(Vttp5SsfDay03wsApplication.class);
		String fileLocation = "/opt/tmp/data";
		ApplicationArguments argOptions = new DefaultApplicationArguments(args);

		if(argOptions.containsOption("dataDir")){
			fileLocation = argOptions.getOptionValues("dataDir").get(0);
		}
		
		app.run(args);

}

}