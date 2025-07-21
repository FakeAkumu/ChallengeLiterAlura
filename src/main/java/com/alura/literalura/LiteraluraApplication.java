package com.alura.literalura;

import com.alura.literalura.main.Principal;
import com.alura.literalura.repository.AutorRepositorio;
import com.alura.literalura.repository.LivroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	private LivroRepositorio repositorioL;
	@Autowired
	private AutorRepositorio repositorioA;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Principal principal = new Principal(repositorioL, repositorioA);
		principal.exibeMenu();
	}
}
