package br.com.fiap.epictask.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService service;

	private final MessageSource messages;

	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("users");

		List<User> users = service.index();

		modelAndView.addObject("users", users);

		return modelAndView;
	}

	@GetMapping(value = "/new")
	public String create(User user) {
		return "users-form";
	}

	// POST nessa rota pois foi único jeito dele funcionar
	// Apontando no form para a rota principal (/user)
	// ele retorna direto para o formulário de LOGIN
	// sem fazer o POST
	@PostMapping("/register")
	public String save(@Valid final User user, final BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			log.info("hasErrors");
			return "users-form";
		}

		User saved = service.save(user);
		log.info("SavedUser: {}", saved);

		redirect.addFlashAttribute("message", messages.getMessage("validation.user.messages.new-user-created", null,
				LocaleContextHolder.getLocale()));

		return "redirect:/user";
	}

	@GetMapping("/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		User user = service.edit(id);

		ModelAndView modelAndView = new ModelAndView("users-update-form");

		modelAndView.addObject("user", user);

		return modelAndView;
	}

	@PostMapping("/update")
	public String update(@Valid final User user, final BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			log.info("hasErrors");
			return "users-update-form";
		}

		service.save(user);

		redirect.addFlashAttribute("message",
				messages.getMessage("validation.user.messages.user-updated", null, LocaleContextHolder.getLocale()));

		return "redirect:/user";
	}

	@GetMapping("/delete/{id}")
	public String remove(@PathVariable Long id, RedirectAttributes redirect) {
		service.remove(id);

		redirect.addFlashAttribute("message",
				messages.getMessage("validation.user.messages.user-removed", null, LocaleContextHolder.getLocale()));

		return "redirect:/user";
	}

}
