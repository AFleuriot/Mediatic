package media.modele;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import emprunt.modele.Emprunt;

@Entity
public class Media {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String titre;
	
	@NotBlank
	private String auteur;
	
	@NotNull
	private TypeMedia type;
	
	@OneToMany(mappedBy = "media")
	private List<Emprunt> emprunts = new ArrayList<>();
	
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
	
}
