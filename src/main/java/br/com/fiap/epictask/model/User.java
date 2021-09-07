package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email(message = "Informe um email válido", regexp = "^[A-z0-9]+@[A-z0-9]+\\.[A-z]{2,}\\.?[A-z]+$")
	private String email;

	@Size(min = 8, message = "Mínimo esperado de 8 caracteres")
	private String password;

	@NotBlank(message = "Nome deve ser preenchido")
	private String name;
}
