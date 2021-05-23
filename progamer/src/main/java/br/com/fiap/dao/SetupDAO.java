package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

//import br.com.fiap.model.Profile;
import br.com.fiap.model.Setup;
import br.com.fiap.util.EntityManagerSingleton;

public class SetupDAO {

	EntityManager manager = EntityManagerSingleton.getInstance().createEntityManager();

	public void save(Setup setup) {

		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();

		manager.close();
	}

	public List<Setup> getAll() {
		String jpql = "SELECT s FROM Setup s";
		TypedQuery<Setup> query = manager.createQuery(jpql, Setup.class);
		List<Setup> resultList = query.getResultList();
		return resultList;
	}

	public Setup findById(Long id) {
		return manager.find(Setup.class, id);
	}
	
	public List<Setup> findByProfile(Long profileId) {
		String jpql = "SELECT s FROM Setup s where s.profile.id = :profileId";
		TypedQuery<Setup> query = manager.createQuery(jpql, Setup.class).setParameter("profileId", profileId);
		List<Setup> resultList = query.getResultList();
		return resultList;
	}

	public void update(Setup setup) {
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.flush();
		manager.getTransaction().commit();
	}
	
	public void delete(Setup setup) {
		manager.getTransaction().begin();
		manager.remove(setup);
		manager.flush();
		manager.getTransaction().commit();
	}
}