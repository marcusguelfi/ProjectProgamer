package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Profile;
import br.com.fiap.model.Setup;

@Named
@RequestScoped
public class SetupBean {

	private Setup setup = new Setup();
	
	Profile profile = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("profile");
	
	public void save() {
		setup.setProfile(profile);
		new SetupDAO().save(this.setup);
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Setup Cadastrado com Sucesso !"));
	}

	public List<Setup> getSetups(){
		return new SetupDAO().getAll();
	}
	
	public List<Setup> getSetupsByProfile(){
		return new SetupDAO().findByProfile(profile.getId());
	}

	
	public void executar() {
		System.out.println("Acionando o Bean");
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}

}
