package com.example.Clientes.ApiClientes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Clientes.ApiClientes.Model.Clientes;

public interface ClientesRepository extends JpaRepository<Clientes, Integer>{

}
