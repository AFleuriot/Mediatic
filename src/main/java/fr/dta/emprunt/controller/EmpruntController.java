package fr.dta.emprunt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import fr.dta.emprunt.dao.EmpruntDAO;
import fr.dta.emprunt.modele.Emprunt;
import fr.dta.emprunt.service.EmpruntService;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

	@RequestMapping(method = RequestMethod.POST)
	public void creerEmprunt(Emprunt emprunt){
		EmpruntService.emprunter(emprunt);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void rendreEmprunt(Emprunt emprunt){
		EmpruntService.rendre(emprunt);
		}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public void trouverEmprunt(@PathVariable Long id){
		EmpruntDAO.trouverEmprunt(id);
	}
	
	
	
}
