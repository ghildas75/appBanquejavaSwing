/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JOptionPane;


public class ConnexionToDB {


	private static ConnexionToDB instance = null;
	private Connection conn = null;
	private Properties properties;

	/*
	 * TRANSACTION EXAMPLE:
	 * con = DriverManager.getConnection(url, user, password);
	 *       st = con.createStatement();
	 *
	 *      con.setAutoCommit(false);
	 *
	 *       st.executeUpdate("UPDATE Authors SET Name = 'Leo Tolstoy' "
	 *              + "WHERE Id = 1");
	 *       st.executeUpdate("UPDATE Books SET Title = 'War and Peace' "
	 *              + "WHERE Id = 1");
	 *       st.executeUpdate("UPDATE Books SET Titl = 'Anna Karenina' "
	 *              + "WHERE Id = 2");
	 *
	 *      con.commit(); 
	 * 
	 * */


	private ConnexionToDB() {

		connectToDB();

	} // constructor

	/**
	 * 
	 */
	private void connectToDB() {

		properties = getProperties(); 

		try {
			Class.forName(properties.getProperty("db.driver"));
			String url = properties.getProperty("db.url") 
					+ "&user=" + properties.getProperty("db.user") 
					+ "&password=" + properties.getProperty("db.password");

			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null," Connection impossible" +  e.getMessage());
		} catch (ClassNotFoundException e) {

			JOptionPane.showMessageDialog(null," Class Diver not found" +  e.getMessage());

		}

	} // connect To DB

	public boolean executeTransaction(PreparedStatement[] psTab) {



		try {
			conn.setAutoCommit(false);

			for (int i = 0; i < psTab.length; i++) {
				
				psTab[i].executeUpdate();
			}
			
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog(null, " exeute trans exepetion : " + e.getMessage());
			return false;
		}
		
		return true;
	}

	/**
	 * @return
	 */
	private Properties getProperties() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(new File("resources/config/db.properties")));
		} catch (FileNotFoundException e) {
			System.out.println("" + e.getMessage());
		}catch (IOException ex) {
			System.out.println("" + ex.getMessage());
		} 
		return properties;
	}

	public static synchronized ConnexionToDB getInstance() {

		if(instance == null)
			instance =  new ConnexionToDB();

		return instance;

	} // public get instance method

	public Connection getConnexion() {

		return conn;
	}

	public void closeConnexion() {

		try {
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Impossible to close DB connexion.");
		}
	} // closeconnexion

	/**
	 * @return
	 */
	public static String getCustomerName(String idClient) {

			String name= "";
			Connection conn = instance.getConnexion();
			try {
				PreparedStatement ps = conn.prepareStatement(SQLQueries.query35);
				ps.setString(1, idClient);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					name = rs.getString(1);					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return name;
	}

	//	public static ResultSet executeQuery(String query) throws SQLException {
	//
	//
	//	}
	//
	//	public static int executeUpdate(String query) throws SQLException {
	//
	//
	//	}



} // Singleton
