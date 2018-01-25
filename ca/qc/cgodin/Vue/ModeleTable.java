package ca.qc.cgodin.Vue;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeleTable extends AbstractTableModel {


	private String[][] donnees;
	private String[] titres;




	/**
	 * 
	 */
	public ModeleTable(String donnees[][], String[] titres) {

		this.donnees = donnees;
		this.titres = titres;	
	}




	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {

		if(donnees.length > 0)
			return donnees[0].length;
		else {
			JOptionPane.showMessageDialog(null, "Pas de transactions pour la periode indique !");
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return donnees.length;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int ligne, int colonne) {
		return donnees[ligne][colonne];
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {

		return titres[column];
	}

}//class
