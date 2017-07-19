package media.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import media.modele.Media;

import static databasehelper.DatabaseHelper.*;

import java.util.List;

public class MediaDAO {
	
	
	
	
	public static List<Media> rechercheMediaParTitre(String titreRecherche){
		EntityManager em = createEntityManager();
		beginTx(em);
		TypedQuery<Media> rech = em.createQuery("SELECT m FROM media m AS med WHERE med.titre LIKE '%titreRecherche'", Media.class);
		commitTxAndClose(em);
		return rech.getResultList();
	}
	
	
	// FROM media WHERE media.titre LIKE '%recherche%' 
	
	
}
