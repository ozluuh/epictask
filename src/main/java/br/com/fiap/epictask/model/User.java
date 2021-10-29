package br.com.fiap.epictask.model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
public class User implements UserDetails {

	private static final String AVATAR_GITHUB_BASE_URL = "https://avatars.githubusercontent.com/";

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

	@NotBlank(message = "{validation.user.github.not-blank}")
	private String githubUser;

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Task> tasks;

	private int rank;

	public String getAvatarUrl() {
		return AVATAR_GITHUB_BASE_URL + this.githubUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



}
