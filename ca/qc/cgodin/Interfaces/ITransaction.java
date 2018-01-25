/**
 * 
 */
package ca.qc.cgodin.Interfaces;

import java.util.List;

import ca.qc.cgodin.Modele.Transaction;


public interface ITransaction {
	
	public void create(Transaction transact);
	public void update(Transaction transact);
	public Transaction edit(int id);
	public void delete(int id);
	public Transaction find(int id);
	public List<Transaction> getAll();

}
