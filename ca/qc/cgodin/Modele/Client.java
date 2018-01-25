/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.util.ArrayList;


import ca.qc.cgodin.Interfaces.IClient;


public abstract class Client implements IClient {
	
	/* references */
	protected ArrayList<Compte> comptes = null;
	protected ArrayList<Adresse> adresses = null;
	protected ArrayList<Telephone> telephones = null;


	protected String id; // generer automatiquement
	protected String nom; // nom prenom ou raison sociale
	protected String courriel;
	protected static int compteur; // = Session.nbrClient++;
	protected  ConnexionToDB instance = null;
	/**
	 * @param nom
	 * @param adresse
	 * @param tel
	 * @param courriel
	 */
	public Client(String nom, String courriel) {

		this.nom = nom;
		this.courriel = courriel;
		comptes = new ArrayList<Compte>();
		telephones = new ArrayList<Telephone>();
		adresses = new ArrayList<Adresse>();
		getNbrClient(); // from db
		compteur = Session.nbrClient;
		
		this.id = genererId(); // generer Id pour le client
	}
	/**
	 * 
	 */
	private void getNbrClient() {

		Utilitaire tool = new Utilitaire();
		tool = tool.find(1);
		Session.nbrClient = tool.getNbrClient() + 1;
		Session.nbrClientEntreprise = tool.getNbrClientEnt() + 1;
		Session.nbrClientParticulier = tool.getNbrClientParticulier() + 1;
	}
	/*
	 *  4312 + 000001
	 */
	protected String genererId() { /* Generer Id pour le client de 10 chiffres */

		return "4312" + getCounterValue();
	}

	/**
	 * @return
	 */
	private String getCounterValue() {
		
		String resultat;

		if(compteur < 10)
			resultat = "00000" + compteur;
		else if(compteur < 100)
			resultat = "0000" + compteur;
		else if(compteur < 1000)
			resultat = "000" + compteur;
		else if(compteur < 10000)
			resultat = "00" + compteur;
		else if(compteur < 100000)
			resultat = "0" + compteur;
		else
			resultat = "" + compteur;

		return resultat;
	}
	protected void incrementCounter() {

		compteur++;
	}

}
