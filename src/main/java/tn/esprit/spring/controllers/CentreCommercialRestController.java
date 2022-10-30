package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Boutique;
import tn.esprit.spring.entities.Categorie;
import tn.esprit.spring.entities.CentreCommercial;
import tn.esprit.spring.entities.Client;
import tn.esprit.spring.services.ICentreCommercialService;

@RestController
@RequestMapping("/centre")
public class CentreCommercialRestController {

	@Autowired
	ICentreCommercialService centreCommercialService;
	
	@PostMapping("/add")
	public void addCentre(@RequestBody CentreCommercial centre){
		centreCommercialService.ajoutCentre(centre);
	}
	@PostMapping("/add-boutiques/{id-centre}")
	public void ajouterEtaffecterListeboutiques(@RequestBody List<Boutique> lb, @PathVariable("id-centre") Long idCentre){
		centreCommercialService.ajouterEtaffecterListeboutiques(lb, idCentre);
	}
	@PostMapping("/add-client/{idBoutiques}")
	public void ajouterEtAffecterClientBoutiques(@RequestBody Client client, @PathVariable("idBoutiques")List<Long> idBoutiques){
		centreCommercialService.ajouterEtAffecterClientBoutiques(client, idBoutiques);
	}
	@PostMapping("/add-clientB")//methode 2
	public void affecterClientBoutiques(@RequestBody Client client){
		centreCommercialService.ajouterEtAffecterClientBoutiques(client);
	}
	
	@GetMapping("/clients/{id-boutique}")
	public List<Client> listeClients(@PathVariable("id-boutique")Long idBoutique) {
		return centreCommercialService.listeClients(idBoutique);
	}
	@GetMapping("/boutiques/{id-centre}")
	public List<Boutique> listeBoutique(@PathVariable("id-centre")Long idCentre) {
		return centreCommercialService.listeBoutique(idCentre);
	}
	@GetMapping("/clients-category/{category}")
	public List<Client> listeDeClientsParCategorie(@PathVariable("category") Categorie categorie) {
		return centreCommercialService.listeDeClientsParCategorie(categorie);
	}
}
