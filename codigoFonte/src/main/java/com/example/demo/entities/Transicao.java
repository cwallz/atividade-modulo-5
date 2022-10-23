package com.example.demo.entities;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_transicao")
public class Transicao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantidade_diarias")
	private int quantidadeDiarias;
	
	@Column(name = "data_viagem")
	private Date dataViagem;

	@Column(name = "optante_seguro")
	private boolean optanteSeguro;
	
	@Column(name = "taxa_seguro")
	private double taxaSeguro;

	
	
	public Transicao() {
		super();
	}
	public Transicao(Long id, int quantidadeDiarias, Date dataViagem, Boolean optanteSeguro, double taxaSeguro) {
		super();
		this.id = id;
		this.quantidadeDiarias = quantidadeDiarias;
		this.dataViagem = dataViagem;
		this.optanteSeguro = optanteSeguro;
		this.taxaSeguro = taxaSeguro;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantidadeDiarias() {
		return quantidadeDiarias;
	}
	public void setQuantidadeDiarias(int quantidadeDiarias) {
		this.quantidadeDiarias = quantidadeDiarias;
	}
	public Date getDataViagem() {
		return dataViagem;
	}
	public void setDataViagem(Date dataViagem) {
		this.dataViagem = dataViagem;
	}
	public Boolean getOptanteSeguro() {
		return optanteSeguro;
	}
	public void setOptanteSeguro(Boolean optanteSeguro) {
		this.optanteSeguro = optanteSeguro;
	}
	public double getTaxaSeguro() {
		return taxaSeguro;
	}
	public void setTaxaSeguro(double taxaSeguro) {
		this.taxaSeguro = taxaSeguro;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	
	
	//muitas transicoes para um cliente - quem recebe a FK
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	
	
	//muitas transicoes para um destino - quem recebe a FK
	@ManyToOne
	@JoinColumn(name = "id_destino")
	private Destino destino;
	
}
