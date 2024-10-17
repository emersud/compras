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

import com.manytomany.entities.ItemCompra;
import com.manytomany.services.ItemCompraService;
@RestController
@RequestMapping("/itens")
public class ItemCompraController {
	@Autowired
	private ItemCompraService itemCompraService;

	// Listar todos os itens de compra
	@GetMapping
	public ResponseEntity<List<ItemCompra>> getAllItensCompra() {
		List<ItemCompra> itens = itemCompraService.findAll();
		return ResponseEntity.ok(itens);
	}

	// Buscar item de compra por ID
	@GetMapping("/{id}")
	public ResponseEntity<ItemCompra> getItemCompraById(@PathVariable Long id) {
		Optional<ItemCompra> item = itemCompraService.findById(id);
		return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Criar novo item de compra
	@PostMapping
	public ResponseEntity<ItemCompra> createItemCompra(@RequestBody ItemCompra itemCompra) {
		ItemCompra novoItemCompra = itemCompraService.save(itemCompra);
		return ResponseEntity.ok(novoItemCompra);
	}

	// Atualizar item de compra existente
	@PutMapping("/{id}")
	public ResponseEntity<ItemCompra> updateItemCompra(@PathVariable Long id,
			@RequestBody ItemCompra itemCompraAtualizado) {
		ItemCompra itemAtualizadoResp = itemCompraService.update(id, itemCompraAtualizado);
		if (itemAtualizadoResp != null) {
			return ResponseEntity.ok(itemAtualizadoResp);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Deletar item de compra
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItemCompra(@PathVariable Long id) {
		itemCompraService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
