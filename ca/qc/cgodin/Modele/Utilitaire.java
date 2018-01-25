/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import ca.qc.cgodin.Interfaces.IUtilitaire;


public class Utilitaire implements IUtilitaire {

	private int id;
	private double tauxInteret;
	private int nbrClient;
	private int nbrClientParticulier;
	private int nbrClientEnt;
	private int nbrCompte;
	private int nbrCompteEpargne;
	private int nbrCompteCheque;
	private ConnexionToDB instance = null;

	public Utilitaire() {

		instance = ConnexionToDB.getInstance();
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilitaire#create(ca.qc.cgodin.Interfaces.Utilitaire)
	 */
	@Override
	public void create(Utilitaire util) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilitaire#update(ca.qc.cgodin.Interfaces.Utilitaire)
	 */
	@Override
	public void update(Utilitaire util) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilitaire#edit(int)
	 */
	@Override
	public Utilitaire edit(int id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilitaire#delete(int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilitaire#find(int)
	 */
	@Override
	public Utilitaire find(int id) {

		Connection conn =null; 
		instance = ConnexionToDB.getInstance();
		conn = instance.getConnexion();
		Utilitaire tool = new Utilitaire();
		PreparedStatement ps = null;
		ResultSet rs = null;

		// start
		try {

			ps = conn.prepareStatement(SQLQueries.query27);
			rs = ps.executeQuery();

			while(rs.next()){

				tool.setId(rs.getInt(1));
				tool.setTauxInteret(rs.getDouble(2));
				tool.setNbrClient(rs.getInt(3));
				tool.setNbrClientEnt(rs.getInt(4));	
				tool.setNbrClientParticulier(rs.getInt(5));	
				tool.setNbrCompte(rs.getInt(6));
				tool.setNbrCompteCheque(rs.getInt(7));
				tool.setNbrCompteEpargne(rs.getInt(8));

			}
			ps.close();
			rs.close();

		} catch (SQLException e) {

		}

		return tool;

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.IUtilitaire#getAll()
	 */
	@Override
	public List<Utilitaire> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the tauxInteret
	 */
	public double getTauxInteret() {
		return tauxInteret;
	}

	/**
	 * @param tauxInteret the tauxInteret to set
	 */
	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	/**
	 * @return the nbrClient
	 */
	public int getNbrClient() {
		return nbrClient;
	}

	/**
	 * @param nbrClient the nbrClient to set
	 */
	public void setNbrClient(int nbrClient) {
		this.nbrClient = nbrClient;
	}

	/**
	 * @return the nbrClientParticulier
	 */
	public int getNbrClientParticulier() {
		return nbrClientParticulier;
	}

	/**
	 * @param nbrClientParticulier the nbrClientParticulier to set
	 */
	public void setNbrClientParticulier(int nbrClientParticulier) {
		this.nbrClientParticulier = nbrClientParticulier;
	}

	/**
	 * @return the nbrClientEnt
	 */
	public int getNbrClientEnt() {
		return nbrClientEnt;
	}

	/**
	 * @param nbrClientEnt the nbrClientEnt to set
	 */
	public void setNbrClientEnt(int nbrClientEnt) {
		this.nbrClientEnt = nbrClientEnt;
	}

	/**
	 * @return the nbrCompte
	 */
	public int getNbrCompte() {
		return nbrCompte;
	}

	/**
	 * @param nbrCompte the nbrCompte to set
	 */
	public void setNbrCompte(int nbrCompte) {
		this.nbrCompte = nbrCompte;
	}

	/**
	 * @return the nbrCompteEpargne
	 */
	public int getNbrCompteEpargne() {
		return nbrCompteEpargne;
	}

	/**
	 * @param nbrCompteEpargne the nbrCompteEpargne to set
	 */
	public void setNbrCompteEpargne(int nbrCompteEpargne) {
		this.nbrCompteEpargne = nbrCompteEpargne;
	}

	/**
	 * @return the nbrCompteCheque
	 */
	public int getNbrCompteCheque() {
		return nbrCompteCheque;
	}

	/**
	 * @param nbrCompteCheque the nbrCompteCheque to set
	 */
	public void setNbrCompteCheque(int nbrCompteCheque) {
		this.nbrCompteCheque = nbrCompteCheque;
	}

}
