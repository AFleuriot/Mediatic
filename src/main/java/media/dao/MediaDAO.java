package media.dao;

import javax.persistence.EntityManager;
import static databasehelper.DatabaseHelper.*;
import media.modele.Media;


public class MediaDAO {
	
	public static void creerMedia(Media media){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.persist(media);
		commitTxAndClose(em);
	}
	
	public static void modifierMedia(Media media){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.merge(media);
		commitTxAndClose(em);
	}
	
}
