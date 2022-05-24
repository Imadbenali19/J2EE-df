package ma.emsi.DevoirFinal.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import ma.emsi.DevoirFinal.model.User;

public interface UserService extends UserDetailsService {

	void save(User user);

	List<User> liste();

	void supprimer(int id);

	void ajouter(User user);

	void modifier(User user);

	User findById(int id);

	

	
}
