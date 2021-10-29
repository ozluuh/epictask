package br.com.fiap.epictask.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repo;

	public List<User> index() {
		return repo.findAll();
	}

	public User save(User user) {
		String encodedPassword = AuthenticationService.getPasswordEncoder().encode(user.getPassword());
		user.setPassword(encodedPassword);

		return repo.saveAndFlush(user);
	}

    public User edit(Long id) {
        return repo.findById(id).orElse(null);
    }

	public void remove(Long id){
		repo.deleteById(id);
	}
}
