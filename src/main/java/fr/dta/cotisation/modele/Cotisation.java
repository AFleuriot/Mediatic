package fr.dta.cotisation.modele;

import java.time.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.dta.adherent.modele.Adherent;
import fr.dta.configuration.IoEntity;
import fr.dta.configuration.View;
import fr.dta.databasehelper.LocalDateDeserializer;
import fr.dta.databasehelper.LocalDateSerializer;

@Entity
@Table
public class Cotisation implements IoEntity{	
	
	@GeneratedValue
	@Id
	@JsonView(View.Summary.class)
	private Integer id;
	
	@OneToOne
	private Adherent adherent;
	
	@Column
	@JsonSerialize(using=LocalDateSerializer.class)
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonView(View.Summary.class)
	private LocalDate dateCotisation;
	
	@Column
	@JsonView(View.Summary.class)
	private Double montant;

	
	public Cotisation (){
		
	}
	
	public Cotisation(Integer id, Adherent adherent, LocalDate dateCotisation, Double montant) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
