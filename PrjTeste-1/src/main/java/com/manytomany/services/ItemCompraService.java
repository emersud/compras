package com.manytomany.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manytomany.entities.ItemCompra;
import com.manytomany.repositories.ItemCompraRepository;

@Service
public class ItemCompraService {
	
	 @Autowired
	    private ItemCompraRepository itemCompraRepository;

	    // Listar todos os itens de compra
	    public List<ItemCompra> findAll() {
	        return itemCompraRepository.findAll();
	    }

	    // Buscar item de compra por ID
	    public Optional<ItemCompra> findById(Long id) {
	        return itemCompraRepository.findById(id);
	    }

	    // Salvar novo item de compra
	    public ItemCompra save(ItemCompra itemCompra) {
	        return itemCompraRepository.save(itemCompra);
	    }

	    // Atualizar item de compra
	    public ItemCompra update(Long id, ItemCompra itemCompraAtualizado) {
	        Optional<ItemCompra> itemExistente = itemCompraRepository.findById(id);
	        if (itemExistente.isPresent()) {
	            ItemCompra item = itemExistente.get();
	            item.setQuantidade(itemCompraAtualizado.getQuantidade());
	            item.setPrecoUnitario(itemCompraAtualizado.getPrecoUnitario());
	            item.setCompra(itemCompraAtualizado.getCompra());
	            item.setProduto(itemCompraAtualizado.getProduto());
	            return itemCompraRepository.save(item);
	        }
	        return null; // Ou lançar exceção caso o item não exista
	    }

	    // Deletar item de compra
	    public void deleteById(Long id) {
	        itemCompraRepository.deleteById(id);
	    }

}
