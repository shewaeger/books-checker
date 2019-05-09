package com.github.shewaeger.bookschecker;

import com.github.shewaeger.springinvoker.annotation.EnableMethodInvoker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMethodInvoker
public class BooksCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksCheckerApplication.class, args);
	}

}
