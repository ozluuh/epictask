package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserRepository repository;

	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("users");
		List<User> users = repository.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}

	@GetMapping(value = "/new")
	public String create(User user) {
		return "users-form";
	}

	@PostMapping()
	public String save(@Valid final User user, final BindingResult result) {

		if (result.hasErrors()) {
			return "users-form";
		}

		repository.save(user);
		return "users";
	}

}
