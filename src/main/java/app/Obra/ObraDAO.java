package main.java.app.Obra;

import java.util.List;
import com.google.common.collect.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import main.java.app.util.*;

public class ObraDAO extends GenericDAO<Obra> {

	private final List<Obra> obras = ImmutableList.of(
            new Obra("Moby Dick", 9789),
            new Obra("A Christmas Carol", 9780),
            new Obra("Pride and Prejudice", 9781),
            new Obra("The Fellowship of The Ring",  0007),
            new Obra("Harry Potter", 0747),
            new Obra("War and Peace",  9785),
            new Obra("Don Quixote", 9784),
            new Obra("Ulysses",  9783),
            new Obra("The Great Gatsby", 9782),
            new Obra("One Hundred Years of Solitude",  1041),
            new Obra("The adventures of Huckleberry Finn", 1296),
            new Obra("Alice In Wonderland", 1491)
    );

	public Obra getRandomObra() {
        return obras.get(new Random().nextInt(obras.size()));
    }

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

