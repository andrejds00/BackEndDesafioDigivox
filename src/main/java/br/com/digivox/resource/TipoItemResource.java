package br.com.digivox.resource;

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

import br.com.digivox.model.TipoItem;
import br.com.digivox.repository.TipoItemRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class TipoItemResource {
	
	@Autowired
	TipoItemRepository tipoItemRepository;
	
	@PostMapping("/tipoitem")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoItem salvar(@RequestBody TipoItem tipo) {
		return tipoItemRepository.save(tipo);
	}
	
	
	@GetMapping("/tipoitem")
	public List<TipoItem> listarTodos(){
		return tipoItemRepository.findAll();
	}
	
	@GetMapping("/tipoitem/{id}")
	public Optional<TipoItem> listarById(@PathVariable("id") Integer id){
		return tipoItemRepository.findById(id);
	}
	
	
	@PutMapping("/tipoitem")
	public TipoItem atualizar(@RequestBody TipoItem tipo) {
		return tipoItemRepository.save(tipo);
	}
	
	@DeleteMapping("/tipoitem/{id}")
	public void deletar(@PathVariable("id") Integer id) {
		
		tipoItemRepository.findById(id)
		.map(tipoItem -> {
			tipoItemRepository.delete(tipoItem);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tipo Item não Encontrado"));
	}

}
