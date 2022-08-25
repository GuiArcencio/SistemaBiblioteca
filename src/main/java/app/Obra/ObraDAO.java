package main.java.app.Obra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import main.java.app.util.*;

public class ObraDAO extends GenericDAO<Obra> {

    @Override
	public Obra find(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Obra obra = em.find(Obra.class, id);
		tx.commit();
		em.close();
		return obra;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Obra> findAll() {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("SELECT d FROM Obra d");
		List<Obra> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}

	@Override
	public void save(Obra obra) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(obra);
		tx.commit();
		em.close();
	}

	@Override
	public void update(Obra obra) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(obra);
		tx.commit();
		em.close();
	}

	@Override
	public void delete(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Obra obra = em.getReference(Obra.class, id);
		tx.begin();
		em.remove(obra);
		tx.commit();
		em.close();
	}
}
}
