package br.com.fiap.epictask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.epictask.model.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	// private final User user;

	@RequestMapping("/login")
	public String index() {
		return "login";
	}
}
