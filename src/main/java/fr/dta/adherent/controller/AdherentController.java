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

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dta.adherent.dao.AdherentDAO;
import fr.dta.adherent.modele.Adherent;
import fr.dta.configuration.View;
import fr.dta.media.modele.Media;

@RestController
@RequestMapping(value="/adherent")
public class AdherentController {

	@Autowired
	AdherentDAO dao;
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	@JsonView(View.Summary.class)
	public Adherent rechercheAdherent( @PathVariable Long id) {
		return dao.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Adherent createMedia(@RequestBody Adherent adh) {
		return dao.save(adh);
	}
	
	@RequestMapping (method = RequestMethod.GET)
	@JsonView(View.Summary.class)
	public List<Adherent> rechercheAdherents(@RequestParam Map<String, String> criteria) throws JsonProcessingException {
		/*Adherent adherent1 = new Adherent("Thomas", "Suzanne", "s.thomas@gmail.com", LocalDate.of(1989, 05, 21));
		AdherentDAO.creerAdherent(adherent1);
		List<Adherent> listAdherents = AdherentDAO.rechercheAdherents();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(listAdherents);
		System.out.println(json);*/
		return dao.findAll();
	}
}
