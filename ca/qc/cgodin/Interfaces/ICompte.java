/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Compte;



public interface ICompte {
	
	public void create(Compte compte);
	public void update(Compte compte);
	public Compte edit(String id);
	public void delete(String id);
	public Compte find(String id);
	public List<Compte> getAll();

}
