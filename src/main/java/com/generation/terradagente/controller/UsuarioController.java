package com.generation.terradagente.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.terradagente.model.Usuario;
import com.generation.terradagente.model.UsuarioLogin;
import com.generation.terradagente.repository.UsuarioRepository;
import com.generation.terradagente.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*", allowedHeaders="*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/all")
	public ResponseEntity <List<Usuario>> getAll(){
		
		return ResponseEntity.ok(usuarioRepository.findAll());
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * Executa o método autenticarUsuario da classe de serviço para efetuar
	 * o login na api. O método da classe Controladora checa se deu certo e
	 * exibe as mensagens (Response Status) pertinentes. 
	 * 
	 * Caso o login tenha sido bem sucedido, os dados do usuário e o token 
	 * são exibidos.
	 */
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
		return usuarioService.autenticarUsuario(usuarioLogin)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	/**
	 * Executa o método cadastrarUsuario da classe de serviço para criar
	 * um novo usuário na api. O método da classe Controladora checa se 
	 * deu certo e exibe as mensagens (Response Status) pertinentes. 
	 * 
	 * Caso cadastro tenha sido bem sucedido, os dados do usuário são 
	 * exibidos.
	 */
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {

		return usuarioService.cadastrarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	/**
	 * Executa o método atualizarUsuario da classe de serviço para atualizar
	 * os dados de um usuário na api. O método da classe Controladora checa 
	 * se deu certo e exibe as mensagens (Response Status) pertinentes. 
	 * 
	 * Caso a atualização tenha sido bem sucedida, os dados do usuário 
	 * atualizados são exibidos.
	 */
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

}

