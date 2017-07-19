package emprunt.modele;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import media.modele.Media;

@Entity
public class Emprunt {

	@ManyToOne
	private Adherent adherent;
	
	@ManyToOne
	private Media media;
	
	@NotBlank
	private LocalDate date_emprunt;
	
	private LocalDate date_retour;
	
	@NotBlank
	private LocalDate date_retour_prevue;
	
	public Emprunt() {
		
	}
	
	public Emprunt(Adherent adherent, Media media, LocalDate date_emprunt) {
		this.adherent = adherent;
		this.media = media;
		this.date_emprunt = date_emprunt;
		this.date_retour_prevue = date_emprunt.plusDays(media.getType().getJoursEmpruntables());
	}
	
}
