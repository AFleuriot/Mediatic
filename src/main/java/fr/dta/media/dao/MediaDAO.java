package fr.dta.media.dao;

import static fr.dta.databasehelper.DatabaseHelper.*;

import javax.persistence.EntityManager;

import fr.dta.media.modele.Media;


public class MediaDAO {
	
	public static void creerMedia(Media media){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.persist(media);
		commitTxAndClose(em);
	}
	
	public static void modifierMedia(Media media){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.merge(media);
		commitTxAndClose(em);
	}
	
}
