package main.java.app.Copia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import main.java.app.util.*;

public class CopiaDAO extends GenericDAO<Copia> {

    @Override
	public Copia find(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Copia copia = em.find(Copia.class, id);
		tx.commit();
		em.close();
		return copia;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Copia> findAll() {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("SELECT d FROM Copia d");
		List<Copia> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}

	@Override
	public void save(Copia copia) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(copia);
		tx.commit();
		em.close();
	}

	@Override
	public void update(Copia copia) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(copia);
		tx.commit();
		em.close();
	}

	@Override
	public void delete(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Copia copia = em.getReference(Copia.class, id);
		tx.begin();
		em.remove(copia);
		tx.commit();
		em.close();
	}
}

