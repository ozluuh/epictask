package br.com.fiap.epictask.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.epictask.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitleContains(String title);

    Page<Task> findByTitleContains(String title, Pageable pageable);

	// Tarefas iguais a ( = ? )
	List<Task> findAllByStatusIs(int status);

	// Tarefas menor que ( < ? )
	List<Task> findAllByStatusLessThan(int status);
}
