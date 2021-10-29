package br.com.fiap.epictask.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.epictask.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);

    Page<User> findAllByEmailContains(String email, Pageable pageable);

    Optional<User> findByEmail(String username);

	List<User> findAllByOrderByRankDesc();
}
