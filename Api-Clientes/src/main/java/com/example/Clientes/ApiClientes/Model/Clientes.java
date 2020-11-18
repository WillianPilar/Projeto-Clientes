package com.example.Clientes.ApiClientes.Model;

import java.time.LocalDate;
import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;



@Entity
@Data
@Builder //Para enviar dados aos construtores de forma mais prática
@NoArgsConstructor
@AllArgsConstructor
public class Clientes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	

	@Column(nullable = false, length = 100) //Para criar coluna no BD e passar as definições
	public String nome;
	
	@Column(nullable = false, length = 11)
	public String cpf;
	
	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")//Formatar o JSON de data que receber para o formato passado no pattern
	public LocalDate dataCadastro;
	
	@PrePersist //executa método anotado antes da entidade ser persistida (salva no BD)
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
	
}
