package br.com.digivox.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.digivox.model.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer>{
	
	@Query("select a from Aluguel a where a.status = :ativo ")
	List<Aluguel> findByAtivos(@Param("ativo") String ativo);
	
	@Query("select a from Aluguel a where a.status = :devolvido ")
	List<Aluguel> findByDevolvido(@Param("devolvido") String devolvido);
	
	@Query("select a from Aluguel a where a.status = :status and a.dataDevolucao >= :dataInicio and a.dataDevolucao <= :dataFim")
	List<Aluguel> findByDevolucaoSemana(@Param("status") String status, @Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim );
	
	@Query("select a from Aluguel a where a.dataAluguel >= :dataInicio and a.dataAluguel <= :dataFim")
	List<Aluguel> findByAlugueisSemana(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim );

}
