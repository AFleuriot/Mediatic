package fr.dta.media.dao;

import static fr.dta.databasehelper.DatabaseHelper.*;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dta.adherent.modele.Adherent;
import fr.dta.configuration.AbstractJpaRepository;
import fr.dta.media.modele.Media;
import fr.dta.media.modele.TypeMedia;

@Repository
public class MediaDAO extends AbstractJpaRepository<Media> {
	
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

	public List<Media> rechercheCriteriaMedia(Map<String,String> criteria){
		Criteria c = getSession().createCriteria(getEntityClass());
		System.out.println(criteria);
		if(!criteria.isEmpty()) {
			if( ( criteria.get("titre_like")!=null && !criteria.get("titre_like").isEmpty() )) {
				c = c.add(Restrictions.like("titre", criteria.get("titre_like"), MatchMode.ANYWHERE));
			} 
			if( criteria.get("auteur_like")!=null && !criteria.get("auteur_like").isEmpty()) {
				c = c.add(Restrictions.like("auteur", criteria.get("auteur_like"), MatchMode.ANYWHERE));
			}
			if(criteria.get("type")!=null && !criteria.get("type").equals("Tous")) {
				c = c.add(Restrictions.eq("type",  TypeMedia.valueOf(criteria.get("type"))));
			}	
		}
		return (List<Media>) c.list();
	}
	
	protected Class<Media> getEntityClass() {
		return Media.class;
	}
	
	
	
}
