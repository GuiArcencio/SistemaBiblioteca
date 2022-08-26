package main.java.app.CategoriaObra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import main.java.app.util.*;

public class CategoriaObraDAO extends GenericDAO<CategoriaObra> {

    @Override
	public CategoriaObra find(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		CategoriaObra categoriaObra = em.find(CategoriaObra.class, id);
		tx.commit();
		em.close();
		return categoriaObra;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaObra> findAll() {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Query q = em.createQuery("SELECT d FROM CategoriaObra d");
		List<CategoriaObra> lista = q.getResultList();
		tx.commit();
		em.close();
		return lista;
	}

	@Override
	public void save(CategoriaObra categoriaObra) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(categoriaObra);
		tx.commit();
		em.close();
	}

	@Override
	public void update(CategoriaObra categoriaObra) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(categoriaObra);
		tx.commit();
		em.close();
	}

	@Override
	public void delete(Long id) {
		EntityManager em = this.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		CategoriaObra categoriaObra = em.getReference(CategoriaObra.class, id);
		tx.begin();
		em.remove(categoriaObra);
		tx.commit();
		em.close();
	}
}

