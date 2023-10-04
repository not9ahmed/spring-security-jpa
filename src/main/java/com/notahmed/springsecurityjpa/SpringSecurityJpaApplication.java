package com.notahmed.springsecurityjpa;

import com.notahmed.springsecurityjpa.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
//@RequestMapping("/api/v1")
@EnableJpaRepositories(basePackages = {"com.notahmed.springsecurityjpa.repository"})
public class SpringSecurityJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJpaApplication.class, args);
	}

}
