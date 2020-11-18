package com.example.Clientes.ApiClientes.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Clientes.ApiClientes.Model.Clientes;
import com.example.Clientes.ApiClientes.Service.ClientesService;

@RestController
@RequestMapping(value="/api/clientes")
public class ClientesController {
		
	@Autowired
	public ClientesService clientesService;
	
	@GetMapping
	public List<Clientes> getAllClientes() {
		return clientesService.getAll();
	}
	
	@GetMapping("/{id}")
	public Clientes getClienteById(@PathVariable int id) {
		return clientesService.getById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Clientes createCliente(@RequestBody Clientes cliente) {
		return clientesService.create(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCliente(@PathVariable int id) {
		
		Optional<Clientes> c = clientesService.getById(id);
		
		if(c.isPresent()) {
			clientesService.deleteById(id);
		}else {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCliete(@PathVariable int id, @RequestBody Clientes updateCliente) {
		Optional<Clientes> c = clientesService.getById(id);
		Clientes update = null;
		
		if(c.isPresent()) {
			update = c.get();
			update.setNome(updateCliente.getNome());
			update.setCpf(updateCliente.getCpf());
			
			
			clientesService.updateCliente(update);
		}else {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(update);
	}

}
