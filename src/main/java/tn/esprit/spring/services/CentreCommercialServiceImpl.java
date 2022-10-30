package tn.esprit.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Boutique;
import tn.esprit.spring.entities.Categorie;
import tn.esprit.spring.entities.CentreCommercial;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.entities.Genre;
import tn.esprit.spring.repositories.IBoutiqueRepository;
import tn.esprit.spring.repositories.ICentreCommercialRepository;
import tn.esprit.spring.repositories.IClientRepository;

@Service
@Slf4j
public class CentreCommercialServiceImpl implements ICentreCommercialService {

	@Autowired
	ICentreCommercialRepository centreCommercialRepository;
	@Autowired
	IBoutiqueRepository boutiqueRepository;
	@Autowired
	IClientRepository clientRepository;

	@Override
	@Transactional
	public void ajoutCentre(CentreCommercial centre) {
		for (Boutique boutique : centre.getBoutiques()) {
			boutique.setCentreCommercial(centre);
		}
		centreCommercialRepository.save(centre);
	}

	@Override
	public void ajouterEtaffecterListeboutiques(List<Boutique> lb, Long idCentre) {
		CentreCommercial centre = centreCommercialRepository.findById(idCentre).orElse(null);
		for (Boutique boutique : lb) {
			boutique.setCentreCommercial(centre);
			boutiqueRepository.save(boutique);
		}
	}

	@Override
	@Transactional
	public void ajouterEtAffecterClientBoutiques(Client client, List<Long> idBoutiques) {
		clientRepository.save(client);
		for (Long idBoutique : idBoutiques) {
			Boutique boutique = boutiqueRepository.findById(idBoutique).orElse(null);
			boutique.getClients().add(client);
		}
	}

	@Override
	@Transactional
	public void ajouterEtAffecterClientBoutiques(Client client) {
		clientRepository.save(client);
		for (Boutique boutique : client.getBoutiques()) { //la liste contient ici seulement les id des boutiques
			boutique = boutiqueRepository.findById(boutique.getId()).orElse(null);
			boutique.getClients().add(client);
		}
	}

	@Override
	public List<Client> listeClients(Long idBoutique) {
		Boutique boutique = boutiqueRepository.findById(idBoutique).orElse(null);
		return boutique.getClients();
	}

	@Override
	public List<Boutique> listeBoutique(Long idCentre) {
		CentreCommercial centre = centreCommercialRepository.findById(idCentre).orElse(null);
		return centre.getBoutiques();
	}

	@Override
	public List<Client> listeDeClientsParCategorie(Categorie categorie) {
		return clientRepository.clientsCategory(categorie);
	}

	@Override
	@Scheduled(cron = "*/30 * * * * *")
	public void nbreClientParGenre() {
		log.info("--- Nombre des Clients MASCULIN :" + clientRepository.nbreByGenre(Genre.MASCULIN));
		log.info("--- Nombre des Clients FEMININ : " + clientRepository.nbreByGenre(Genre.FEMININ));
	}

}
