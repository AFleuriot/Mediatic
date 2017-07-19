import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import adherent.modele.Adherent;
import emprunt.modele.Emprunt;
import media.modele.Media;
import media.modele.TypeMedia;

public class Main {

	public static void main(String[] args) {
		
		EntityManager em = Persistence.createEntityManagerFactory("unit").createEntityManager();
		em.getTransaction().begin();
		Media media = new Media("Essais", "Michel de Montaigne", TypeMedia.Livre);
		em.persist(media);
		Adherent adherent = new Adherent("Truc", "Machin");
		em.persist(adherent);
		Emprunt emprunt = new Emprunt(adherent, media, LocalDate.now());
		em.persist(emprunt);
		em.getTransaction().commit();
	    em.close();
		
	}
	
}
