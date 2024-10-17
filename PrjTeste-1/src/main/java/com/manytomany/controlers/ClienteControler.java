package com.manytomany.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manytomany.entities.Cliente;
import com.manytomany.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteControler {
	@Autowired
	private ClienteService clienteService;

	// Listar todos os clientes
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok(clientes);
	}

	// Buscar cliente por ID
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienteService.findById(id);
		return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Criar um novo cliente
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		Cliente novoCliente = clienteService.save(cliente);
		return ResponseEntity.ok(novoCliente);
	}

	// Atualizar cliente existente
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
		Cliente clienteAtualizadoResp = clienteService.update(id, clienteAtualizado);
		if (clienteAtualizadoResp != null) {
			return ResponseEntity.ok(clienteAtualizadoResp);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Deletar cliente
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
