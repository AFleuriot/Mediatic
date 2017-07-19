package adherent.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import adherent.modele.Adherent;

public class AdherentDAO {
		public static EntityManager em = null;
		
		public static void creerAdherent(Adherent adherent){
			em.persist(adherent);
		}
		
		public static void majAdherent(Adherent adherent){
			em.merge(adherent);
		}
		
		public static List<Adherent> rechercheAdherents(){
			TypedQuery<Adherent> query = em.createQuery("from Adherent", Adherent.class);
			return query.getResultList();			
		}
		
		public static List<Adherent> rechercheAdherentParIdEtNom(Long id, String nom){
			String req = "from Adherent ";
			int etape = 0;
			if(id != null){
				req += "where id = :id";
				etape++;
			}
			if(nom != null){
				if(etape==1){
					req+=" and nom like :nom";
				}
				else{
					req+="where nom like :nom";
				}
			}
			
			TypedQuery<Adherent> query = em.createQuery(req, Adherent.class);
			if(id != null){
				query.setParameter("id", id);	
			}
			if(nom != null){
				query.setParameter("nom", "%"+nom+"%");	
			}			
			return query.getResultList();
		}
}
