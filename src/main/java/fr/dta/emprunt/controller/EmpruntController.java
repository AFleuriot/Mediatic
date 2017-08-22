package fr.dta.emprunt.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import fr.dta.emprunt.dao.EmpruntDAO;
import fr.dta.emprunt.modele.Emprunt;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

	@Autowired
	EmpruntDAO dao;
	
	@RequestMapping(method = RequestMethod.POST)
	public void creerEmprunt(Emprunt emprunt){
		dao.save(emprunt);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void modifierEmprunt(Emprunt emprunt){
		dao.save(emprunt);
		}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public void trouverEmprunt(@PathVariable Long id){
		dao.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void chercherEmprunts(@RequestParam Map<String, String> criteria){
		dao.rechercheCriteriaMedia(criteria);
	}
	
	
}
