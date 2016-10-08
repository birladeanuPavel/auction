package com.birladeanu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuctionApplication {
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		logger.debug("@@@@@@@@@TESTTTT");
		SpringApplication.run(AuctionApplication.class, args);
	}
}
