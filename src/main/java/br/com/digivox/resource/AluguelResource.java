package br.com.digivox.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.digivox.model.Aluguel;
import br.com.digivox.repository.AluguelRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class AluguelResource {
	
	@Autowired
	AluguelRepository aluguelRepository;
	
	@PostMapping("/aluguel")
	@ResponseStatus(HttpStatus.CREATED)
	public Aluguel salvar(@RequestBody Aluguel a) {
		a.setStatus("ATIVO");
		return aluguelRepository.save(a);
	}
	
	
	@GetMapping("/aluguel")
	public List<Aluguel> listarTodos(){
		return aluguelRepository.findAll();
	}
	
	@GetMapping("/aluguelativos")
	public List<Aluguel> listarTodosAtivos(){
		String ativo = "ATIVO";
		return aluguelRepository.findByAtivos(ativo);
	}
	
	@GetMapping("/alugueldevolvidos")
	public List<Aluguel> listarTodosDevolvidos(){
		String devolvido = "DEVOLVIDO";
		return aluguelRepository.findByDevolvido(devolvido);
	}
	
	@GetMapping("/aluguel/{id}")
	public Optional<Aluguel> listarById(@PathVariable("id") Integer id){
		return aluguelRepository.findById(id);
	}
	
	@PutMapping("/aluguel")
	public Aluguel atualizar(@RequestBody Aluguel a) {
		return aluguelRepository.save(a);
	}
	
	@GetMapping("/aluguel-devolucao-semana")
	public List<Aluguel> listarDevolucaoSemana(){
		String status = "ATIVO";
		LocalDate dataInicio = LocalDate.now(); 
		LocalDate dataFim = dataInicio.plusDays(7);
		return aluguelRepository.findByDevolucaoSemana(status, dataInicio, dataFim);
	}
	
	@GetMapping("/aluguel-alugueis-semana")
	public List<Aluguel> listarAlugueisSemana(){		
		LocalDate dataInicio = LocalDate.now(); 
		LocalDate dataFim = dataInicio.plusDays(7);
		return aluguelRepository.findByAlugueisSemana(dataInicio, dataFim);
	}
}
