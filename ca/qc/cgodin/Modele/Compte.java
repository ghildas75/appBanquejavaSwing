/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.util.ArrayList;
import java.util.Date;


import ca.qc.cgodin.Interfaces.ICompte;


public abstract class Compte implements ICompte {


	private static int compteur; // = Session.nbrCompte;

	protected String numero = null;
	protected double solde;
	protected String devise;
	protected Date dateOuverture;
	protected  ConnexionToDB instance = null;
	protected String dateOuvertureS;

	/* references */

	protected Client client = null;
	protected ArrayList<Transaction> transactions = null;

	protected double montant;

	/**
	 * @param numero
	 * @param solde
	 * @param devise
	 * @param dateOuverture
	 */
	public Compte(double solde, String devise, Date dateOuverture) {

		this.solde = solde;
		this.devise = devise;
		this.dateOuverture = dateOuverture;
		getNbrCompte(); // from db
		compteur = Session.nbrCompte;
		numero = genererNumero();

	} 




	public Compte(String id) {
		
		chargerDonnees(id);
	}

	/**
	 * 
	 */
	public Compte() {
		
	}

	/**
	 * 
	 */
	private void chargerDonnees(String id) {
		
	} // charger donnees

	/**
	 * @return
	 */
	private void getNbrCompte() {

		Utilitaire tool = new Utilitaire();
		tool = tool.find(1);
		Session.nbrCompte = tool.getNbrCompte() + 1;
		Session.nbrCompteCheque = tool.getNbrCompteCheque() + 1;
		Session.nbrCompteEpargne = tool.getNbrCompteEpargne() + 1;
	}

	protected abstract String getType();

	public void crediter(double montant) {
		this.montant = montant;
		solde += montant;

	}

	public void debiter(double montant) throws CompteException {

		if(solde >= montant)
			solde -= montant;
		else
			throw new CompteException("Solde est insuffisent!");

	}

	protected String genererNumero() {		
		return "022" + getSeccursaleId() + getIndiceCompte();
	}

	protected void incrementCounter() {

		compteur++;
	}

	/**
	 * @return
	 */
	private String getIndiceCompte() {

		String resultat;

		if(compteur < 10)
			resultat = "000000" + compteur;
		else if(compteur < 100)
			resultat = "00000" + compteur;
		else if(compteur < 1000)
			resultat = "0000" + compteur;
		else if(compteur < 10000)
			resultat = "000" + compteur;
		else if(compteur < 100000)
			resultat = "00" + compteur;
		else
			resultat = "0" + compteur;

		return resultat;

	}

	/**
	 * @return
	 */
	private String getSeccursaleId() {

		return Session.seccursalle;
	}




	/**
	 * @param solde2
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	/* 
	 *  une transaction qui est
	 *	enregistrée dans la base de données avec l’utilisateur qui a réalisé la transaction, la
	 *	succursale de la transaction , le type de transaction et le montant de la transaction 
	 * */


}// class



