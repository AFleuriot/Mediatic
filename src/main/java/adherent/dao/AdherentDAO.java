package adherent.dao;

import java.util.List;

import javax.persistence.*;

import adherent.modele.Adherent;
import static databasehelper.DatabaseHelper.*;

public class AdherentDAO {
		
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
			TypedQuery<Adherent> query = em.createQuery("from Adherent", Adherent.class);
			return query.getResultList();			
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

}
