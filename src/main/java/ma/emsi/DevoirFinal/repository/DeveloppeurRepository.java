package ma.emsi.DevoirFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.DevoirFinal.model.Developpeur;

@Repository
public interface DeveloppeurRepository extends JpaRepository<Developpeur, Integer> {

}
