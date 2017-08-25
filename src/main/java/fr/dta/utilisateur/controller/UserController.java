package fr.dta.utilisateur.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.dta.utilisateur.model.User;
import fr.dta.utilisateur.service.UserService;
import javassist.NotFoundException;
@RestController
@RequestMapping("/api/users")

public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public User findById(@PathVariable Integer id) throws NotFoundException {
        return userService.findById(id);
    }
		
	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
        return userService.findAll();
    }
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid User resource) {
		userService.create(resource);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public User update(@PathVariable Integer id, @RequestBody @Valid User resource) {
		return userService.update(id, resource);
	}
	
}