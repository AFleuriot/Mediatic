package adherent.modele;

import java.time.*;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import cotisation.modele.Cotisation;
import emprunt.modele.Emprunt;


@Entity
@Table
public class Adherent {	

		@GeneratedValue
		@Id
		private Long id;		
		
		@Column
		@NotBlank
		private String nom;
		
		@Column
		@NotBlank
		private String prenom;
		
		@Column
		@NotBlank
		private String email;
		
		@Column
		@NotBlank
		private LocalDate dateNaissance;
		
		@OneToOne
		private Cotisation cotisation;
		
		@Column
		private String rue;
		
		@Column
		private String ville;
		
		@Column
		private Integer cp;
		
		@OneToMany(mappedBy="adherent")
		private List<Emprunt> emprunt;
		
		public Adherent(){
			
		}
		
		public Adherent(Long id, String nom, String prenom, String email, LocalDate dateNaissance,
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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
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

		
		@Override
		public String toString() {
			return "Adherent [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", dateNaissance="
					+ dateNaissance + ", cotisation=" + cotisation + ", rue=" + rue + ", ville=" + ville + ", cp=" + cp
					+ "]";
		}		
								
}
