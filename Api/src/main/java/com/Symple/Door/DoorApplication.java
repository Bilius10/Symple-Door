package com.Symple.Door;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DoorApplication {

	//https://dashboard.ngrok.com/get-started/setup/windows
	//ngrok http (porta rodando)
	public static void main(String[] args) {
		SpringApplication.run(DoorApplication.class, args);
	}

}
	