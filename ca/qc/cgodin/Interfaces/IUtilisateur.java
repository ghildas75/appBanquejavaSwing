/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Utilisateur;

public interface IUtilisateur {
	
	public void create(Utilisateur user); // insert in db
	public void update(Utilisateur user); // updting record
	public Utilisateur edit(String id); //
	public void delete(String id);
	public Utilisateur find(String id);
	public List<Utilisateur> getAll();

}
