package fr.dta.emprunt.dao;

import static fr.dta.databasehelper.DatabaseHelper.*;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dta.adherent.modele.Adherent;
import fr.dta.configuration.AbstractJpaRepository;
import fr.dta.emprunt.modele.Emprunt;
import fr.dta.media.modele.Media;
import fr.dta.media.modele.TypeMedia;

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
	
	public List<Emprunt> rechercheEmpruntParAdherent(Adherent adherent){
		Criteria c = getSession().createCriteria(getEntityClass());
		c = c.add(Restrictions.eq("adherent", adherent))
				.add(Restrictions.isNull("dateRetour"));
		
		List<Emprunt> liste = (List<Emprunt>) c.list();		
		return liste;
	}
	
	public List<Emprunt> rechercheEmpruntParMedia(Media media){
		Criteria c = getSession().createCriteria(getEntityClass());
		c = c.add(Restrictions.eq("media", media));
		List<Emprunt> liste = (List<Emprunt>) c.list();		
		return liste;
	}

}
