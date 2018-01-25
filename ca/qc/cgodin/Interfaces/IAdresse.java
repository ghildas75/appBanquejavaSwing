/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Adresse;


public interface IAdresse {
	
	public void create(Adresse adresse);
	public void update(Adresse adresse);
	public Adresse edit(int id);
	public void delete(int id);
	public Adresse find(int id);
	public List<Adresse> getAll();

}
