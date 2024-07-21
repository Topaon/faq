package com.example.faq;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FaqApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaqApplication.class, args);
	}
	
	public static void test() {
		File file = new File(".");
		System.out.println(file.getAbsolutePath());
	}
}