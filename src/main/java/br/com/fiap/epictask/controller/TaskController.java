package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.service.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

	private final MessageSource message;

	private final TaskService service;

	@GetMapping
	public ModelAndView index() {
		// View name
		final ModelAndView modelAndView = new ModelAndView("tasks");
		// View object
		final List<Task> tasks = service.index();
		// Attribute
		modelAndView.addObject("tasks", tasks);
		return modelAndView;
	}

	@GetMapping("/new")
	public String create(final Task task) {
		return "tasks-form";
	}

	@PostMapping
	public String save(@Valid final Task task, final BindingResult result, final RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "tasks-form";
		}

		service.save(task);
		redirect.addFlashAttribute("message", message.getMessage("{validation.task.messages.new-task-created}", null, LocaleContextHolder.getLocale()));

		return "redirect:/task";
	}

	@GetMapping("/hold/{id}")
	public String hold(@PathVariable final Long id, final Authentication auth) {
		service.hold(id, auth);

		return "redirect:/task";
	}

	@GetMapping("/release/{id}")
	public String release(@PathVariable final Long id, final Authentication auth) {
		service.release(id, auth);

		return "redirect:/task";
	}

	@GetMapping("/done")
	public ModelAndView done() {
		List<Task> tasksDone = service.done();

		ModelAndView modelAndView = new ModelAndView("tasks");

		modelAndView.addObject("tasks", tasksDone);

		return modelAndView;
	}
}
