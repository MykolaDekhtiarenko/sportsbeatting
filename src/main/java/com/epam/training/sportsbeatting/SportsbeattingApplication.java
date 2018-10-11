package com.epam.training.sportsbeatting;

import com.epam.training.sportsbeatting.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportsbeattingApplication implements CommandLineRunner {

	@Autowired
	private View view;

	public static void main(String[] args) {
		SpringApplication.run(SportsbeattingApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		view.show();
	}
}
