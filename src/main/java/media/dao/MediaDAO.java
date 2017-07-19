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
	
}
