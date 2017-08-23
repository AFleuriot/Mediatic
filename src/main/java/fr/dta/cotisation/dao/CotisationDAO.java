package fr.dta.cotisation.dao;

import static fr.dta.databasehelper.DatabaseHelper.beginTx;
import static fr.dta.databasehelper.DatabaseHelper.commitTxAndClose;
import static fr.dta.databasehelper.DatabaseHelper.createEntityManager;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import fr.dta.configuration.AbstractJpaRepository;
import fr.dta.cotisation.modele.Cotisation;

@Repository
public class CotisationDAO extends AbstractJpaRepository<Cotisation>{

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

	@Override
	protected Class<Cotisation> getEntityClass() {
		return Cotisation.class;
	}			
}
