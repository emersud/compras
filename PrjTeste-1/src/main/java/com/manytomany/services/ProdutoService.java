package com.manytomany.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manytomany.entities.Produto;
import com.manytomany.repositories.ProdutoRepository;
@Service
public class ProdutoService {
	@Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    // Salvar novo produto
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualizar produto
    public Produto update(Long id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setProdutonome(produtoAtualizado.getProdutonome());
            produto.setProdutotipo(produtoAtualizado.getProdutotipo());
            produto.setProdutopreco(produtoAtualizado.getProdutopreco());
            produto.setProdutoestoque(produtoAtualizado.getProdutoestoque());
            return produtoRepository.save(produto);
        }
        return null; // Ou lançar exceção caso o produto não exista
    }

    // Deletar produto
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

}
