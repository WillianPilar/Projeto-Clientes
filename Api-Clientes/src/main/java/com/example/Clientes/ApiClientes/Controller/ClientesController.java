package com.example.Clientes.ApiClientes.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.Clientes.ApiClientes.Model.Clientes;
import com.example.Clientes.ApiClientes.Service.ClientesService;

@RestController
@RequestMapping(value="/api/clientes")
@CrossOrigin("http://localhost:4200")
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
	public Clientes createCliente(@RequestBody @Valid Clientes cliente) {
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
	public ResponseEntity<?> updateCliete(@PathVariable int id, @RequestBody @Valid Clientes updateCliente) {
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
