/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ca.qc.cgodin.Modele.Session;
import ca.qc.cgodin.Modele.Transaction;


@SuppressWarnings("serial")
public class FenetreAffichTransact extends JInternalFrame {

	private BufferedImage img;
	private JTable table;
	private String[] collones;
	private String[][] contenu;

	/**
	 * 
	 */
	public FenetreAffichTransact(int selection) {

		super("", true, true, true, true);
		setFont(new Font("Tahoma", Font.BOLD, 13));



		Session.transactionSelect = selection;

		if(selection == 0)
			setTitle("Transactions en 3 mois");
		else
			setTitle("Transactions en 12 mois");

		setSize(450, 200);

		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

		}
		this.setFrameIcon(new ImageIcon(img));

		getContentPane().setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		collones = new String[] { "Id transaction", "Employe id" , "Num compte" ,"Type transaction", "Montant (" + Session.devise + ")", "Solde initial (" + Session.devise + ")", "Date et heure" };


		table = new JTable();

		if(selection == 0) {
			table.setModel(new ModeleTable(getContenu(), collones));
		}
		else {
			table.setModel(new ModeleTable(getContenu(), collones));
			
		}
		
		if(getContenu().length != 0) {

			scrollPane.setViewportView(table);
			construireUI(selection);
			try {
				setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			
			setVisible(true);
		}

	} /**
	 * @param  
	 * @param type
	 * @return
	 */
	private String[][] getContenu() {

		Transaction transaction = new Transaction("", "",0.0 , null);
		ArrayList<Transaction> transactions = (ArrayList<Transaction>) transaction.getAll();

		String[][] data = new String[transactions.size()][7];
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
		int i = 0;

		for (Transaction tr : transactions) {

			data[i][0] = tr.getId();
			data[i][1] = tr.getIdUser();
			data[i][2] = tr.getNumeroCompte();
			data[i][3] = tr.getType();
			data[i][4] = "" + tr.getMontant();
			data[i][5] = "" + tr.getSolde_init();
			data[i][6] = dt.format(tr.getDateTransact());

			i++;			
		}

		return data;
	}

	//constructor

	/**
	 * @param type 
	 * 
	 */
	private void construireUI(int type) {



	}// construire UI


}// class
