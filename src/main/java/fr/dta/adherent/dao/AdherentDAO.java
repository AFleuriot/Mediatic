package fr.dta.adherent.dao;

import static fr.dta.databasehelper.DatabaseHelper.*;

import java.util.List;
import java.util.Map;

import javax.persistence.*;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import fr.dta.adherent.modele.Adherent;
import fr.dta.configuration.AbstractJpaRepository;
import fr.dta.media.modele.Media;
import fr.dta.media.modele.TypeMedia;

@Repository
public class AdherentDAO extends AbstractJpaRepository<Adherent>{
		
		public static void creerAdherent(Adherent adherent){
			EntityManager em = createEntityManager();
			beginTx(em);			
			em.persist(adherent);
			commitTxAndClose(em);
			
		}
		
		public static void majAdherent(Adherent adherent){
			EntityManager em = createEntityManager();
			beginTx(em);
			em.merge(adherent);			
			commitTxAndClose(em);
		}			
		
		public static List<Adherent> rechercheAdherents(){
			EntityManager em = createEntityManager();
			beginTx(em);
			TypedQuery<Adherent> query = em.createQuery("from Adherent", Adherent.class);
			List<Adherent> adherents =  query.getResultList();
			commitTxAndClose(em);
			return adherents;					
		}
		
		public static List<Adherent> rechercheAdherentParIdEtNom(Long id, String nom){
			String req = "from Adherent ";
			if(id != null){
				req += "where id = :id";
			}
			if(nom != null){
				if(id != null){
					req+=" and nom like :nom";
				}
				else{
					req+="where nom like :nom";
				}
			}
			EntityManager em = createEntityManager();
			beginTx(em);
			TypedQuery<Adherent> query = em.createQuery(req, Adherent.class);
			if(id != null){
				query.setParameter("id", id);	
			}
			if(nom != null){
				query.setParameter("nom", "%"+nom+"%");	
			}		
			List<Adherent> resultat = query.getResultList();
			commitTxAndClose(em);			
			return resultat;
		}

		public List<Adherent> rechercheCriteriaAdherent(Map<String,String> criteria){
			Criteria c = getSession().createCriteria(getEntityClass());
			if(!criteria.isEmpty()) {
				if( ( criteria.get("id")!=null && !criteria.get("id").isEmpty() )) {
					c = c.add(Restrictions.idEq(Integer.parseInt(criteria.get("id"))));
				} 
				if( criteria.get("nom_like")!=null && !criteria.get("nom_like").isEmpty()) {
					Criterion c3 = Restrictions.sqlRestriction("concat(this_.nom, ' ', this_.prenom) ilike ?","%"+criteria.get("nom_like")+"%",StringType.INSTANCE);
					Criterion c4 = Restrictions.sqlRestriction("concat(this_.prenom, ' ', this_.nom) ilike ?","%"+criteria.get("nom_like")+"%",StringType.INSTANCE);					
					c.add(Restrictions.or(c3,c4));
				}
			}
			return (List<Adherent>) c.list();
		}
		
		@Override
		protected Class<Adherent> getEntityClass() {
			return Adherent.class;
		}

}
