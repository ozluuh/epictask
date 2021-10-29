package br.com.fiap.epictask.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

	private final UserService service;

	@GetMapping
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("rank");

		log.info("GET:/ranking");

		List<User> users = service.ranking();

		modelAndView.addObject("users", users);

		return modelAndView;
	}
}
