package com.generation.terradagente.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.terradagente.model.Categoria;
import com.generation.terradagente.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List <Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
		
	}
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
		return categoriaRepository.findById(categoria.getId())
				.map(resposta -> ResponseEntity.ok().body(categoriaRepository.save(categoria)))
				.orElse(ResponseEntity.notFound().build());
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
		return categoriaRepository.findById(id)
				.map(resposta -> {
					categoriaRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
}
