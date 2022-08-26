package main.java.app.Autor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import main.java.app.util.*;

public class AutorDAO extends GenericDAO<Autor> {

    @Override
	public Autor find(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Autor autor = em.find(Autor.class, id);
		tx.commit();
		em.close();
		return autor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Autor> findAll() {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("SELECT d FROM Autor d");
		List<Autor> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}

	@Override
	public void save(Autor autor) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(autor);
		tx.commit();
		em.close();
	}

	@Override
	public void update(Autor autor) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(autor);
		tx.commit();
		em.close();
	}

	@Override
	public void delete(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Autor autor = em.getReference(Autor.class, id);
		tx.begin();
		em.remove(autor);
		tx.commit();
		em.close();
	}
}

