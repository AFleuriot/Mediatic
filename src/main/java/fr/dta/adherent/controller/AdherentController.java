package fr.dta.adherent.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.dta.adherent.dao.AdherentDAO;
import fr.dta.adherent.modele.Adherent;

@RestController
@RequestMapping(value="/adherent")
public class AdherentController {

	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	public String rechercheAdherents(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
		return "";
	}
	
	@RequestMapping (method = RequestMethod.GET)
	public String rechercheAdherents(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		//Adherent adherent1 = new Adherent("Thomas", "Suzanne", "s.thomas@gmail.com", LocalDate.of(1989, 05, 21));
		//AdherentDAO.creerAdherent(adherent1);
		List<Adherent> listAdherents = AdherentDAO.rechercheAdherents();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(listAdherents);
		System.out.println(json);
		return json;
	}
}
