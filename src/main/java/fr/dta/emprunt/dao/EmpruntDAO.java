package fr.dta.emprunt.dao;

import static fr.dta.databasehelper.DatabaseHelper.*;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.dta.configuration.AbstractJpaRepository;
import fr.dta.emprunt.modele.Emprunt;
import fr.dta.media.modele.Media;

@Repository
public class EmpruntDAO extends AbstractJpaRepository<Emprunt> {
	
	public static void creerEmprunt(Emprunt emprunt) {
		EntityManager em = createEntityManager();
		beginTx(em);
		em.persist(emprunt);
		commitTxAndClose(em);
	}
	
	public static void modifierEmprunt(Emprunt emprunt) {
		EntityManager em = createEntityManager();
		beginTx(em);
		em.merge(emprunt);
		commitTxAndClose(em);
	}
	
	
	public static void supprimerEmprunt(Emprunt emprunt) {
		EntityManager em = createEntityManager();
		beginTx(em);
		Emprunt empruntBD = em.find(Emprunt.class, emprunt.getId());
		em.remove(empruntBD);
		commitTxAndClose(em);
	}
	
	public static Emprunt trouverEmprunt(Long id) {
		EntityManager em = createEntityManager();
		beginTx(em);
		Emprunt emprunt = em.find(Emprunt.class, id);
		commitTxAndClose(em);
		return emprunt;
	}

	@Override
	protected Class<Emprunt> getEntityClass() {
		return Emprunt.class;
	}

}
