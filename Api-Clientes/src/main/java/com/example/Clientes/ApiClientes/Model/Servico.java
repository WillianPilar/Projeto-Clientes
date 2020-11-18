package com.example.Clientes.ApiClientes.Model;

import java.math.BigDecimal;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column(nullable = false, length = 150)
	public String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	public Clientes cliente;
	
	@Column
	public BigDecimal valor;
	
}
