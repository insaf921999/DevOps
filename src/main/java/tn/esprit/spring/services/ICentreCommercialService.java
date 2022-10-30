package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Boutique;
import tn.esprit.spring.entities.Categorie;
import tn.esprit.spring.entities.CentreCommercial;
import tn.esprit.spring.entities.Client;

public interface ICentreCommercialService {

	void ajoutCentre(CentreCommercial centre);
	void ajouterEtaffecterListeboutiques(List<Boutique> lb, Long idCentre);
	void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques);
	void ajouterEtAffecterClientBoutiques(Client client);// methode 2
	List<Client> listeClients(Long idBoutique);
	List<Boutique> listeBoutique(Long idCentre);
	List<Client> listeDeClientsParCategorie(Categorie categorie);
	void nbreClientParGenre();
	

}
