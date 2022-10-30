package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nom;
	@Enumerated(EnumType.STRING)
	private Genre genre;

	//@JsonIgnore // il faut supprimer si on utilise la methode 2 de la question 3
	//le type de cascade doit etre aussi CascadeType.REMOVE seulement pour la methode 2 de la question 3
	//sinon une exception sera lance de type entity detached
	@ManyToMany(mappedBy = "clients", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Boutique> boutiques;
}
