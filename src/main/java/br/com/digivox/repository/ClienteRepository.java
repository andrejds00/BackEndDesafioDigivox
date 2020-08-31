package br.com.digivox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digivox.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
