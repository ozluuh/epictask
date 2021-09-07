package br.com.fiap.epictask.controller.api;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ApiUserController {

	private final UserRepository repo;

	@GetMapping
	public Page<User> index(@RequestParam(required = false) final String email,
							@PageableDefault(size = 5) final Pageable pageable) {

		if(email == null) repo.findAll(pageable);

		return repo.findAllByEmailContains(email, pageable);
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody final User user, final UriComponentsBuilder uriBuilder) {
		repo.save(user);

		final URI uri = uriBuilder
							.path("/api/user/{id}")
							.buildAndExpand(user.getId())
							.toUri();

		return ResponseEntity.created(uri).body(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> show(@PathVariable final Long id){
		return ResponseEntity.of(repo.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable final Long id, @RequestBody final User user) {
		if(repo.findById(id).isEmpty()) return ResponseEntity.notFound().build();

		repo.save(user);

		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> destroy(@PathVariable final Long id){
		if(repo.findById(id).isEmpty()) return ResponseEntity.notFound().build();

		repo.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
