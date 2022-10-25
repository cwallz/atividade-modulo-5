package com.example.demo.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tb_funcionario")
public class Funcionario{

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "registro_matricula")
	private String registroMatricula;
	
	@Column(name = "perfil")
	private String perfil;

	
	
	public Funcionario() {
		super();
	}
	public Funcionario(long id, String nome, String email, String senha, String registroMatricula, String perfil) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.registroMatricula = registroMatricula;
		this.perfil = perfil;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getRegistroMatricula() {
		return registroMatricula;
	}
	public void setRegistroMatricula(String registroMatricula) {
		this.registroMatricula = registroMatricula;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


	//Quem envia a FK
	@JsonIgnore
	@OneToMany(mappedBy = "funcionario")
	private List<Destino> destinos = new ArrayList<Destino>();

	public List<Destino> getDestinos() {
		return destinos;
	}

	public void setDestinos(List<Destino> destinos) {
		this.destinos = destinos;
	}
	
}
