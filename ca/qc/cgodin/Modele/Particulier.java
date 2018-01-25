/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.util.List;


public class Particulier extends Client {
	
	private static int compteur; // = Session.nbrClientParticulier++;
	
	
	private String NAS;
	private boolean Sexe; // true male and false female
	private String id = "";
	
	/**
	 * @param nom
	 * @param adresse
	 * @param tel
	 * @param courriel
	 * @param nAS
	 * @param sexe
	 */
	public Particulier(String nom, Adresse adresse, Telephone tel, String courriel, String NAS, boolean Sexe) {
		super(nom, courriel);
		this.NAS = NAS;
		this.Sexe = Sexe;
		compteur = Session.nbrClientParticulier;
		id = genererId(); // generer le id de client
	}
	/**
	 * @return the nAS
	 */
	public String getNAS() {
		return NAS;
	}
	/**
	 * @param nAS the nAS to set
	 */
	public void setNAS(String nAS) {
		NAS = nAS;
	}
	/**
	 * @return the sexe
	 */
	public boolean isSexe() {
		return Sexe;
	}
	/**
	 * @param sexe the sexe to set
	 */
	public void setSexe(boolean sexe) {
		Sexe = sexe;
	}
	
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Modele.Client#genererId()
	 */
	@Override
	protected String genererId() {
		
		id = super.genererId() + "12" + getPartCounterValue(); // add a 6 extras digits  12 + 4chiffres 0001
		
		return id;
	}
	/**
	 * @return
	 */
	private String getPartCounterValue() {

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

} // class
