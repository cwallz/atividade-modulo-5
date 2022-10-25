package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResourceNotFoundException;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Destino;
import com.example.demo.entities.Transicao;
import com.example.demo.repositories.ClienteRepository;
import com.example.demo.repositories.DestinoRepository;
import com.example.demo.repositories.TransicaoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TransicaoController {

	
	
	@Autowired
	private TransicaoRepository transicaoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private DestinoRepository destinoRepository;
	
	//READ ou GET all
	
	@GetMapping("/transicoes")
	public List<Transicao> getAllTransicoes(){
		return transicaoRepository.findAll();
	}
	
	//READ ou GET by id
	
	@GetMapping("/transicoes/{id}")
	public ResponseEntity<Transicao> getTransicaoById(@PathVariable Long id){
		Transicao transicao = transicaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transicao inexistente"));
		return ResponseEntity.ok(transicao);
	}
	
	//POST ou CREATE
	@PostMapping("/transicoes")
	public Transicao createTransicao(@RequestBody Transicao transicao) {
		return transicaoRepository.save(transicao);
	}
	
	
	
	//PUT ou UPDATE
	@PutMapping("/transicoes/{id}")
	public ResponseEntity<Transicao> updateTransicao(@PathVariable Long id, @RequestBody Transicao transicoesDetails){
		
		Transicao transicao = transicaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transicao Inexistente"));
		
		Cliente	cliente	= clienteRepository.findById(transicoesDetails.getCliente().getId()).get();
		Destino	destino	= destinoRepository.findById(transicoesDetails.getDestino().getId()).get();
		
		transicao.setQuantidadeDiarias(transicoesDetails.getQuantidadeDiarias());
		transicao.setDataViagem(transicoesDetails.getDataViagem());
		transicao.setOptanteSeguro(transicoesDetails.getOptanteSeguro());
		transicao.setTaxaSeguro(transicoesDetails.getTaxaSeguro());
		transicao.setCliente(cliente);
		transicao.setDestino(destino);
		
		Transicao newTransicao = transicaoRepository.save(transicao);
		
		return ResponseEntity.ok(newTransicao);
	}
	
	//DELETE
	@DeleteMapping("/transicoes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTransicao(@PathVariable Long id){
		
		Transicao transicao = transicaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transicao Inexistente"));
		
		transicaoRepository.delete(transicao);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
