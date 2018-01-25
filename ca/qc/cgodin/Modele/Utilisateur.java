package ca.qc.cgodin.Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ca.qc.cgodin.Interfaces.IUtilisateur;

public class Utilisateur implements IUtilisateur {


	private String poste;
	private String  nom;
	private String login;
	private String password;
	private String seccurs;

	/* references */

	private ArrayList<Compte> comptes = null;
	private ArrayList<Login> logins = null;
	private ArrayList<Transaction> transactions = null;
	private Seccursale seccursale = null;
	protected  ConnexionToDB instance = null;  // Singleton

	public Utilisateur() {

		this("undefined", "john", "doe", "1111");
	}

	public Utilisateur(String login, String poste, String nom, String password) { // parameters expected from DB 

		this.setLogin(login); // primary key
		this.setPoste(poste);
		this.setNom(nom);
		this.setPassword(password);
		setComptes(new ArrayList<Compte>());
		setLogins(new ArrayList<Login>());
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilisateur#create(ca.qc.cgodin.Modele.Utilisateur)
	 */
	@Override
	public void create(Utilisateur user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilisateur#update(ca.qc.cgodin.Modele.Utilisateur)
	 */
	@Override
	public void update(Utilisateur user) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilisateur#edit(java.lang.String)
	 */
	@Override
	public Utilisateur edit(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilisateur#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilisateur#find(java.lang.String)
	 */
	@Override
	public Utilisateur find(String id) {
		// TODO Auto-generated method stub !Important
		Connection conn =null; 
		instance = ConnexionToDB.getInstance();
		conn = instance.getConnexion();
		Utilisateur user = new Utilisateur();
		PreparedStatement ps = null;
		ResultSet rs = null;

		// start
		try {


			
			ps = conn.prepareStatement(SQLQueries.query1);
			ps.setString(1,id);			
			rs = ps.executeQuery();


			while(rs.next()){
				
				user.setLogin(rs.getString(1));
				user.setNom(rs.getString(2));
				Session.seccursalle = rs.getString(3);
				user.setSeccursale(new Seccursale(Session.seccursalle, ""));	
				user.setPassword(rs.getString(4));
				user.setPoste(rs.getString(5));
				
			}
			ps.close();
			rs.close();
			
		} catch (SQLException e) {
			
			//JOptionPane.showMessageDialog(null, " sql issues" + e.getMessage());
			
		} finally {
			
			//instance.closeConnexion();
			
		}
		
		return user;
		
	}// method find

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilisateur#getAll()
	 */
	@Override
	public List<Utilisateur> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the comptes
	 */
	public ArrayList<Compte> getComptes() {
		return comptes;
	}

	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(ArrayList<Compte> comptes) {
		this.comptes = comptes;
	}

	/**
	 * @return the logins
	 */
	public ArrayList<Login> getLogins() {
		return logins;
	}

	/**
	 * @param logins the logins to set
	 */
	public void setLogins(ArrayList<Login> logins) {
		this.logins = logins;
	}

	/**
	 * @return the transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * @return the seccursale
	 */
	public Seccursale getSeccursale() {
		return seccursale;
	}

	/**
	 * @param seccursale the seccursale to set
	 */
	public void setSeccursale(Seccursale seccursale) {
		this.seccursale = seccursale;
	}

	/**
	 * @return the poste
	 */
	public String getPoste() {
		return poste;
	}

	/**
	 * @param poste the poste to set
	 */
	public void setPoste(String poste) {
		this.poste = poste;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nom : " + nom +  "\npassword " + password +  "\n login : " + login +  "\n poste : " + poste 
				+ "\n succursale : " + seccursale;
	}

	/**
	 * @return the seccurs
	 */
	public String getSeccurs() {
		return seccurs;
	}

	/**
	 * @param seccurs the seccurs to set
	 */
	public void setSeccurs(String seccurs) {
		this.seccurs = seccurs;
	}


} // class
