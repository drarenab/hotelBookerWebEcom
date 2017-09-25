package com.models;

import java.sql.Date;

public class ReservationModel {
	private Long id;
	private Date dateDeb;
	private Date dateFin;
	private int nbEnfant;
	private int nbAdulte;
	
	private ChambreModel chambreModel;
	private UtilisationToken utilisateur;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public int getNbEnfant() {
		return nbEnfant;
	}
	public void setNbEnfant(int nbEnfant) {
		this.nbEnfant = nbEnfant;
	}
	public int getNbAdulte() {
		return nbAdulte;
	}
	public void setNbAdulte(int nbAdulte) {
		this.nbAdulte = nbAdulte;
	}
	public ChambreModel getChambreModel() {
		return chambreModel;
	}
	public void setChambreModel(ChambreModel chambreModel) {
		this.chambreModel = chambreModel;
	}
	public UtilisationToken getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(UtilisationToken utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	
}
