package fr.dta.emprunt.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.dta.adherent.dao.AdherentDAO;
import fr.dta.adherent.modele.Adherent;
import fr.dta.configuration.View;
import fr.dta.emprunt.dao.EmpruntDAO;
import fr.dta.emprunt.modele.Emprunt;
import fr.dta.emprunt.service.EmpruntService;
import fr.dta.media.dao.MediaDAO;
import fr.dta.media.modele.Media;
import fr.dta.media.modele.TypeMedia;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

	@Autowired
	EmpruntDAO dao; 
	
	@Autowired
	AdherentDAO adherentdao;
	
	@Autowired
	MediaDAO mediadao;
	
	@RequestMapping(method = RequestMethod.POST)
	public void creerEmprunt(@RequestBody Emprunt emprunt){
		emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(emprunt.getMedia().getType().getJoursEmpruntables()));
		emprunt.getMedia().setEmpruntactuel(emprunt);
		dao.save(emprunt);
	}
	

	@RequestMapping(value="{id}", method = RequestMethod.PUT)
	public void rendreEmprunt(@PathVariable Integer id, @RequestBody Emprunt emprunt){
			emprunt.setDateRetour(LocalDate.now());
			emprunt.getMedia().setEmpruntactuel(null);
			dao.update(emprunt);
		}
	
	@JsonView(View.EmpruntSummary.class)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Emprunt trouverEmprunt(@PathVariable Integer id){
		return dao.findOne(id);
	}
	
	@JsonView(View.EmpruntSummary.class)
	@RequestMapping(method = RequestMethod.GET)
	public List<Emprunt> trouverEmpruntDeAdherent(@RequestParam Map<String,String> criteria){
		if (criteria.get("adherent")!=null) {
			Adherent a =  trouverAdherent(Integer.parseInt(criteria.get("adherent")));
			return dao.rechercheEmpruntParAdherent(a);
		}
		return dao.findAll();
	}
	
	@JsonView(View.AdherentSummary.class)
	private Adherent trouverAdherent(Integer ad) {
		return adherentdao.findOne(ad);
	}
	
	
	
	
	
	
}
