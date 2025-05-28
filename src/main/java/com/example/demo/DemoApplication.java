package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "Hello Jenkins!";
	}

	@GetMapping("/greet")
	public String greet(@RequestParam(defaultValue = "World") String name) {
		return "Hello, " + name + "!";
	}

	@PostMapping("/add")
	public int add(@RequestParam int a, @RequestParam int b) {
		return a + b;
	}
}
