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

import br.com.digivox.model.Cliente;
import br.com.digivox.repository.ClienteRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClienteResource {
	
	@Autowired
	ClienteRepository clienteRepository;

	
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		    //Setar a Data de Cadastro Default
		    cliente.setDataCadastro(LocalDate.now());
		return clienteRepository.save(cliente);
	}
	
	
	@GetMapping("/cliente")
	public List<Cliente> listarTodos(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	public Optional<Cliente> listarUnico(@PathVariable("id") Integer id){
		return clienteRepository.findById(id);
	}
	
	@PutMapping("/cliente")
	public Cliente atualizar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("/cliente/{id}")
	public void deletar(@PathVariable("id") Integer id) {
		
		clienteRepository.findById(id)
		.map(cliente -> {
			clienteRepository.delete(cliente);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não Encontrado"));
	}
}
