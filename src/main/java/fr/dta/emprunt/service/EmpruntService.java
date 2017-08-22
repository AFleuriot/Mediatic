package fr.dta.emprunt.service;

import static fr.dta.emprunt.dao.EmpruntDAO.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.dta.emprunt.modele.Emprunt;
import fr.dta.media.dao.MediaDAO;

@Service
public class EmpruntService {
	/*
	 * 
	 * =================== Ancienne méthode ==================== Hibernate sans Spring
	 * 
	public static void emprunter(Emprunt emprunt) {
		emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(emprunt.getMedia().getType().getJoursEmpruntables()));
		emprunt.getMedia().setEmpruntactuel(emprunt);
		creerEmprunt(emprunt);
		MediaDAO.modifierMedia(emprunt.getMedia());
	}
	
	public static void rendre(Emprunt emprunt) {
		emprunt.setDateRetour(LocalDate.now());
		emprunt.getMedia().setEmpruntactuel(null);
		modifierEmprunt(emprunt);
		MediaDAO.modifierMedia(emprunt.getMedia());
	}
	
	public static List<Media> getMediaEmpruntes(Adherent adherent) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m FROM "+ Emprunt.class.getName() +" e INNER JOIN FETCH e.media m WHERE e.adherent=:id" ,Media.class);
		query.setParameter("id", adherent);
		List<Media> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}
	
	public static List<Media> getMediaPossedes(Adherent adherent) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m FROM "+ Emprunt.class.getName() +" e INNER JOIN FETCH e.media m WHERE e.adherent=:id AND e.dateRetour IS NULL" ,Media.class);
		query.setParameter("id", adherent);
		List<Media> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}
	
	public static List<Adherent> getAdherentsEmprunteurs(Media media) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Adherent> query = em.createQuery("SELECT a FROM "+ Emprunt.class.getName() +" e INNER JOIN FETCH e.adherent a WHERE e.media=:id" , Adherent.class);
		query.setParameter("id", media);
		List<Adherent> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}
	
	public static List<Adherent> getAdherentsPossedant(Media media) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Adherent> query = em.createQuery("SELECT a FROM "+ Emprunt.class.getName() +" e INNER JOIN FETCH e.adherent a WHERE e.media=:id AND e.dateRetour IS NULL" , Adherent.class);
		query.setParameter("id", media);
		List<Adherent> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}*/

}
