/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.util.List;

import ca.qc.cgodin.Interfaces.IAdresse;


public class Adresse implements IAdresse {
	
	private int numeroCivic;
	private String numeroAppartement;
	private String nomRue;
	private String ville;
	private String province;
	private String codePostal;
	private String pays;
	private ConnexionToDB conn = null;
	
	/* references */
	
	private Client client = null;
	
	/**
	 * 
	 */
	/**
	 * @param numeroRue
	 * @param numeroAppartement
	 * @param nomRue
	 * @param ville
	 * @param province
	 * @param codePostal
	 * @param pays
	 */
	public Adresse(int numeroCivic, String numeroAppartement, String nomRue, String ville, String province,
			String codePostal, String pays) {
		this.numeroCivic = numeroCivic;
		this.numeroAppartement = numeroAppartement;
		this.nomRue = nomRue;
		this.ville = ville;
		this.province = province;
		this.codePostal = codePostal;
		this.pays = pays;
	}
	/**
	 * @return the numeroRue
	 */
	public int getNumeroRue() {
		return numeroCivic;
	}
	/**
	 * @param numeroRue the numeroRue to set
	 */
	public void setNumeroRue(int numeroRue) {
		this.numeroCivic = numeroRue;
	}
	/**
	 * @return the numeroAppartement
	 */
	public String getNumeroAppartement() {
		return numeroAppartement;
	}
	/**
	 * @param numeroAppartement the numeroAppartement to set
	 */
	public void setNumeroAppartement(String numeroAppartement) {
		this.numeroAppartement = numeroAppartement;
	}
	/**
	 * @return the nomRue
	 */
	public String getNomRue() {
		return nomRue;
	}
	/**
	 * @param nomRue the nomRue to set
	 */
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IAdresse#create(ca.qc.cgodin.Modele.Adresse)
	 */
	@Override
	public void create(Adresse adresse) {

		conn = ConnexionToDB.getInstance();
		//insert Adresse
		
			
		
		// Fin connexion
		conn.closeConnexion();
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IAdresse#update(ca.qc.cgodin.Modele.Adresse)
	 */
	@Override
	public void update(Adresse adresse) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IAdresse#edit(int)
	 */
	@Override
	public Adresse edit(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IAdresse#delete(int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IAdresse#find(int)
	 */
	@Override
	public Adresse find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IAdresse#getAll()
	 */
	@Override
	public List<Adresse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
