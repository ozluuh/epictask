package br.com.fiap.epictask.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	private final TaskRepository repo;

	public List<Task> index(){
		return repo.findAll();
	}

	public Task save(Task task){
		return repo.save(task);
	}

	public void hold(){}

	public void release(){}

}
