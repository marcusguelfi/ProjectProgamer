package br.com.fiap.model;

import java.util.Date;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "prf", sequenceName = "SQ_T_PROFILE_PROGAMER", allocationSize = 1)
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prf")
	private	Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "birthdate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@JsonbTransient
	private String password;
	
	@OneToMany(mappedBy = "profile")
	private List<Setup> setups;

	public Profile() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public List<Setup> getSetups() {
		return setups;
	}

	public void setSetups(List<Setup> setups) {
		this.setups = setups;
	}

	@Override
	public String toString() {
		return "Profile [name=" + name + ", birthDate=" + birthDate + ", email=" + email + "]";
	}

}
