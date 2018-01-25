/**
 * 
 */
package ca.qc.cgodin.Modele;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgodin.Interfaces.ISeccursale;


public class Seccursale implements ISeccursale {

	private String id;
	private String adresse;
	protected  ConnexionToDB instance = null;
	
	/* references */
	
	ArrayList<Utilisateur> utilisateurs = null;
	
	/**
	 * @param id
	 * @param adresse
	 */
	public Seccursale(String id, String adresse) {

		this.id = id;
		this.adresse = adresse;
		utilisateurs = new ArrayList<Utilisateur>();
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ISeccursale#create(ca.qc.cgodin.Modele.Seccursale)
	 */
	@Override
	public void create(Seccursale succ) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ISeccursale#update(ca.qc.cgodin.Modele.Seccursale)
	 */
	@Override
	public void update(Seccursale succ) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ISeccursale#edit(java.lang.String)
	 */
	@Override
	public Seccursale edit(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ISeccursale#delete(java.lang.String)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ISeccursale#find(java.lang.String)
	 */
	@Override
	public Seccursale find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ca.qc.cgodin.Interfaces.ISeccursale#getAll()
	 */
	@Override
	public List<Seccursale> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}// class
