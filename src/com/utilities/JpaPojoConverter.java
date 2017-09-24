package com.utilities;

import java.awt.List;
import java.util.ArrayList;

import com.entities.Chambre;
import com.entities.Photos;
import com.entities.Reservation;
import com.models.ChambreModel;
import com.models.HotelModel;
import com.models.PhotosModel;
import com.models.ReservationModel;
import com.models.UtilisationToken;

public class JpaPojoConverter {

	/**
	 * Method to covnert Chambre entity object to a plain java object 
	 * TODO parameterize to not include reservations or photos 
	 * @param chambre  : entity to be converted
	 * @return the resulted object after covnersion
	 */
	//manque reservations des chambres ! 
	 public static ChambreModel chambreJpaToPojo(Chambre chambre) {
		ChambreModel chambreModel;
		HotelModel hotelModel;
		PhotosModel photosModel;
		java.util.List<PhotosModel> photosModelList = new ArrayList<>();
		
		
		chambreModel = new ChambreModel();
		chambreModel.setEtage(chambre.getEtage());
		chambreModel.setId(chambre.getId());
		chambreModel.setNumero(chambre.getNumero());
		chambreModel.setNbLits(chambre.getnbLits());
		chambreModel.setEtat(chambre.getEtat());
		chambreModel.setPrix(chambre.getPrix());
		
		//converting photos Entities to Model
		for(Photos photos : chambre.getPhotos()) {
			photosModel = new PhotosModel();
			photosModel.setId(photos.getId());
			photosModel.setNom(photos.getNom());
			photosModel.setUrl(photos.getUrl());
			
			photosModelList.add(photosModel);
		}
		chambreModel.setPhotos(photosModelList);
		
		//converting hotel Entity to Model
		hotelModel = new HotelModel();
		hotelModel.setAdresse(chambre.getHotel().getAdresse());
		hotelModel.setCodePostal(chambre.getHotel().getCodePostal());
		hotelModel.setId(chambre.getHotel().getId());
		hotelModel.setNbEtoile(chambre.getHotel().getNbEtoile());
		hotelModel.setNom(chambre.getHotel().getNom());
		hotelModel.setRegion(chambre.getHotel().getRegion());
		hotelModel.setVille(chambre.getHotel().getVille());
		
		chambreModel.setHotel(hotelModel);
		return chambreModel;
	}
	 //if user not connected, no token dispo, so put null (a tester si pas de souci)
	 public static ReservationModel reservationJpaToPojo(Reservation reservation, String token) {
		 	ReservationModel reservationModel;
		 	ChambreModel chambre;
			UtilisationToken utilisateurToken;
			chambre=chambreJpaToPojo(reservation.getChambre());
			
			utilisateurToken=new UtilisationToken(reservation.getutilisateur(), token);
			
			reservationModel=new ReservationModel();
			reservationModel.setId(reservation.getId());
			reservationModel.setDateDeb(reservation.getDateDeb());
			reservationModel.setDateFin(reservation.getDateFin());
			reservationModel.setNbAdulte(reservation.getNbAdulte());
			reservationModel.setNbEnfant(reservation.getNbEnfant());
			reservationModel.setChambreModel(chambre);
			reservationModel.setUtilisateur(utilisateurToken);
			chambre=chambreJpaToPojo(reservation.getChambre());
		
			
			
			return reservationModel;
		}
}
