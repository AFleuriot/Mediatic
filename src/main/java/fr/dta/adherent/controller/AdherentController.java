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
import fr.dta.cotisation.dao.CotisationDAO;
import fr.dta.media.modele.Media;

@RestController
@RequestMapping(value="/adherent")
public class AdherentController {

	@Autowired
	AdherentDAO dao;
	
	@Autowired
	CotisationDAO cotDao;
	
	@RequestMapping (value = "{id}", method = RequestMethod.GET)
	@JsonView(View.AdherentSummary.class)
	public Adherent rechercheAdherent( @PathVariable Integer id) {
		return dao.findOne(id);
	}
	
	@JsonView(View.Summary.class)
	@RequestMapping(method = RequestMethod.POST)
	public Adherent createAdherent(@RequestBody Adherent adh) {
		cotDao.save(adh.getCotisation());
		Adherent res = dao.save(adh);
		adh.getCotisation().setAdherent(res);
		cotDao.update(adh.getCotisation());
		return res;
	}
	
	@JsonView(View.AdherentSummary.class)
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Adherent updateAdherent(@PathVariable Integer id, @RequestBody Adherent adh) {
		cotDao.update(adh.getCotisation());
		return dao.update(adh);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@JsonView(View.AdherentSummary.class)
	public List<Adherent> getAdherents(@RequestParam Map<String,String> criteria) {
		return dao.rechercheCriteriaAdherent(criteria);
	}
}
