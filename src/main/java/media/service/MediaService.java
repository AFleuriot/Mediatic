package media.service;

import static databasehelper.DatabaseHelper.beginTx;
import static databasehelper.DatabaseHelper.commitTxAndClose;
import static databasehelper.DatabaseHelper.createEntityManager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import media.modele.*;

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
		TypedQuery<Media> rech = em.createQuery("SELECT m FROM Media m AS med WHERE med.type=:type'" , Media.class);
		rech.setParameter("type", type );
		commitTxAndClose(em);
		return rech.getResultList();
	}
	
	public static List<Media> rechercheMedia(String titreRecherche, String auteurRecherche, TypeMedia type){
		if (titreRecherche == "" && auteurRecherche == "") {
			rechercheMediaParType(type);
		}
		if (auteurRecherche == "" && type == null) {
			rechercheMediaParAuteur(titreRecherche);
		}
		if (titreRecherche == "" && type == null) {
			rechercheMediaParAuteur(auteurRecherche);
		}
		
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> rech = em.createQuery("SELECT m FROM media m AS med WHERE med.type=:type'" , Media.class);
		rech.setParameter("type", type );
		commitTxAndClose(em);
		return rech.getResultList();
		
		
		
		
		
	}
	
	// méthode find param id renvoie media avec inner join 
	
}
