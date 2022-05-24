package ma.emsi.DevoirFinal.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.emsi.DevoirFinal.model.Client;
import ma.emsi.DevoirFinal.model.Role;
import ma.emsi.DevoirFinal.model.User;
import ma.emsi.DevoirFinal.repository.RoleRepository;
import ma.emsi.DevoirFinal.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username){
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Nom d'utilisateur ou mot de passe erroné");
		for (Role r : user.getRoles())
			System.out.println("Role:" + r.getNom());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getNom()))
						.collect(Collectors.toList()));
	}

	@Override
	public void save(User user) {
		
		Role r = roleRepository.findByNom("USER");
		if (r == null)
			r = new Role("USER");
		user.setRoles(Arrays.asList(r));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	
		User sameUser = userRepository.findByUsername(user.getUsername());
		if(sameUser == null)
			userRepository.save(user);

	}

	@Override
	public List<User> liste() {
		return userRepository.findAll();

	}

	@Override
	public void supprimer(int id) {
		userRepository.deleteById(id);

	}

	@Override
	public void ajouter(User user) {
		userRepository.save(user);
		SecurityContextHolder.getContext().getAuthentication();

	}

	@Override
	public void modifier(User user) {
		User u = findById(user.getId());
		if (u != null) {
			u.setUsername(user.getUsername());
			userRepository.save(u);
		}

	}

	@Override
	public User findById(int id) {
		if (userRepository.existsById(id))
			return userRepository.getById(id);
		
		return null;
	}

	public List<User> devs(){
		return userRepository.devloppeurs();
	}
	
	public List<User> admins(){
		return userRepository.admins();
	}
	
	public List<User> clients(){
		return userRepository.clients();
	}
	
	public List<User> users(){
		return userRepository.users();
	}
	

	
}
