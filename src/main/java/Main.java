import adherent.dao.AdherentDAO;
import adherent.modele.Adherent;
import cotisation.dao.CotisationDAO;
import cotisation.modele.Cotisation;

import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		Adherent adherent1 = new Adherent("Thomas", "Suzanne", "patate@gmail.com", LocalDate.of(1989, 05, 21));
		AdherentDAO.creerAdherent(adherent1);
		
		Cotisation cotisation1 = new Cotisation(adherent1, LocalDate.of(2017, 07, 19), 50d);
		CotisationDAO.creerCotisation(cotisation1);
		
		

	}

}
