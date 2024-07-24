package me.dio.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.dio.domain.model.User;
import me.dio.service.impl.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	private final UserService userService; //Usamos a interface, não a implementação. Por isso os métodos não são expostos para além do necessário.
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// Nesse caso, podemos criar DTOs específicas para expor, de maneira a filtrar somente os dados que deverão ser expostos na API.
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Long id) {
		var user = userService.findbyId(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User userToCreate) {
		var userCreated = userService.create(userToCreate);
		// location do usuário criado (é uma boa prática retornar)
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userCreated.getId())
				.toUri();
		return ResponseEntity.created(location).body(userCreated);
	}
	
 }
