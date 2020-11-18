package com.example.Clientes.ApiClientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Clientes.ApiClientes.Model.Clientes;
import com.example.Clientes.ApiClientes.Repository.ClientesRepository;

@SpringBootApplication
public class ApiClientesApplication {
	
	@Bean //Um bean é um objeto que é instanciado, montado e gerenciado pelo Spring IoC container.
	public CommandLineRunner run(@Autowired ClientesRepository clienteRepository) { //Será executado sempre que a aplicação começar
		return args -> {
			Clientes cliente = Clientes.builder().cpf("11111111111").nome("Fulano").build(); 
			//Forma mais prática de adicionar dados sem usar os Setters, funciona pela anotation @Builder
			
			clienteRepository.save(cliente);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiClientesApplication.class, args);
	}

}
