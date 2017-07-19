package cotisation.modele;

import java.time.*;

import javax.persistence.*;

import adherent.modele.Adherent;

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

}
