package media.modele;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Media {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String titre;
	
	@NotBlank
	private String auteur;
	
	@NotBlank
	private TypeMedia type;
	
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
	
}
