package ma.emsi.DevoirFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.DevoirFinal.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	
}
