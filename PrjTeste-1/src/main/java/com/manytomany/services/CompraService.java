package com.manytomany.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manytomany.entities.Cliente;
import com.manytomany.entities.Compra;
import com.manytomany.entities.ItemCompra;
import com.manytomany.entities.Produto;
import com.manytomany.repositories.ClienteRepository;
import com.manytomany.repositories.CompraRepository;
import com.manytomany.repositories.ItemCompraRepository;
import com.manytomany.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Autowired
	private ItemCompraRepository itemCompraRepository;

	@Autowired
	private ClienteRepository clienteRepository; // Adicione o repositório do Cliente

	@Autowired
	private ProdutoRepository produtoRepository; // Adicione o repositório do Produto

	// Listar todas as compras
	public List<Compra> findAll() {
		return compraRepository.findAll();
	}

	// Buscar compra por ID
	public Optional<Compra> findById(Integer id) {
		return compraRepository.findById(id);
	}

	// Salvar nova compra com itens
	public Compra save(Compra compra) {
		// Buscar o cliente existente pelo ID
		Cliente cliente = clienteRepository.findById(compra.getCliente().getClientecodigo())
				.orElseThrow(() -> new EntityNotFoundException(
						"Cliente não encontrado com ID: " + compra.getCliente().getClientecodigo()));

		// Definir o cliente completo na compra
		compra.setCliente(cliente);

		// Salvar a compra para obter o ID gerado
		Compra novaCompra = compraRepository.save(compra);

		// Salvar os itens da compra
		for (ItemCompra item : compra.getItens()) {
			// Buscar o produto existente pelo ID
			Produto produto = produtoRepository.findById(item.getProduto().getProdutoid())
					.orElseThrow(() -> new EntityNotFoundException(
							"Produto não encontrado com ID: " + item.getProduto().getProdutoid()));

			// Definir o produto completo no item
			item.setProduto(produto);
			item.setCompra(novaCompra);
			itemCompraRepository.save(item);
		}

		return novaCompra;
	}

	// Atualizar compra e itens relacionados
	public Compra update(Integer id, Compra compraAtualizada) {
		Optional<Compra> compraExistente = compraRepository.findById(id);
		if (compraExistente.isPresent()) {
			Compra compra = compraExistente.get();

			// Atualiza os dados da compra
			compra.setCliente(compraAtualizada.getCliente());
			compra.setCompraData(compraAtualizada.getCompraData());
			compra.setCompraValorTotal(compraAtualizada.getCompraValorTotal());

			// Limpar os itens antigos e salvar os novos
			itemCompraRepository.deleteAll(compra.getItens());
			for (ItemCompra item : compraAtualizada.getItens()) {
				// Buscar o produto existente pelo ID
				Produto produto = produtoRepository.findById(item.getProduto().getProdutoid())
						.orElseThrow(() -> new EntityNotFoundException(
								"Produto não encontrado com ID: " + item.getProduto().getProdutoid()));

				// Definir o produto completo no item
				item.setProduto(produto);
				item.setCompra(compra);
				itemCompraRepository.save(item);
			}

			return compraRepository.save(compra);
		}
		return null; // Ou lançar exceção caso a compra não exista
	}
/*
	public void confirmarPagamento(Integer compraId) {
		// Buscar a compra pelo ID
		Optional<Compra> compraExistente = compraRepository.findById(compraId);

		// Verificar se a compra existe
		if (compraExistente.isPresent()) {
			Compra compra = compraExistente.get();

			// Verificar se o pagamento já foi confirmado
			if (compra.getPagamentoConfirmado()) {
				throw new IllegalStateException("Pagamento já foi confirmado");
			}

			// Confirmar o pagamento
			compra.setPagamentoConfirmado(true);
			compraRepository.save(compra);

			// Atualizar o estoque dos produtos comprados
			for (ItemCompra item : compra.getItens()) {
				Produto produto = item.getProduto();
				int novaQuantidade = produto.getProdutoestoque() - item.getQuantidade();
				if (novaQuantidade < 0) {
					throw new IllegalStateException("Estoque insuficiente para o produto: " + produto.getProdutonome());
				}
				produto.setProdutoestoque(novaQuantidade);
				produtoRepository.save(produto);
			}
		} else {
			// Caso a compra não exista
			throw new IllegalStateException("Compra não encontrada");
		}
	}
	*/
	
	public Compra confirmarPagamento(Integer compraId) {
        // Buscar a compra pelo ID
        Optional<Compra> compraExistente = compraRepository.findById(compraId);

        // Verificar se a compra existe
        if (compraExistente.isPresent()) {
            Compra compra = compraExistente.get();

            // Verificar se o pagamento já foi confirmado
            if (compra.getPagamentoConfirmado()) {
                throw new IllegalStateException("Pagamento já foi confirmado");
            }

            // Confirmar o pagamento
            compra.setPagamentoConfirmado(true);
            compraRepository.save(compra);

            // Atualizar o estoque dos produtos comprados
            for (ItemCompra item : compra.getItens()) {
                Produto produto = item.getProduto();
                int novaQuantidade = produto.getProdutoestoque() - item.getQuantidade();
                if (novaQuantidade < 0) {
                    throw new IllegalStateException("Estoque insuficiente para o produto: " + produto.getProdutonome());
                }
                produto.setProdutoestoque(novaQuantidade);
                produtoRepository.save(produto);
            }

            return compra; // Retornar a compra atualizada
        } else {
            throw new IllegalStateException("Compra não encontrada");
        }
    }
	

	// Deletar compra e seus itens
	public void deleteById(Integer id) {
		Optional<Compra> compraExistente = compraRepository.findById(id);
		if (compraExistente.isPresent()) {
			Compra compra = compraExistente.get();

			// Deletar os itens relacionados antes de deletar a compra
			itemCompraRepository.deleteAll(compra.getItens());

			compraRepository.deleteById(id);
		}
	}

}
