package emprunt.modele;

import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import adherent.modele.Adherent;
import media.modele.Media;

@Entity
public class Emprunt {

	@ManyToOne
	private Adherent adherent;
	
	@ManyToOne
	private Media media;
	
	@NotBlank
	private LocalDate dateEmprunt;
	
	private LocalDate dateRetour;
	
	@NotBlank
	private LocalDate dateRetourPrevue;
	
	public Emprunt() {
		
	}
	
	public Emprunt(Adherent adherent, Media media, LocalDate dateEmprunt) {
		this.adherent = adherent;
		this.media = media;
		this.dateEmprunt = dateEmprunt;
		this.dateRetourPrevue = dateEmprunt.plusDays(media.getType().getJoursEmpruntables());
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public LocalDate getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(LocalDate dateRetour) {
		this.dateRetour = dateRetour;
	}

	public LocalDate getDateRetourPrevue() {
		return dateRetourPrevue;
	}

	public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
		this.dateRetourPrevue = dateRetourPrevue;
	}
	
}
