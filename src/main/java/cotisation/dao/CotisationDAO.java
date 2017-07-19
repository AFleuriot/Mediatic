package cotisation.dao;

import static databasehelper.DatabaseHelper.beginTx;
import static databasehelper.DatabaseHelper.commitTxAndClose;
import static databasehelper.DatabaseHelper.createEntityManager;

import javax.persistence.EntityManager;

import cotisation.modele.Cotisation;

public class CotisationDAO {

	public static void creerCotisation(Cotisation cotisation){
		EntityManager em = createEntityManager();
		beginTx(em);			
		em.persist(cotisation);
		commitTxAndClose(em);	
	}
	
	public static void majCotisation(Cotisation cotisation){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.merge(cotisation);			
		commitTxAndClose(em);
	}			
}
