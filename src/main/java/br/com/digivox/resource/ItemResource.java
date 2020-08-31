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

import br.com.digivox.model.Item;
import br.com.digivox.repository.ItemRepository;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class ItemResource {
	
	@Autowired
	ItemRepository itemRepository;
	
	@PostMapping("/item")
	@ResponseStatus(HttpStatus.CREATED)
	public Item salvar(@RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	
	@GetMapping("/item")
	public List<Item> listarTodos(){
		return itemRepository.findAll();
	}
	
	@GetMapping("/item/{id}")
	public Optional<Item> listarById(@PathVariable("id") Integer id){
		return itemRepository.findById(id);
	}
	
	@PutMapping("/item")
	public Item atualizar(@RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	@DeleteMapping("/item/{id}")
	public void deletar(@PathVariable("id") Integer id) {
		
		itemRepository.findById(id)
		.map(item -> {
			itemRepository.delete(item);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Item não Encontrado"));
	}

}
