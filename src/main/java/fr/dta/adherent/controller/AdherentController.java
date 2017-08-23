package fr.dta.adherent.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dta.adherent.dao.AdherentDAO;
import fr.dta.adherent.modele.Adherent;
import fr.dta.media.modele.Media;

@RestController
@RequestMapping(value="/adherent")
public class AdherentController {

	@Autowired
	AdherentDAO dao;
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public Adherent rechercheAdherent( @PathVariable Long id) {
		return dao.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Adherent createMedia(@RequestBody Adherent adh) {
		return dao.save(adh);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Adherent> getAdherents(@RequestParam Map<String,String> criteria) {
		return dao.rechercheCriteriaAdherent(criteria);
	}
}
