package br.com.fiap.epictask.controller.api;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ApiUserController {

	private static final String CACHE_NAME = "users";
	private final UserRepository repo;

	@GetMapping
	@Cacheable(CACHE_NAME)
	public Page<User> index(@RequestParam(required = false) final String email,
							@PageableDefault(size = 5) final Pageable pageable) {

		if(email == null) {
			log.debug("Find with Request Param");
			return repo.findAll(pageable);
		}

		log.debug("Find without Request Param");
		return repo.findAllByEmailContains(email, pageable);
	}

	@PostMapping
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public ResponseEntity<User> create(@RequestBody @Valid final User user, final UriComponentsBuilder uriBuilder) {
		user.setName(user.getName().trim());

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
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public ResponseEntity<User> update(@PathVariable final Long id, @RequestBody  @Valid final User user) {
		if(repo.findById(id).isEmpty()) return ResponseEntity.notFound().build();

		user.setId(id);
		var response = repo.save(user);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public ResponseEntity<Object> destroy(@PathVariable final Long id){
	if(repo.findById(id).isEmpty()){
		return ResponseEntity.notFound().build();
	}

		repo.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
