package com.jatis.showcase.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "SC_MST_USER")
@ToString
public class UserEntity extends BaseEntity implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(length = 100)
	@Size(max = 100, min = 3)
	private String email;
	
	@Column(length = 200)
	@NotEmpty
	private String password;
	
	private LocalDate birthDate;
	
	@Column(length = 200)
	@Size(max = 200, min = 1)
	private String authorityList;
	
	@Column(length = 20)
	@Length(max = 20)
	private String phone;

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new LinkedList<>();
		for (String auth : StringUtils.commaDelimitedListToSet(authorityList)) {
			authorities.add(new SimpleGrantedAuthority(auth));
		}
		return authorities;
	}

}
