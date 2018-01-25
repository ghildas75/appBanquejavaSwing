/**
 * 
 */
package ca.qc.cgodin.Modele;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import ca.qc.cgodin.Interfaces.ILogin;


public class Login implements ILogin {

	private Date heureLogin;
	private Date heureDeconnexion;
	private String logout = null;
	protected  ConnexionToDB instance = null;

	/* references */

	private Utilisateur utilisateur = null;

	/**
	 * @param heureLogin
	 * @param heureDeconnexion
	 */
	public Login(Date heureLogin, Date heureDeconnexion) {

		this.heureLogin = heureLogin;
		this.heureDeconnexion = heureDeconnexion;
	}

	public Login(String logout) {

		this.logout = logout;
		instance = ConnexionToDB.getInstance();
	}
	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ILogin#create(ca.qc.cgodin.Modele.Login)
	 */
	@Override
	public void create(Login login) {
		// TODO Auto-generated method stub


		Connection conn =null; 
		conn = instance.getConnexion();
		PreparedStatement ps = null;

		// start
		try {

			ps = conn.prepareStatement(SQLQueries.query4);
			ps.setString(1, Session.loginAgent);
			ps.setString(2, Session.dateLogin);
			ps.setString(3, logout);
			ps.executeUpdate();

			ps.close();


		} catch (SQLException e) {

			//JOptionPane.showMessageDialog(null, " sql issues" + e.getMessage());

		} finally {

			//instance.closeConnexion();

		}

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ILogin#update(ca.qc.cgodin.Modele.Login)
	 */
	@Override
	public void update(Login login) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ILogin#edit(int)
	 */
	@Override
	public Login edit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ILogin#delete(int)
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ILogin#find(int)
	 */
	@Override
	public Login find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ILogin#getAll()
	 */
	@Override
	public List<Login> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



} // class
