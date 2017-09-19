package com.models;

import java.util.List;

import com.entities.Photos;

public class HotelModel {
	private Long id;
	private String nom;
	private String adresse;
	private String ville;
	private String region;
	private String codePostal;
	private String nbEtoile;
	private List<ChambreModel> chambreList;
	private List<Photos> photosList;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getNbEtoile() {
		return nbEtoile;
	}
	public void setNbEtoile(String nbEtoile) {
		this.nbEtoile = nbEtoile;
	}
	public List<ChambreModel> getChambreList() {
		return chambreList;
	}
	public void setChambreList(List<ChambreModel> chambreList) {
		this.chambreList = chambreList;
	}
	public List<Photos> getPhotosList() {
		return photosList;
	}
	public void setPhotosList(List<Photos> photosList) {
		this.photosList = photosList;
	}
	
	
	
	
	
}
