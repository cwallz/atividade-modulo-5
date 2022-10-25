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
import com.example.demo.repositories.ClienteRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ClienteController {

	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	//READ ou GET all
	
	@GetMapping("/clientes")
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	//READ ou GET by id
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id){
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente inexistente"));
		return ResponseEntity.ok(cliente);
	}
	
	//POST ou CREATE
	@PostMapping("/clientes")
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	
	
	//PUT ou UPDATE
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clientesDetails){
		
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente Inexistente"));
		
		cliente.setNome(clientesDetails.getNome());
		cliente.setEmail(clientesDetails.getEmail());
		cliente.setSenha(clientesDetails.getSenha());
		cliente.setCpf(clientesDetails.getCpf());
		cliente.setDataDeNascimento(clientesDetails.getDataDeNascimento());
		
		Cliente newCliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(newCliente);
	}
	
	//DELETE
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCliente(@PathVariable Long id){
		
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente Inexistente"));
		
		clienteRepository.delete(cliente);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
