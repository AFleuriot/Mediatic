package emprunt.service;

import static emprunt.dao.EmpruntDAO.*;
import static databasehelper.DatabaseHelper.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import adherent.modele.Adherent;
import emprunt.modele.Emprunt;
import media.modele.Media;

public class EmpruntService {
	
	public static void emprunter(Emprunt emprunt) {
		emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(emprunt.getMedia().getType().getJoursEmpruntables()));
		creerEmprunt(emprunt);
	}
	
	public static void rendre(Emprunt emprunt) {
		emprunt.setDateRetour(LocalDate.now());
		modifierEmprunt(emprunt);
	}
	
	public static List<Media> getMediaEmpruntes(Adherent adherent) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m FROM "+ Emprunt.class.getName() +" e INNER JOIN e.media m WHERE e.id=:id" ,Media.class);
		query.setParameter("id", adherent.getId());
		List<Media> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}
	
	public static List<Media> getMediaPossedes(Adherent adherent) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> query = em.createQuery("SELECT m FROM "+ Emprunt.class.getName() +" e INNER JOIN e.media m WHERE e.id=:id AND e.dateRetour IS NULL" ,Media.class);
		query.setParameter("id", adherent.getId());
		List<Media> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}
	
	public static List<Adherent> getAdherentsEmprunteurs(Media media) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Adherent> query = em.createQuery("SELECT a FROM "+ Emprunt.class.getName() +" e INNER JOIN e.adherent a WHERE e.id=:id" , Adherent.class);
		query.setParameter("id", media.getId());
		List<Adherent> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}
	
	public static List<Adherent> getAdherentsPossedant(Media media) {
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Adherent> query = em.createQuery("SELECT a FROM "+ Emprunt.class.getName() +" e INNER JOIN e.adherent a WHERE e.id=:id AND e.dateRetour IS NULL" , Adherent.class);
		query.setParameter("id", media.getId());
		List<Adherent> result = query.getResultList();
		commitTxAndClose(em);
		return result;
	}

}
