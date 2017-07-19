package media.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import media.modele.Media;

import static databasehelper.DatabaseHelper.*;

import java.util.List;

public class MediaDAO {
	
	public static void creerMedia(Media media){
		EntityManager em = createEntityManager();
		beginTx(em);
		em.merge(media);		
		commitTxAndClose(em);
	}
	
	
	
		
}
