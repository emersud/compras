package com.manytomany.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manytomany.entities.Cliente;
import com.manytomany.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por ID
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    // Salvar novo cliente
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualizar cliente
    public Cliente update(Integer id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setClienteNome(clienteAtualizado.getClienteNome());
            cliente.setClienteRg(clienteAtualizado.getClienteRg());
            cliente.setClienteTelefone(clienteAtualizado.getClienteTelefone());
            cliente.setClienteEndereco(clienteAtualizado.getClienteEndereco());
            return clienteRepository.save(cliente);
        }
        return null; // Ou lançar exceção caso o cliente não exista
    }

    // Deletar cliente
    public void deleteById(Integer id) {
        clienteRepository.deleteById(id);
    }
	
	

}
