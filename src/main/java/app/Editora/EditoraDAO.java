package main.java.app.Editora;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import main.java.app.util.*;

public class EditoraDAO extends GenericDAO<Editora> {

    @Override
	public Editora find(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Editora editora = em.find(Editora.class, id);
		tx.commit();
		em.close();
		return editora;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Editora> findAll() {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("SELECT d FROM Editora d");
		List<Editora> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}

	@Override
	public void save(Editora editora) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(editora);
		tx.commit();
		em.close();
	}

	@Override
	public void update(Editora editora) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(editora);
		tx.commit();
		em.close();
	}

	@Override
	public void delete(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Editora editora = em.getReference(Editora.class, id);
		tx.begin();
		em.remove(editora);
		tx.commit();
		em.close();
	}
}

