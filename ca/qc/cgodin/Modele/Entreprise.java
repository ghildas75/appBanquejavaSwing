/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.util.List;


public class Entreprise extends Client {

	
	private String neq;
	private String url;
	private String id;
	private int compteur; // = Session.nbrClientEntreprise++;
	/**
	 * @param nom
	 * @param adresse
	 * @param tel
	 * @param courriel
	 * @param neq
	 * @param url
	 */
	public Entreprise(String nom, String courriel, String neq, String url) {
		
		super(nom, courriel);
		this.setNeq(neq);
		this.setUrl(url);
		compteur = Session.nbrClientEntreprise;
		this.id  = genererId();
		
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Modele.Client#genererId()
	 */
	@Override
	protected String genererId() {

		this.id = super.genererId() + "16" + getEntrCounterValue(); // add a 6 extras digits  16 + 4chiffres 0001
		return this.id;
	}


	/**
	 * @return
	 */
	private String getEntrCounterValue() {
		
		String resultat;

		if(compteur < 10)
			resultat = "000" + compteur;
		else if(compteur < 100)
			resultat = "00" + compteur;
		else if(compteur < 1000)
			resultat = "0" + compteur;
		else
			resultat = "" + compteur;

		return resultat;
		
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IClient#create(ca.qc.cgodin.Modele.Client)
	 */
	@Override
	public void create(Client client) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IClient#update(ca.qc.cgodin.Modele.Client)
	 */
	@Override
	public void update(Client client) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IClient#edit(java.lang.String)
	 */
	@Override
	public Client edit(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IClient#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IClient#find(java.lang.String)
	 */
	@Override
	public Client find(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IClient#getAll()
	 */
	@Override
	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @return the neq
	 */
	public String getNeq() {
		return neq;
	}


	/**
	 * @param neq the neq to set
	 */
	public void setNeq(String neq) {
		this.neq = neq;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}//class
