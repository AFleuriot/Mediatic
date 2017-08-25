package fr.dta.utilisateur.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import fr.dta.authentication.util.NotFoundException;
import fr.dta.utilisateur.model.User;
import fr.dta.utilisateur.repository.UserRepository;


@SuppressWarnings("deprecation")
@Service
public class UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@SuppressWarnings("deprecation")
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findById(Integer id) throws NotFoundException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new NotFoundException();
		}
		return user;
	}
	
	public User findByLogin(String login) throws NotFoundException {
		Optional<User> user = userRepository.findOneByLogin(login);
		if (!user.isPresent()) {
			throw new NotFoundException();
		}
		return user.get();
	}
	
	public User create(User user) {
		Assert.notNull(user, "Resource can't be null");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User update(Integer id, User user) {
		User resource = userRepository.findOne(id);
		Assert.notNull(resource, "Resource can't be null");
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
