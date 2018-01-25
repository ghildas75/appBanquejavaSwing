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


public class CompteCheque extends Compte {



	private double limiteDecouvert;
	ConnexionToDB instance = null;
	private String type;




	/**
	 * @param numero
	 * @param solde
	 * @param devise
	 * @param dateOuverture
	 */
	public CompteCheque(double solde, String devise, Date dateOuverture, double limiteDecouvert) {

		super(solde, devise, dateOuverture);
		this.limiteDecouvert = limiteDecouvert;
		instance = ConnexionToDB.getInstance();

	}

	public CompteCheque(String numero, double solde, String devise, double limiteDecouvert, String date_ouverture) {

		super();


		super.numero =  numero;
		super.solde = solde;
		super.devise = devise;
		this.limiteDecouvert = limiteDecouvert;
		super.dateOuvertureS = date_ouverture;

		instance = ConnexionToDB.getInstance();
	}

	/**
	 * 
	 */
	public CompteCheque(String id) {

		super(id);
		this.type = getType();
	}

	/**
	 * 
	 */
	public CompteCheque() {
		super();
	}

	
	public void setSolde(double solde) {
		
		super.setSolde(solde);
		
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Modele.Compte#getType()
	 */
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Cheque";
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

		if((solde + limiteDecouvert) >= montant)
			solde -= montant;
		else
			throw new CompteException("Solde est insuffisent!");

	}	

	/**
	 * @return the limiteDecouvert
	 */
	public double getLimiteDecouvert() {
		return limiteDecouvert;
	}

	/**
	 * @param limiteDecouvert the limiteDecouvert to set
	 */
	public void setLimiteDecouvert(double limiteDecouvert) {
		this.limiteDecouvert = limiteDecouvert;
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
	public Compte edit(String id) { // 16 31

		Connection conn =null; 
		instance = ConnexionToDB.getInstance();
		conn = instance.getConnexion();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);


			ps = conn.prepareStatement(SQLQueries.query31); /* update solde in db */
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


		instance = ConnexionToDB.getInstance();

		Connection conn =null; 
		conn = instance.getConnexion();
		CompteCheque cpt = new CompteCheque(id);
		PreparedStatement ps = null;
		ResultSet rs = null;

		// start
		try {

			ps = conn.prepareStatement(SQLQueries.query30);
			ps.setString(1,id);			
			rs = ps.executeQuery();

			while(rs.next()){

				cpt.setLimiteDecouvert(rs.getDouble(1));

			}
			ps = conn.prepareStatement(SQLQueries.query28);
			ps.setString(1,id);			
			rs = ps.executeQuery();

			while(rs.next()){

				//cpt.solde = rs.getDouble(1);
				cpt.setSolde(rs.getDouble(1));

			}

			ps = conn.prepareStatement(SQLQueries.query30);
			ps.setString(1,id);			
			rs = ps.executeQuery();

			while(rs.next()){

				cpt.setLimiteDecouvert(rs.getDouble(1));

			}

			ps.close();
			rs.close();

		} catch (SQLException e) {

			//JOptionPane.showMessageDialog(null, " sql issues" + e.getMessage());

		} finally {

			//instance.closeConnexion();

		}


		return cpt;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ICompte#getAll()
	 */
	@Override
	public List<Compte> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return
	 */
	public String getNumero() {
		// TODO Auto-generated method stub
		return super.numero;
	}

} // class
