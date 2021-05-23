package br.com.fiap.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.UnaryOperator;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Profile;
import br.com.fiap.util.EntityManagerSingleton;

public class ProfileDAO {

	EntityManager manager = EntityManagerSingleton.getInstance().createEntityManager();

	public void save(Profile profile) {

		manager.getTransaction().begin();
		manager.persist(profile);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<Profile> getAll() {
		TypedQuery<Profile> query = manager.createQuery("SELECT s FROM Profile s", Profile.class);
		List<Profile> resultList = query.getResultList();
		return resultList;
	}

	public Profile findById(Long id) {
		return manager.find(Profile.class, id);
	}

	public void update(Profile profile) {
		manager.getTransaction().begin();
		manager.merge(profile);
		manager.flush();
		manager.getTransaction().commit();
	}

	public void delete(Profile profile) {
		manager.getTransaction().begin();
		manager.remove(profile);
		manager.flush();
		manager.getTransaction().commit();
	}

	
	public static boolean exist(Profile profile) {
		Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		UnaryOperator<Profile> toSession = x -> {
			session.put("profile", x);
			return x;
		};
		
		TypedQuery<Profile> query = EntityManagerSingleton.getInstance().createEntityManager().createQuery(
				"SELECT p FROM Profile p WHERE " + "email=:email AND " + "password=:password", Profile.class)
				.setParameter("email", profile.getEmail())
				.setParameter("password", profile.getPassword());
		try {
			return Optional.ofNullable(query.getSingleResult()).map(toSession).isPresent();
		} catch (Exception e) {
			System.out.println(e);
			session.remove("profile");
		}
		return false;
	}
}
