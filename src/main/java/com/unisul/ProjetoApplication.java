package com.unisul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.unisul.projeto.graph.Grafo;

@Configuration
@SpringBootApplication
@EnableJpaRepositories("com.unisul.projeto.*")
@ComponentScan("com.unisul.projeto.*")
@EntityScan("com.unisul.projeto.*")
public class ProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Bean
	public Grafo<String> grafo() {
		return new Grafo<>();
	}

}