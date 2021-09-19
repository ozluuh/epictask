package br.com.fiap.epictask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "{validation.task.title.not-blank}")
	private String title;

	@Size(min = 10, message = "{validation.task.description.size-min-or-between}")
	private String description;

	@Min(value = 10, message = "{validation.task.score.min}")
	@Max(value = 500, message = "{validation.task.score.max}")
	private int points;
}
