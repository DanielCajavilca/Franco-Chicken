package com.FChicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FChicken.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
