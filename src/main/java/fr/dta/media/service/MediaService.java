package fr.dta.media.service;

import static fr.dta.databasehelper.DatabaseHelper.beginTx;
import static fr.dta.databasehelper.DatabaseHelper.commitTxAndClose;
import static fr.dta.databasehelper.DatabaseHelper.createEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import fr.dta.media.modele.*;

public class MediaService {
	
	
	public static List<Media> rechercheMediaParTitre(String titreRecherche){
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> rech = em.createQuery("SELECT m FROM Media m  WHERE m.titre LIKE :recherche", Media.class);
		rech.setParameter("recherche", "%" + titreRecherche + "%");
		List<Media> resultats = rech.getResultList();
		commitTxAndClose(em);
		return resultats;
	}
	
	public static List<Media> rechercheMediaParAuteur(String auteurRecherche){
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> rech = em.createQuery("SELECT m FROM Media m WHERE m.auteur LIKE :recherche", Media.class);
		rech.setParameter("recherche", 	"%"+auteurRecherche+"%");
		List<Media> resultats = rech.getResultList();
		commitTxAndClose(em);
		return resultats;
	}
	
	public static List<Media> rechercheMediaParType(TypeMedia type){
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> rech = em.createQuery("SELECT m FROM Media m WHERE m.type=:type'" , Media.class);
		rech.setParameter("type", type );
		commitTxAndClose(em);
		return rech.getResultList();
	}
	
	public static Media rechercheMediaParId(Long id) {
		EntityManager em = createEntityManager();
		beginTx(em);
		return em.find(Media.class, id);
	}
	
	
	
	
	// ===================================================================================================================
	public static List<Media> rechercheMedia(String  titreRecherche, String auteurRecherche, TypeMedia type){
			
		EntityManager em = createEntityManager();
		beginTx(em);
		
		String sqlquery = "SELECT m FROM Media m "; 
		boolean firstwhere = true;
		
		if (type != null){
			firstwhere = false;
			sqlquery += "WHERE m.type=:typerecherche " ; 
						
		}
		if (titreRecherche != null){
		
			if(firstwhere){
				sqlquery += "WHERE ";  
				firstwhere = false;
			} else {
				sqlquery += "AND ";
			}
			sqlquery += "m.titre LIKE :titreRecherche " ;
		}
		if (auteurRecherche != null) {
			if(firstwhere){
				sqlquery += "WHERE ";  
				firstwhere = false;
			} else {
				sqlquery += "AND ";
			}
			sqlquery += "m.auteur LIKE :auteurRecherche " ;
		}
		
		TypedQuery<Media> rech = em.createQuery(sqlquery, Media.class);
		
		if (type != null) {
			rech.setParameter("typerecherche", type);
		}
		if (auteurRecherche != "") {
			rech.setParameter("auteurRecherche", "%"+auteurRecherche+"%");
		}
		if (titreRecherche != "") {
			rech.setParameter("titreRecherche",  "%"+titreRecherche+"%");
		}
		List<Media> resultats = rech.getResultList();
		commitTxAndClose(em);
		
		return resultats;
		
		
				
	}
	
	
}
