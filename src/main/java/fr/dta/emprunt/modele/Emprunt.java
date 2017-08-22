package fr.dta.emprunt.modele;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import fr.dta.adherent.modele.Adherent;
import fr.dta.media.modele.Media;

@Entity
public class Emprunt {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@ManyToOne
	private Adherent adherent;
	
	@NotNull
	@ManyToOne
	private Media media;
	
	@NotNull
	private LocalDate dateEmprunt;
	
	private LocalDate dateRetour;
	
	@NotNull
	private LocalDate dateRetourPrevue;
	
	public Emprunt() {
		
	}
	
	public Emprunt(Adherent adherent, Media media, LocalDate dateEmprunt) {
		this.adherent = adherent;
		this.media = media;
		this.dateEmprunt = dateEmprunt;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
