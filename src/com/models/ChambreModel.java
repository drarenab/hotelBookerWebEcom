package com.models;

import java.util.List;

import com.entities.Photos;

public class ChambreModel {
	
	private Long id;
	private Long numero;
	private int nbLits;//nbLits de chambre (duo, individuel....)
	private Long etage;
	private int etat;//dispo a la location 0 non dispo 1
	private float prix; 
	private HotelModel hotel;
	private List<PhotosModel> photos;
	
	public List<PhotosModel> getPhotos() {
		return photos;
	}
	public void setPhotos(List<PhotosModel> photos) {
		this.photos = photos;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public int getNbLits() {
		return nbLits;
	}
	public void setNbLits(int nbLits) {
		this.nbLits = nbLits;
	}
	public Long getEtage() {
		return etage;
	}
	public void setEtage(Long etage) {
		this.etage = etage;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public HotelModel getHotel() {
		return hotel;
	}
	public void setHotel(HotelModel hotel) {
		this.hotel = hotel;
	}
	
	
}
