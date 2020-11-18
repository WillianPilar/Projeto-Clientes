package com.example.Clientes.ApiClientes.DTO;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import com.example.Clientes.ApiClientes.Model.Clientes;

import lombok.*;




@Data
@Builder //Para enviar dados aos construtores de forma mais prática
@AllArgsConstructor
@NoArgsConstructor

public class ClientesDTO {
	
	private Integer id;
	private String nome;
	private String cpf;
	private LocalDate dataCadastro;
	
	public ClientesDTO crate (Clientes cliente) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(cliente, ClientesDTO.class);
		
	}

}
