package com.example.Clientes.ApiClientes.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Clientes.ApiClientes.Model.Clientes;
import com.example.Clientes.ApiClientes.Repository.ClientesRepository;

@Service
public class ClientesService {
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	public List<Clientes> getAll() {
		 return this.clientesRepository.findAll();
		
	}

	public Clientes create(Clientes cliente) {
		
		return clientesRepository.save(cliente);
	}

	public Optional<Clientes> getById(int id) {

		return clientesRepository.findById(id);
	}

	public void deleteById(int id) {
		
		clientesRepository.deleteById(id);
		
	}

	public void updateCliente(Clientes updateCliente) {
		
		clientesRepository.save(updateCliente);
	}

	

}
