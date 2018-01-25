/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Seccursale;


public interface ISeccursale {
	
	public void create(Seccursale succ);
	public void update(Seccursale succ);
	public Seccursale edit(String id);
	public void delete(String id);
	public Seccursale find(String id);
	public List<Seccursale> getAll();

}
