package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Categorie;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Genre;

@Repository
public interface IClientRepository extends CrudRepository<Client, Long> {

	@Query("Select c FROM Client c join c.boutiques bs where bs.categorie = :category")
	List<Client> clientsCategory(@Param("category") Categorie categorie);
	
	@Query("Select COUNT(*) FROM Client c where c.genre = :genre")
	int nbreByGenre(@Param("genre") Genre genre);
}
