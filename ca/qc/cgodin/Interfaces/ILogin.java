/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Login;


public interface ILogin {
	
	public void create(Login login);
	public void update(Login login);
	public Login edit(int id);
	public void delete(int id);
	public Login find(int id);
	public List<Login> getAll();

}
