package com.manytomany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manytomany.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
