/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CompteEpargne extends Compte {

	private double soldeMinimal = 0;
	private String type;
	ConnexionToDB instance = null;

	/**
	 * @param numero
	 * @param solde
	 * @param devise
	 * @param dateOuverture
	 * @param soldeMinimal
	 */


	public CompteEpargne(double solde, String devise, Date dateOuverture, double soldeMinimal) {
		super(solde, devise, dateOuverture);
		this.soldeMinimal = soldeMinimal;
		super.genererNumero();
		instance = ConnexionToDB.getInstance();
	}

	/**
	 * @param text
	 */
	public CompteEpargne(String id) {
		super(id);
		this.type = getType();

	}

	/**
	 * 
	 */
	public CompteEpargne() {
		super();
	}

	/**
	 * @param string
	 * @param double1
	 * @param string2
	 * @param double2
	 * @param format
	 */
	public CompteEpargne(String numero, double solde, String devise, double soldeMInimal, String date_ouverture) {
		
		super();
		super.numero =  numero;
		super.solde = solde;
		super.devise = devise;
		this.soldeMinimal = soldeMInimal;
		super.dateOuvertureS = date_ouverture;

	}

	/**
	 * @param numero
	 */

	public String getNumero() {

		return super.numero;
	}

	/**
	 * @return the soldeMinimal
	 */
	public double getSoldeMinimal() {
		return soldeMinimal;
	}

	/**
	 * @param soldeMinimal the soldeMinimal to set
	 */
	public void setSoldeMinimal(double soldeMinimal) {
		this.soldeMinimal = soldeMinimal;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Modele.Compte#getType()
	 */
	@Override
	public String getType() {

		return "Epargne";
	}

	public Double getSolde() {

		return solde;
	}

	public String getDevise() {

		return devise;
	}
	
	public String getDateOuverture() {

		return dateOuvertureS;
	}
	



	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Modele.Compte#debiter(double)
	 */
	@Override
	public void debiter(double montant) throws CompteException {
		if(solde - soldeMinimal >= montant)
			solde -= montant;
		else
			throw new CompteException("Solde est insuffisent!");
	}

	/* Calculer interet mensuellement based on trigger event */

	@SuppressWarnings("unused")
	private double calculerInteret() {

		return 0.0;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ICompte#create(ca.qc.cgodin.Modele.Compte)
	 */
	@Override
	public void create(Compte compte) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ICompte#update(ca.qc.cgodin.Modele.Compte)
	 */
	@Override
	public void update(Compte compte) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ICompte#edit(java.lang.String)
	 */
	@Override
	public Compte edit(String id) {

		Connection conn =null; 
		instance = ConnexionToDB.getInstance();
		conn = instance.getConnexion();
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(SQLQueries.query31); /* update solde */

			ps.setDouble(1,solde);
			ps.setString(2,id);
			ps.executeUpdate(); /*update solde*/

			ps = conn.prepareStatement(SQLQueries.query16); /* update transacions table */
			ps.setString(1,Session.loginAgent);
			ps.setString(2,id);

			if(Session.typeTransaction == 0)
				ps.setString(3,"DEPOT");
			else
				ps.setString(3,"RETRAIT");

			ps.setDouble(4, montant);
			ps.setDouble(5, 0.0);

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_transaction = df.format(Calendar.getInstance().getTime());
			ps.setString(6, date_transaction);
			ps.executeUpdate();

			conn.commit();

			Session.statusTransaction = true;


		} catch (SQLException e) {
			// TODO Auto-generated catch block

			Session.statusTransaction = false;
			e.printStackTrace();
		}



		return null;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ICompte#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ICompte#find(java.lang.String)
	 */
	@Override
	public Compte find(String id) {

		Connection conn =null; 
		instance = ConnexionToDB.getInstance();
		conn = instance.getConnexion();
		CompteEpargne cpte = new CompteEpargne(id);
		PreparedStatement ps = null;
		ResultSet rs = null;

		// start
		try {

			ps = conn.prepareStatement(SQLQueries.query30);
			ps.setString(1,id);			
			rs = ps.executeQuery();

			while(rs.next()){

				cpte.setSoldeMinimal(rs.getDouble(1));

			}

			ps = conn.prepareStatement(SQLQueries.query28);
			ps.setString(1,id);			
			rs = ps.executeQuery();

			while(rs.next()){

				cpte.solde = rs.getDouble(1);

			}

			ps = conn.prepareStatement(SQLQueries.query29);
			ps.setString(1,id);			
			rs = ps.executeQuery();

			while(rs.next()){

				cpte.setSoldeMinimal(rs.getDouble(1));

			}

			ps.close();
			rs.close();

		} catch (SQLException e) {

			//JOptionPane.showMessageDialog(null, " sql issues" + e.getMessage());

		} finally {

			//instance.closeConnexion();

		}


		return cpte;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ICompte#getAll()
	 */
	@Override
	public List<Compte> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}//class
