package br.com.fiap.epictask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.epictask.model.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = { "/", "/login" })
@RequiredArgsConstructor
public class LoginController {

	@GetMapping("/login")
	public String index(final User user) {
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "home";
	}
}
