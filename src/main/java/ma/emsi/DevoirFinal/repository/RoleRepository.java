package ma.emsi.DevoirFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.DevoirFinal.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByNom(String nom);

}
