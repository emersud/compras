package com.manytomany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
