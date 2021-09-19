package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email(message = "{validation.user.email.valid}", regexp = "^[A-z0-9\\.]+@[A-z0-9]+\\.[A-z]{1,}\\.?[A-z]+$")
	private String email;

	@Size(min = 8, message = "{validation.user.password.size-min-or-between}")
	private String password;

	@NotBlank(message = "{validation.user.name.not-blank}")
	@Pattern(message = "{validation.user.name.pattern-matcher}", regexp = "^[a-zA-ZÀ-ü\\s]+$")
	private String name;
}
