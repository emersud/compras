package com.manytomany.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer compraCodigo;

	@ManyToOne
	@JoinColumn(name = "clientecodigo")
	private Cliente cliente;

	private LocalDateTime compraData;

	private double compraValorTotal;
	
    // Novo campo: forma de pagamento (cartão, boleto, etc.)
    private String formaPagamento;

    // Novo campo: confirmação de pagamento (true/false)
    private Boolean pagamentoConfirmado;

	// Adicionando o relacionamento OneToMany com ItemCompra
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemCompra> itens;

	// Getters e setters
	public Integer getCompraCodigo() {
		return compraCodigo;
	}

	public void setCompraCodigo(Integer compraCodigo) {
		this.compraCodigo = compraCodigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getCompraData() {
		return compraData;
	}

	public void setCompraData(LocalDateTime compraData) {
		this.compraData = compraData;
	}

	public double getCompraValorTotal() {
		return compraValorTotal;
	}

	public void setCompraValorTotal(double compraValorTotal) {
		this.compraValorTotal = compraValorTotal;
	}

	public List<ItemCompra> getItens() {
		return itens;
	}

	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Boolean getPagamentoConfirmado() {
		return pagamentoConfirmado;
	}

	public void setPagamentoConfirmado(Boolean pagamentoConfirmado) {
		this.pagamentoConfirmado = pagamentoConfirmado;
	}
	
	
}
