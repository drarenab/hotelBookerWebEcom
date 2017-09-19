package com.models;

import com.entities.Utilisateur;

public class UtilisationToken {

	private String nom;
	private String prenom;
	private String adresse;
	private String ville;
	private String region;
	private String codePostal;
	private String sexe;
	private String numTel;
	private String email;
	private String role;
	
	private String token;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UtilisationToken(String nom, String prenom, String adresse, String ville, String region, String codePostal,
			String sexe, String numTel, String email, String role, String token) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.ville = ville;
		this.region = region;
		this.codePostal = codePostal;
		this.sexe = sexe;
		this.numTel = numTel;
		this.email = email;
		this.role = role;
		this.token = token;
	}
	
	public UtilisationToken(Utilisateur uti, String token) {
		super();
		this.nom = uti.getNom();
		this.prenom = uti.getPrenom();
		this.adresse = uti.getAdresse();
		this.ville = uti.getVille();
		this.region = uti.getRegion();
		this.codePostal = uti.getCodePostal();
		this.sexe = uti.getsexe();
		this.numTel = uti.getNumTel();
		this.email = uti.getEmail();
		this.role = uti.getRole().getlibelle();
		this.token = token;
	}
	
	
	
	
}
