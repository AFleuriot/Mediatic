package fr.dta.adherent.dao;

import static fr.dta.databasehelper.DatabaseHelper.*;

import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Repository;

import fr.dta.adherent.modele.Adherent;
import fr.dta.configuration.AbstractJpaRepository;

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

		@Override
		protected Class<Adherent> getEntityClass() {
			return Adherent.class;
		}

}
