package fr.dta.emprunt.modele;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import fr.dta.adherent.modele.Adherent;

import fr.dta.configuration.IoEntity;

import fr.dta.configuration.View;

import fr.dta.media.modele.Media;

@Entity
public class Emprunt implements IoEntity {

	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JsonView(View.MediaSummary.class)
	private Adherent adherent;
	
	@NotNull
	@ManyToOne
	@JsonView(View.AdherentSummary.class)
	private Media media;
	
	@NotNull
	@JsonView(View.Summary.class)
	private LocalDate dateEmprunt;
	
	@JsonView(View.Summary.class)
	private LocalDate dateRetour;
	
	@NotNull
	@JsonView(View.Summary.class)
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
