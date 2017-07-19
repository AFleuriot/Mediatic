package adherent.modele;

import java.time.*;

import javax.persistence.*;

import cotisation.modele.Cotisation;


@Entity
@Table
public class Adherent {
	
		@GeneratedValue
		@Id
		private Long id;
		
		@Column
		private String mdp;
		
		@Column
		private String nom;
		
		@Column
		private String prenom;
		
		@Column
		private String email;
		
		@Column
		private LocalDate dateNaissance;
		
		@OneToOne(mappedBy = "adherent")
		private Cotisation cotisation;
		
		@Column
		private String rue;
		
		@Column
		private String ville;
		
		@Column
		private int cp;
								
}
