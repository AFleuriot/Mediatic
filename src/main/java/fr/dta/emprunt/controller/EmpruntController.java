package fr.dta.emprunt.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.dta.configuration.View;
import fr.dta.emprunt.dao.EmpruntDAO;
import fr.dta.emprunt.modele.Emprunt;
import fr.dta.emprunt.service.EmpruntService;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

	@Autowired
	EmpruntDAO dao; 
	
	@RequestMapping(method = RequestMethod.POST)
	public void creerEmprunt(@RequestBody Emprunt emprunt){
		emprunt.setDateRetourPrevue(emprunt.getDateEmprunt().plusDays(emprunt.getMedia().getType().getJoursEmpruntables()));
		emprunt.getMedia().setEmpruntactuel(emprunt);
		dao.save(emprunt);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void rendreEmprunt(@RequestBody Emprunt emprunt){
		emprunt.setDateRetour(LocalDate.now());
		emprunt.getMedia().setEmpruntactuel(null);
		dao.save(emprunt);
		}
	
	@JsonView(View.EmpruntSummary.class)
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public void trouverEmprunt(@PathVariable Integer id){
		dao.findOne(id);
	}
	
	
	
}
