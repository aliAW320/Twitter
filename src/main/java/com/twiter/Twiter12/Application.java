package com.twiter.Twiter12;

import com.twiter.Twiter12.DataBase.DBSetup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		DBSetup.setup();
		SpringApplication.run(Application.class, args);
	}

}
