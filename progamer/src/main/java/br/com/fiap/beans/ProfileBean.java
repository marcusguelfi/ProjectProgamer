package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.ProfileDAO;

import br.com.fiap.model.Profile;

@Named
@RequestScoped
public class ProfileBean {

	private Profile profile = new Profile();

	public void save() {
		new ProfileDAO().save(this.profile);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Perfil cadastrado com sucesso !"));
	}

	public List<Profile> getProfiles() {
		return new ProfileDAO().getAll();
	}

	private String loginError() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(false);
		context.getExternalContext().getSessionMap().remove("profile");
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login inválido", "erro"));
		return "login?faces-redirect=true";
	}

	public String login() {
		return (ProfileDAO.exist(this.profile)) ? "index?faces-redirect=true" : loginError();
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(false);
		context.getExternalContext().getSessionMap().remove("profile");
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout do Sistema", "erro"));
		return "login?faces-redirect=true";
	}

	public void executar() {
		System.out.println("Acionando o Bean");
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
