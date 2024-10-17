package com.manytomany.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long produtoid;	

	private String produtonome;
	private String produtotipo;
	private double produtopreco;
	private int produtoestoque;
	

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ItemCompra> itens;
	
	
	public long getProdutoid() {
		return produtoid;
	}
	public void setProdutoid(long produtoid) {
		this.produtoid = produtoid;
	}
	public String getProdutonome() {
		return produtonome;
	}
	public void setProdutonome(String produtonome) {
		this.produtonome = produtonome;
	}
	public String getProdutotipo() {
		return produtotipo;
	}
	public void setProdutotipo(String produtotipo) {
		this.produtotipo = produtotipo;
	}
	public double getProdutopreco() {
		return produtopreco;
	}
	public void setProdutopreco(double produtopreco) {
		this.produtopreco = produtopreco;
	}
	public int getProdutoestoque() {
		return produtoestoque;
	}
	public void setProdutoestoque(int produtoestoque) {
		this.produtoestoque = produtoestoque;
	}
	
	
	

}
