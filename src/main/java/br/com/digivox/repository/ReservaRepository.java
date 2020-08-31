package br.com.digivox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digivox.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

}
