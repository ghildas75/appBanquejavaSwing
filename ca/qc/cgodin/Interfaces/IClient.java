/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Client;

public interface IClient {

	public void create(Client client);
	public void update(Client client);
	public Client edit(String id);
	public void delete(String id);
	public Client find(String id);
	public List<Client> getAll();
	
}
