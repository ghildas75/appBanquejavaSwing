/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Telephone;


public interface ITelephone {
	
	public void create(Telephone tel);
	public void update(Telephone tel);
	public Telephone edit(int id);
	public void delete(int id);
	public Telephone find(int id);
	public List<Telephone> getAll();

}
