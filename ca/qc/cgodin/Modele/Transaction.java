/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.List;

import javax.swing.JOptionPane;

import ca.qc.cgodin.Interfaces.ITransaction;


public class Transaction implements ITransaction {

	private String id;
	private String type;
	private String numeroCompte;
	private String idUser;
	private double montant;
	private Timestamp dateTransact = null;
	private double solde_init = 0;
	protected  ConnexionToDB instance = null;

	/* references */

	private Utilisateur utilisateur = null;
	private Compte compte = null;


	/**
	 * @param id
	 * @param type
	 * @param montant
	 * @param dateTransact
	 */
	public Transaction(String id, String type, double montant, Timestamp dateTransact) {

		this.setId(id);
		this.setType(type);
		this.setMontant(montant);
		this.setDateTransact(dateTransact);
		instance = ConnexionToDB.getInstance();

	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITransaction#create(ca.qc.cgodin.Modele.Transaction)
	 */
	@Override
	public void create(Transaction transact) {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITransaction#update(ca.qc.cgodin.Modele.Transaction)
	 */
	@Override
	public void update(Transaction transact) {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITransaction#edit(int)
	 */
	@Override
	public Transaction edit(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITransaction#delete(int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITransaction#find(int)
	 */
	@Override
	public Transaction find(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ITransaction#getAll()
	 */
	@Override
	public List<Transaction> getAll() {

		Connection conn =null; 
		instance = ConnexionToDB.getInstance();
		conn = instance.getConnexion();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		// start
		try {
			
			if(Session.transactionSelect == 0) {
				
				ps = conn.prepareStatement(SQLQueries.query2);
				
			}else {
				ps = conn.prepareStatement(SQLQueries.query3);
			}
			
			ps.setString(1, Session.numeroDeCompte);			
			rs = ps.executeQuery();


			while(rs.next()){

					Transaction transaction = new Transaction("","",0.0,null);
					transaction.setId(rs.getString(1));
					transaction.setIdUser(rs.getString(2));
					transaction.setNumeroCompte(rs.getString(3));
					transaction.setType(rs.getString(4));
					transaction.setMontant(rs.getDouble(5));
					transaction.setSolde_init(rs.getDouble(6));
					transaction.setDateTransact(rs.getTimestamp(7));	
					transactions.add(transaction);
				
			}
			ps.close();
			rs.close();

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, " sql issues" + e.getMessage());

		} finally {

			//instance.closeConnexion();

		}

		return transactions;

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


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}


	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}


	/**
	 * @return the dateTransact
	 */
	public Timestamp getDateTransact() {
		return dateTransact;
	}


	/**
	 * @param timestamp the dateTransact to set
	 */
	public void setDateTransact(Timestamp timestamp) {
		this.dateTransact = timestamp;
	}


	/**
	 * @return the solde_init
	 */
	public double getSolde_init() {
		return solde_init;
	}


	/**
	 * @param solde_init the solde_init to set
	 */
	public void setSolde_init(double solde_init) {
		this.solde_init = solde_init;
	}


	/**
	 * @return the numeroCompte
	 */
	public String getNumeroCompte() {
		return numeroCompte;
	}


	/**
	 * @param numeroCompte the numeroCompte to set
	 */
	public void setNumeroCompte(String numeroCompte) {
		this.numeroCompte = numeroCompte;
	}


	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}


	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}



} // class
