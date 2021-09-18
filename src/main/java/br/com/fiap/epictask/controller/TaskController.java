package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

	private final MessageSource message;

	private final TaskRepository repository;

	@GetMapping
	public ModelAndView index() {
		// View name
		ModelAndView modelAndView = new ModelAndView("tasks");
		// View object
		List<Task> tasks = repository.findAll();
		// Attribute
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}

	@GetMapping("/new")
	public String create(Task task) {
		return "tasks-form";
	}

	@PostMapping
	public String save(@Valid final Task task, BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "tasks-form";
		}

		repository.save(task);
		redirect.addFlashAttribute("message", message.getMessage("{validation.task.messages.new-task-created}", null, LocaleContextHolder.getLocale()));

		return "redirect:/task";
	}
}
