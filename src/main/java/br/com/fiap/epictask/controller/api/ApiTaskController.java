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

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class ApiTaskController {

	public static final String CACHE_NAME = "tasks";

	private final TaskRepository repo;


	@GetMapping
	@Cacheable(CACHE_NAME)
	public Page<Task> index(
			@RequestParam(required = false) final String title,
			@PageableDefault(size = 5) final Pageable pageable
	) {
		if (title == null) {
			return repo.findAll(pageable);
		}

		return repo.findByTitleContains(title, pageable);
	}

	@PostMapping
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public ResponseEntity<Task> create(
			@RequestBody @Valid final Task task,
			final UriComponentsBuilder uriBuilder
	) {
		repo.save(task);

		final URI uri = uriBuilder
							.path("/api/task/{id}")
							.buildAndExpand(task.getId())
							.toUri();

		return ResponseEntity.created(uri).body(task);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> show(@PathVariable final Long id) {
		return ResponseEntity.of(repo.findById(id));
	}

	@PutMapping("/{id}")
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public ResponseEntity<Task> update(
			@PathVariable final Long id,
			@RequestBody @Valid final Task newTask
	) {
		if (repo.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		newTask.setId(id);
		var response = repo.save(newTask);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public ResponseEntity<Object> destroy(@PathVariable final Long id) {
		if (repo.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		repo.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
