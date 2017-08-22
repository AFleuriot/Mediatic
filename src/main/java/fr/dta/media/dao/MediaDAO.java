package fr.dta.media.dao;

import static fr.dta.databasehelper.DatabaseHelper.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.adherent.modele.Adherent;
import fr.dta.media.modele.Media;


public class MediaDAO {
	
	public static Media creerMedia(Media media){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.persist(media);
		commitTxAndClose(em);
		return media;
	}
	
	public static Media modifierMedia(Media media){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.merge(media);
		commitTxAndClose(em);
		return media;
	}
	
	
	public static List<Media> rechercheMedias(){
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> query = em.createQuery("from Media", Media.class);
		List<Media> medias =  query.getResultList();
		commitTxAndClose(em);
		return medias;					
	}
	
}
