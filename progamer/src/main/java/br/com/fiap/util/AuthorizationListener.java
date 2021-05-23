package br.com.fiap.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.fiap.model.Profile;

public class AuthorizationListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String viewId = context.getViewRoot().getViewId();
		if (viewId.equals("/login.xhtml")) return;
		
		Profile profile = (Profile) context.getExternalContext().getSessionMap().get("profile");
		if (profile != null) return;
		
		NavigationHandler navigator = context.getApplication().getNavigationHandler();
		navigator.handleNavigation(context, null, "login?faces-redirect=true");
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
