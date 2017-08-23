package fr.dta.adherent.modele;

import java.time.*;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import fr.dta.configuration.IoEntity;
import fr.dta.configuration.View;
import fr.dta.cotisation.modele.Cotisation;
import fr.dta.databasehelper.LocalDateDeserializer;
import fr.dta.databasehelper.LocalDateSerializer;
import fr.dta.emprunt.modele.Emprunt;


@Entity
@Table
public class Adherent implements IoEntity {	

		@GeneratedValue
		@Id
		@JsonView(View.Summary.class)
		private Integer id;		
		
		@Column
		@NotBlank
		@JsonView(View.Summary.class)
		private String nom;
		
		@Column
		@NotBlank
		@JsonView(View.Summary.class)
		private String prenom;
		
		@Column
		@NotBlank
		@JsonView(View.Summary.class)
		private String email;
		
		@Column
		@NotNull
		@JsonSerialize(using=LocalDateSerializer.class)
		@JsonDeserialize(using=LocalDateDeserializer.class)
		@JsonView(View.AdherentSummary.class)
		private LocalDate dateNaissance;
		
		@OneToOne
		@JsonView(View.AdherentSummary.class)
		private Cotisation cotisation;
		
		@Column
		@JsonView(View.AdherentSummary.class)
		private String rue;
		
		@Column
		@JsonView(View.AdherentSummary.class)
		private String ville;
		
		@Column
		@JsonView(View.AdherentSummary.class)
		private Integer cp;
		
		@OneToMany(mappedBy="adherent")
		@JsonView(View.AdherentSummary.class)
		private List<Emprunt> emprunt;
		
		public Adherent(){
			
		}
		
		public Adherent(String nom, String prenom, String email, LocalDate dateNaissance) {
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.dateNaissance = dateNaissance;
		}		
		
		public Adherent(Integer id, String nom, String prenom, String email, LocalDate dateNaissance,
				Cotisation cotisation, String rue, String ville, Integer cp) {
			this.id = id;
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.dateNaissance = dateNaissance;
			this.cotisation = cotisation;
			this.rue = rue;
			this.ville = ville;
			this.cp = cp;
		}		
		
		public Adherent(String nom, String prenom, String email, LocalDate dateNaissance, Cotisation cotisation,
				String rue, String ville, Integer cp) {
			super();
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.dateNaissance = dateNaissance;
			this.cotisation = cotisation;
			this.rue = rue;
			this.ville = ville;
			this.cp = cp;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public LocalDate getDateNaissance() {
			return dateNaissance;
		}

		public void setDateNaissance(LocalDate dateNaissance) {
			this.dateNaissance = dateNaissance;
		}

		public Cotisation getCotisation() {
			return cotisation;
		}

		public void setCotisation(Cotisation cotisation) {
			this.cotisation = cotisation;
		}

		public String getRue() {
			return rue;
		}

		public void setRue(String rue) {
			this.rue = rue;
		}

		public String getVille() {
			return ville;
		}

		public void setVille(String ville) {
			this.ville = ville;
		}

		public Integer getCp() {
			return cp;
		}

		public void setCp(Integer cp) {
			this.cp = cp;
		}

		public List<Emprunt> getEmprunt() {
			return emprunt;
		}

		public void setEmprunt(List<Emprunt> emprunt) {
			this.emprunt = emprunt;
		}

		public void ajoutEmprunt(Emprunt emprunt){
			this.emprunt.add(emprunt);
		}
		
		@Override
		public String toString() {
			return "Adherent [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
					+ ", dateNaissance=" + dateNaissance + ", cotisation=" + cotisation + ", rue=" + rue + ", ville="
					+ ville + ", cp=" + cp + ", emprunt=" + emprunt + "]";
		}
}
