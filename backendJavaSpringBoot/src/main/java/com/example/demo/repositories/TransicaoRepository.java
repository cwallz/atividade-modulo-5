package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Transicao;

@Repository
public interface TransicaoRepository extends JpaRepository<Transicao, Long>{
	

}