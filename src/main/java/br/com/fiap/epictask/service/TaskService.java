package br.com.fiap.epictask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.fiap.epictask.exception.NotAllowedException;
import br.com.fiap.epictask.exception.TaskNotFoundException;
import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepository repo;

	public List<Task> index() {
		return repo.findAllByStatusLessThan(100);
	}

	public Task save(final Task task) {
		return repo.save(task);
	}

	public void hold(final Long id, final Authentication auth) {
		final Optional<Task> optional = repo.findById(id);

		if (optional.isEmpty()) {
			throw new TaskNotFoundException("Tarefa não encontrada");
		}

		final Task task = optional.get();

		if (task.getUser() != null) {
			throw new NotAllowedException("Tarefa já atribuída");
		}

		final User user = (User) auth.getPrincipal();
		task.setUser(user);

		save(task);
	}

	public void release(final Long id, final Authentication auth) {
		final Optional<Task> optional = repo.findById(id);

		if (optional.isEmpty()) {
			throw new TaskNotFoundException("Tarefa não encontrada");
		}

		final Task task = optional.get();
		final User user = (User) auth.getPrincipal();

		if (!task.getUser().equals(user)) {
			throw new NotAllowedException("Essa tarefa não está atribuída para você");
		}

		task.setUser(null);

		save(task);
	}

	public List<Task> done() {
		return repo.findAllByStatusIs(100);
	}

}
