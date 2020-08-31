package br.com.digivox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digivox.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
