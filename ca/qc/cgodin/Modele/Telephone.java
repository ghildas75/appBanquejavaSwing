/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.util.List;

import ca.qc.cgodin.Interfaces.ITelephone;


public class Telephone implements ITelephone {
	
	private String telephonePrincipal;
	private int numeroPostePrincipale;
	private String telephoneSecondaire;
	private int numeroPosteSecondaire;
	private String telephoneMobile;
	private String telecopieur;
	protected  ConnexionToDB instance = null;
	
	/* references */
	
	private Client client = null;
	
	
	/**
	 * @return the telephonePrincipal
	 */
	public String getTelephonePrincipal() {
		return telephonePrincipal;
	}
	/**
	 * @param telephonePrincipal the telephonePrincipal to set
	 */
	public void setTelephonePrincipal(String telephonePrincipal) {
		this.telephonePrincipal = telephonePrincipal;
	}
	/**
	 * @return the telephoneSecondaire
	 */
	public String getTelephoneSecondaire() {
		return telephoneSecondaire;
	}
	/**
	 * @param telephoneSecondaire the telephoneSecondaire to set
	 */
	public void setTelephoneSecondaire(String telephoneSecondaire) {
		this.telephoneSecondaire = telephoneSecondaire;
	}
	/**
	 * @return the telephoneMobile
	 */
	public String getTelephoneMobile() {
		return telephoneMobile;
	}
	/**
	 * @param telephoneMobile the telephoneMobile to set
	 */
	public void setTelephoneMobile(String telephoneMobile) {
		this.telephoneMobile = telephoneMobile;
	}
	/**
	 * @return the telecopieur
	 */
	public String getTelecopieur() {
		return telecopieur;
	}
	/**
	 * @param telecopieur the telecopieur to set
	 */
	public void setTelecopieur(String telecopieur) {
		this.telecopieur = telecopieur;
	}
	
	
	/**
	 * @param telephonePrincipal
	 * @param telephoneSecondaire
	 * @param telephoneMobile
	 * @param telecopieur
	 */
	public Telephone(String telephonePrincipal, String telephoneSecondaire, String telephoneMobile,
			String telecopieur) {
		super();
		this.telephonePrincipal = telephonePrincipal;
		this.telephoneSecondaire = telephoneSecondaire;
		this.telephoneMobile = telephoneMobile;
		this.telecopieur = telecopieur;
	}
	/**
	 * @return the numeroPostePrincipale
	 */
	public int getNumeroPostePrincipale() {
		return numeroPostePrincipale;
	}
	/**
	 * @param numeroPostePrincipale the numeroPostePrincipale to set
	 */
	public void setNumeroPostePrincipale(int numeroPostePrincipale) {
		this.numeroPostePrincipale = numeroPostePrincipale;
	}
	/**
	 * @return the numeroPosteSecondaire
	 */
	public int getNumeroPosteSecondaire() {
		return numeroPosteSecondaire;
	}
	/**
	 * @param numeroPosteSecondaire the numeroPosteSecondaire to set
	 */
	public void setNumeroPosteSecondaire(int numeroPosteSecondaire) {
		this.numeroPosteSecondaire = numeroPosteSecondaire;
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITelephone#create(ca.qc.cgodin.Modele.Telephone)
	 */
	@Override
	public void create(Telephone tel) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITelephone#update(ca.qc.cgodin.Modele.Telephone)
	 */
	@Override
	public void update(Telephone tel) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITelephone#edit(int)
	 */
	@Override
	public Telephone edit(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITelephone#delete(int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITelephone#find(int)
	 */
	@Override
	public Telephone find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITelephone#getAll()
	 */
	@Override
	public List<Telephone> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
