package fr.dta.cotisation.modele;

import java.time.*;

import javax.persistence.*;

import fr.dta.adherent.modele.Adherent;

@Entity
@Table
public class Cotisation {	
	
	@GeneratedValue
	@Id
	private Long id;
	
	@OneToOne
	private Adherent adherent;
	
	@Column
	private LocalDate dateCotisation;
	
	@Column
	private Double montant;

	
	public Cotisation (){
		
	}
	
	public Cotisation(Long id, Adherent adherent, LocalDate dateCotisation, Double montant) {
		this.id = id;
		this.adherent = adherent;
		this.dateCotisation = dateCotisation;
		this.montant = montant;
	}

	public Cotisation(Adherent adherent, LocalDate dateCotisation, Double montant) {
		this.adherent = adherent;
		this.dateCotisation = dateCotisation;
		this.montant = montant;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public LocalDate getDateCotisation() {
		return dateCotisation;
	}

	public void setDateCotisation(LocalDate dateCotisation) {
		this.dateCotisation = dateCotisation;
	}

	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

}
