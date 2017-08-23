package fr.dta.media.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.dta.configuration.View;
import fr.dta.media.dao.MediaDAO;
import fr.dta.media.modele.Media;
import fr.dta.media.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {
	
	@Autowired
	MediaDAO dao;
	
	@JsonView(View.MediaSummary.class)
	@RequestMapping(method=RequestMethod.GET)
	public List<Media> getMedias(@RequestParam Map<String,String> criteria) {
		return dao.rechercheCriteriaMedia(criteria);
	}
	
	
	@JsonView(View.MediaSummary.class)
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Media getMediaById(@PathVariable Long id) {
		return dao.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Media createMedia(@RequestBody Media media) {
		return dao.save(media);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Media updateMedia(@PathVariable Long id, @RequestBody Media media) {
		return dao.save(media);
	}
	
	
	
	

}
