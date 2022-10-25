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
import com.example.demo.entities.Funcionario;
import com.example.demo.repositories.FuncionarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FuncionarioController {

	
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	//READ ou GET all
	
	@GetMapping("/funcionarios")
	public List<Funcionario> getAllFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	//READ ou GET by id
	
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id){
		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionario inexistente"));
		return ResponseEntity.ok(funcionario);
	}
	
	//POST ou CREATE
	@PostMapping("/funcionarios")
	public Funcionario createFuncionario(@RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}
	
	
	
	//PUT ou UPDATE
	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionariosDetails){
		
		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionario Inexistente"));
		
		funcionario.setNome(funcionariosDetails.getNome());
		funcionario.setEmail(funcionariosDetails.getEmail());
		funcionario.setSenha(funcionariosDetails.getSenha());
		funcionario.setRegistroMatricula(funcionariosDetails.getRegistroMatricula());
		funcionario.setPerfil(funcionariosDetails.getPerfil());
		
		Funcionario newFuncionario = funcionarioRepository.save(funcionario);
		
		return ResponseEntity.ok(newFuncionario);
	}
	
	//DELETE
	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFuncionario(@PathVariable Long id){
		
		Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Funcionario Inexistente"));
		
		funcionarioRepository.delete(funcionario);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}