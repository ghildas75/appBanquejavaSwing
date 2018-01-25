/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ca.qc.cgodin.Modele.Compte;
import ca.qc.cgodin.Modele.CompteCheque;
import ca.qc.cgodin.Modele.CompteEpargne;
import ca.qc.cgodin.Modele.ConnexionToDB;
import ca.qc.cgodin.Modele.SQLQueries;
import ca.qc.cgodin.Modele.Session;
import ca.qc.cgodin.Modele.Transaction;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class FenetreVisualiserCpt extends JInternalFrame {

	private BufferedImage img;

	private ArrayList<CompteEpargne> comptesEpargne = null;
	private ArrayList<CompteCheque> comptesCheque = null;
	private String nomClient;
	private JTable table;

	private String[] collonnes = new String[] {"Numero du compte", " Solde", "Devise", "Type", "Limite decouvert", "Solde minimal", "Date d'ouverture"};

	protected int doubleClicked;

	private JLabel lblNomClient;


	/**
	 * @param nomClient2 
	 * 
	 */
	public FenetreVisualiserCpt(ArrayList<CompteCheque> comptesCheque, ArrayList<CompteEpargne> comptesEpargne, String nomClient) {

		super("Client#", true, true, true, true);
		setFont(new Font("Tahoma", Font.BOLD, 13));
		this.comptesCheque = comptesCheque;
		this.comptesEpargne = comptesEpargne;
		this.nomClient = nomClient;
		setSize(450, 200);

		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		this.setFrameIcon(new ImageIcon(img));

		getContentPane().setLayout(new BorderLayout(0, 0));

		construireUI();
		setVisible(true);
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} //constructor

	private String[][] getContenu() {

		String[][] data =  new String[(comptesCheque.size() + comptesEpargne.size())][7];

		int i = 0;

		for (CompteCheque cpt : comptesCheque) {
			
			data[i][0] = cpt.getNumero();
			data[i][1] = "" + cpt.getSolde();
			data[i][2] = cpt.getDevise();
			data[i][3] = cpt.getType();
			data[i][4] = "" + cpt.getLimiteDecouvert();
			data[i][5] = "NA";
			data[i][6] = cpt.getDateOuverture();

			i++;			
		}

		for (CompteEpargne cpte : comptesEpargne) {
			
			data[i][0] = cpte.getNumero();
			data[i][1] = "" + cpte.getSolde();
			data[i][2] = cpte.getDevise();
			data[i][3] = cpte.getType();
			data[i][4] = "NA";
			data[i][5] = "" + cpte.getSoldeMinimal();
			data[i][6] = cpte.getDateOuverture();

			i++;			
		}


		return data;
	}


	private void construireUI() {
		
		JPanel panelNorth = new JPanel();
		lblNomClient = new JLabel();
		lblNomClient.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNomClient.setText(nomClient);
		panelNorth.add(lblNomClient);
		getContentPane().add(panelNorth, BorderLayout.NORTH);
		
		
		table = new JTable();
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent evt) {
				
				doubleClicked++;

				if(doubleClicked == 2) {
					
					int row = table.rowAtPoint(evt.getPoint());
					String compte = (String) table.getValueAt(row, 0);
					displayTransaction(compte); /* for last 3 month */
					doubleClicked = 0;
				}

			}
		});
		table.setModel(new ModeleTable(getContenu(), collonnes ));
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * 
	 */
	protected void displayTransaction(String compte) {

		Session.numeroDeCompte = compte;
		
		FenetreAffichTransact fAffichageTrois = new FenetreAffichTransact(0);

		Dimension desktopSize = Session.desk.getSize();
		fAffichageTrois.setLocation(5, 5);
		fAffichageTrois.setSize(desktopSize.width - 20,
				desktopSize.height - 20);
		fAffichageTrois.setTitle("Compte# " + compte + " -- transactions en trois mois");

		Session.desk.add(fAffichageTrois);
		Session.desk.setSelectedFrame(fAffichageTrois);
		try {
			fAffichageTrois.setSelected(true);
			this.setSelected(false);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


	}

	/**
	 * @param type 
	 * 
	 */
	private void construireUI(int type) {



	}// construire UI


}// class
