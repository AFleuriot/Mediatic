package fr.dta.media.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import fr.dta.configuration.IoEntity;
import fr.dta.configuration.View;
import fr.dta.emprunt.modele.Emprunt;

@Entity
public class Media implements IoEntity {

	@Id
	@GeneratedValue
	@JsonView(View.Summary.class)
	private Long id;
	
	@NotBlank
	@JsonView(View.Summary.class)
	private String titre;
	
	@NotBlank
	@JsonView(View.Summary.class)
	private String auteur;
	
	@NotNull
	@JsonView(View.Summary.class)
	private TypeMedia type;
	
	@OneToMany(mappedBy = "media")
	private List<Emprunt> emprunts = new ArrayList<>();
	
	@OneToOne
	@JsonView(View.MediaSummary.class)
	private Emprunt empruntactuel;
	

	
	public Media() {
		
	}
	
	public Media(String titre, String auteur, TypeMedia type) {
		this.titre = titre;
		this.setAuteur(auteur);
		this.setType(type);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public TypeMedia getType() {
		return type;
	}

	public void setType(TypeMedia type) {
		this.type = type;
	}

	public List<Emprunt> getEmprunts() {
		return emprunts;
	}

	public void setEmprunts(List<Emprunt> emprunts) {
		this.emprunts = emprunts;
	}

	public Emprunt getEmpruntactuel() {
		return empruntactuel;
	}

	public void setEmpruntactuel(Emprunt empruntactuel) {
		this.empruntactuel = empruntactuel;
	}
	
}
