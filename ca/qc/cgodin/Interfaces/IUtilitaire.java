/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Utilitaire;



public interface IUtilitaire {
	
	public void create(Utilitaire util);
	public void update(Utilitaire util);
	public Utilitaire edit(int id);
	public void delete(int id);
	public Utilitaire find(int id);
	public List<Utilitaire> getAll();

}
