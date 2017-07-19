package emprunt.dao;

import static databasehelper.DatabaseHelper.*;

import javax.persistence.EntityManager;

import emprunt.modele.Emprunt;

public class EmpruntDAO {
	
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

}
