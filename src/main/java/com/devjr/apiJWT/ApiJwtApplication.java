package com.devjr.apiJWT;

import com.devjr.apiJWT.models.EnumRoles;
import com.devjr.apiJWT.models.Role;
import com.devjr.apiJWT.models.UserEntity;
//import com.devjr.apiJWT.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ApiJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiJwtApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			Role roleAdmin= Role.builder()
					.name(EnumRoles.ADMIN)
					.build();

			Role roleUser= Role.builder()
					.name(EnumRoles.USER)
					.build();

			UserEntity userAdmin= UserEntity.builder()
					.name("junior")
					.username("devjr")
					.password(passwordEncoder.encode("1234"))
					.date(LocalDate.now())
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity user= UserEntity.builder()
					.name("kevin")
					.username("kerubin")
					.password(passwordEncoder.encode("5678"))
					.date(LocalDate.now())
					.roles(Set.of(roleUser))
					.build();

			userRepository.saveAll(List.of(user,userAdmin));
		};
	}*/

}
