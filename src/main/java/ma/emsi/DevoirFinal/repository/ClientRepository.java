package ma.emsi.DevoirFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.emsi.DevoirFinal.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
