package com.manytomany.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientecodigo;

	@Column(unique = true, length = 15)
	private String clienteRg;

	@Column(length = 100)
	private String clienteNome;

	@Column(length = 15)
	private String clienteTelefone;

	@Column(length = 255)
	private String clienteEndereco;

	public Integer getClientecodigo() {
		return clientecodigo;
	}

	public void setClientecodigo(Integer clientecodigo) {
		this.clientecodigo = clientecodigo;
	}

	public String getClienteRg() {
		return clienteRg;
	}

	public void setClienteRg(String clienteRg) {
		this.clienteRg = clienteRg;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public String getClienteTelefone() {
		return clienteTelefone;
	}

	public void setClienteTelefone(String clienteTelefone) {
		this.clienteTelefone = clienteTelefone;
	}

	public String getClienteEndereco() {
		return clienteEndereco;
	}

	public void setClienteEndereco(String clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}
	
	

}
