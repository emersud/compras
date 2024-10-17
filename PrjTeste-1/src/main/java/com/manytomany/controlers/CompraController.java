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

import com.manytomany.entities.Compra;
import com.manytomany.services.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {
	@Autowired
	private CompraService compraService;

	// Listar todas as compras
	@GetMapping
	public ResponseEntity<List<Compra>> getAllCompras() {
		List<Compra> compras = compraService.findAll();
		return ResponseEntity.ok(compras);
	}

	// Buscar compra por ID
	@GetMapping("/{id}")
	public ResponseEntity<Compra> getCompraById(@PathVariable Integer id) {
		Optional<Compra> compra = compraService.findById(id);
		return compra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Criar nova compra com itens
	@PostMapping
	public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
		Compra novaCompra = compraService.save(compra);
		return ResponseEntity.ok(novaCompra);
	}

	// Atualizar compra existente e seus itens
	@PutMapping("/{id}")
	public ResponseEntity<Compra> updateCompra(@PathVariable Integer id, @RequestBody Compra compraAtualizada) {
		Compra compraAtualizadaResp = compraService.update(id, compraAtualizada);
		if (compraAtualizadaResp != null) {
			return ResponseEntity.ok(compraAtualizadaResp);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	// Confirmar pagamento
	@PutMapping("/{id}/confirmar-pagamento")
    public ResponseEntity<Compra> confirmarPagamento(@PathVariable Integer id) {
        Compra compra = compraService.confirmarPagamento(id);
        return ResponseEntity.ok(compra);
    }

	// Deletar compra e seus itens
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompra(@PathVariable Integer id) {
		compraService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
