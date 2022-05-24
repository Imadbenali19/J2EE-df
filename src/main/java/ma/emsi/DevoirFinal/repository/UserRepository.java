package ma.emsi.DevoirFinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.emsi.DevoirFinal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u left join fetch u.roles r where r.nom='USER'")
    List<User> clients(); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='DEVELOPPEUR'")
    List<User> devloppeurs(); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='ADMIN'")
    List<User> admins(); 
	
	@Query("select DISTINCT u from User u left join fetch u.roles r where r.nom='DEVELOPPEUR' or r.nom='USER' or r=null")
    List<User> users(); 
	
	
	@Query("select u from User u left join fetch u.roles r where r.nom='USER' and u.id=:id")
	User client(@Param("id") Long id); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='DEVELOPPEUR' and u.id=:id")
	User devlopper(@Param("id") Long id); 
	
	@Query("select u from User u left join fetch u.roles r where r.nom='ADMIN' and u.id=:id")
	User admin(@Param("id") Long id); 
	
	@Query("select u from User u where u.id=:id")
	User user(@Param("id") Long id); 
	
	
	User findByUsername(String username);


}
