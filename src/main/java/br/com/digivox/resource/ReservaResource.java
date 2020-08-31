package br.com.digivox.resource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.digivox.model.Reserva;
import br.com.digivox.repository.ReservaRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class ReservaResource {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@PostMapping("/reserva")
	@ResponseStatus(HttpStatus.CREATED)
	public Reserva salvar(@RequestBody Reserva r) {	
		//Setar a Data de Cadastro Default
	    r.setDataCadastro(LocalDate.now());
		return reservaRepository.save(r);
	}
	
	
	@GetMapping("/reserva")
	public List<Reserva> listarTodos(){
		return reservaRepository.findAll();
	}
	
		
	@GetMapping("/reserva/{id}")
	public Optional<Reserva> listarById(@PathVariable("id") Integer id){
		return reservaRepository.findById(id);
	}
	
	@PutMapping("/reserva")
	public Reserva atualizar(@RequestBody Reserva r) {
		return reservaRepository.save(r);
	}
	
	@DeleteMapping("/reserva/{id}")
	public void deletar(@PathVariable("id") Integer id) {
		
		reservaRepository.findById(id)
		.map(reserva -> {
			reservaRepository.delete(reserva);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reserva não Encontrada"));
	}
	
	
}
