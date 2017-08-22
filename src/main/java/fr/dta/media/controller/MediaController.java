package fr.dta.media.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.media.dao.MediaDAO;
import fr.dta.media.modele.Media;
import fr.dta.media.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Media> getMedias( ) {
		return MediaDAO.rechercheMedias();
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public Media getMediaById(@PathVariable Long id) {
		return MediaService.rechercheMediaParId(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Media createMedia(@RequestBody Media media) {
		return MediaDAO.creerMedia(media);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public Media updateMedia(@PathVariable Long id, @RequestBody Media media) {
		return MediaDAO.modifierMedia(media);
	}
	
	
	
	

}
