package com.example.demo.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "tb_destino")
public class Destino {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "preco_pacote")
	private double precoDoPacote;
	
	@Column(name = "promocao")
	private boolean promocao;

	@Column(name = "desconto")
	private double desconto;
	
	@Column(name = "url_foto")
	private String urlFoto;

	
	
	public Destino() {
		super();
	}
	public Destino(long id, String nome, String cidade, String estado, double precoDoPacote, boolean promocao,
			double desconto, String urlFoto) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
		this.precoDoPacote = precoDoPacote;
		this.promocao = promocao;
		this.desconto = desconto;
		this.urlFoto = urlFoto;
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public double getPrecoDoPacote() {
		return precoDoPacote;
	}
	public void setPrecoDoPacote(double precoDoPacote) {
		this.precoDoPacote = precoDoPacote;
	}
	public boolean isPromocao() {
		return promocao;
	}
	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	
	
		
	//muitos destinos para um funcionario - quem recebe a FK
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	
	
	//Quem envia a FK
	@JsonIgnore
	@OneToMany(mappedBy = "destino")
	private List<Transicao> transicoes = new ArrayList<Transicao>();

	public List<Transicao> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(List<Transicao> transicoes) {
		this.transicoes = transicoes;
	}
	
	
	
}
