/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ca.qc.cgodin.Modele.CompteCheque;
import ca.qc.cgodin.Modele.CompteEpargne;
import ca.qc.cgodin.Modele.CompteException;
import ca.qc.cgodin.Modele.Session;


@SuppressWarnings("serial")
public class FenetreVirement extends JInternalFrame {



	private JTextField textSource;
	private JTextField textMontant;
	private JTextField textDestinataire;
	private BufferedImage img;
	private JComboBox<String> cboTypeSource;
	private JComboBox<String> cboTypeBenificiare;
	private JLabel lblMessage;

	/**
	 * 
	 */
	public FenetreVirement() {

		super("", true, true, false, true);
		setFont(new Font("Tahoma", Font.BOLD, 13));
		setTitle("Operations");
		setSize(550, 250);
		getContentPane().setLayout(new BorderLayout(0, 0));

		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		this.setFrameIcon(new ImageIcon(img));

		construireUI();
		
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);

	}// constructor

	private void construireUI() {




		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Virement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMessage.setForeground(Color.RED);
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.anchor = GridBagConstraints.WEST;
		gbc_lblMessage.gridwidth = 4;
		gbc_lblMessage.insets = new Insets(5, 5, 5, 5);
		gbc_lblMessage.gridx = 2;
		gbc_lblMessage.gridy = 1;
		panel.add(lblMessage, gbc_lblMessage);

		JLabel lblNumCompte = new JLabel("Compte Source: ");
		lblNumCompte.setMinimumSize(new Dimension(0, 14));
		lblNumCompte.setHorizontalAlignment(SwingConstants.LEFT);
		lblNumCompte.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblNumCompte = new GridBagConstraints();
		gbc_lblNumCompte.gridwidth = 2;
		gbc_lblNumCompte.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumCompte.weightx = 1.0;
		gbc_lblNumCompte.anchor = GridBagConstraints.EAST;
		gbc_lblNumCompte.ipadx = 10;
		gbc_lblNumCompte.ipady = 10;
		gbc_lblNumCompte.gridx = 0;
		gbc_lblNumCompte.gridy = 2;
		panel.add(lblNumCompte, gbc_lblNumCompte);

		textSource = new JTextField();
		lblNumCompte.setLabelFor(textSource);
		textSource.setFont(new Font("DialogInput", Font.BOLD, 12));
		textSource.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textSource = new GridBagConstraints();
		gbc_textSource.gridwidth = 2;
		gbc_textSource.insets = new Insets(5, 5, 5, 5);
		gbc_textSource.anchor = GridBagConstraints.NORTHWEST;
		gbc_textSource.gridx = 2;
		gbc_textSource.gridy = 2;
		panel.add(textSource, gbc_textSource);
		textSource.setColumns(20);

		cboTypeSource = new JComboBox();
		cboTypeSource.setModel(new DefaultComboBoxModel(new String[] {"Cheque", "Epargne"}));
		GridBagConstraints gbc_cboTypeSource = new GridBagConstraints();
		gbc_cboTypeSource.anchor = GridBagConstraints.LINE_START;
		gbc_cboTypeSource.insets = new Insets(5, 5, 5, 5);
		gbc_cboTypeSource.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboTypeSource.gridx = 4;
		gbc_cboTypeSource.gridy = 2;
		panel.add(cboTypeSource, gbc_cboTypeSource);

		JLabel lblCompteDestinataire = new JLabel("Compte Benificiare: ");
		lblCompteDestinataire.setMinimumSize(new Dimension(0, 14));
		lblCompteDestinataire.setHorizontalAlignment(SwingConstants.LEFT);
		lblCompteDestinataire.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblCompteDestinataire = new GridBagConstraints();
		gbc_lblCompteDestinataire.weightx = 1.0;
		gbc_lblCompteDestinataire.ipady = 10;
		gbc_lblCompteDestinataire.ipadx = 10;
		gbc_lblCompteDestinataire.anchor = GridBagConstraints.EAST;
		gbc_lblCompteDestinataire.gridwidth = 2;
		gbc_lblCompteDestinataire.insets = new Insets(0, 0, 5, 5);
		gbc_lblCompteDestinataire.gridx = 0;
		gbc_lblCompteDestinataire.gridy = 4;
		panel.add(lblCompteDestinataire, gbc_lblCompteDestinataire);

		textDestinataire = new JTextField();
		textDestinataire.setMinimumSize(new Dimension(0, 20));
		textDestinataire.setFont(new Font("DialogInput", Font.BOLD, 12));
		textDestinataire.setColumns(20);
		GridBagConstraints gbc_textDest = new GridBagConstraints();
		gbc_textDest.anchor = GridBagConstraints.NORTHWEST;
		gbc_textDest.gridwidth = 2;
		gbc_textDest.insets = new Insets(5, 5, 5, 5);
		gbc_textDest.gridx = 2;
		gbc_textDest.gridy = 4;
		panel.add(textDestinataire, gbc_textDest);

		cboTypeBenificiare = new JComboBox();
		cboTypeBenificiare.setModel(new DefaultComboBoxModel(new String[] {"Cheque", "Epargne"}));
		GridBagConstraints gbc_cboTypeBenificiare = new GridBagConstraints();
		gbc_cboTypeBenificiare.anchor = GridBagConstraints.LINE_START;
		gbc_cboTypeBenificiare.insets = new Insets(5, 5, 5, 5);
		gbc_cboTypeBenificiare.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboTypeBenificiare.gridx = 4;
		gbc_cboTypeBenificiare.gridy = 4;
		panel.add(cboTypeBenificiare, gbc_cboTypeBenificiare);

		JLabel lblMontant = new JLabel("Montant: ");
		lblMontant.setMinimumSize(new Dimension(0, 20));
		lblMontant.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblMontant = new GridBagConstraints();
		gbc_lblMontant.gridwidth = 2;
		gbc_lblMontant.weightx = 1.0;
		gbc_lblMontant.ipady = 10;
		gbc_lblMontant.ipadx = 10;
		gbc_lblMontant.anchor = GridBagConstraints.EAST;
		gbc_lblMontant.insets = new Insets(0, 0, 5, 5);
		gbc_lblMontant.gridx = 0;
		gbc_lblMontant.gridy = 6;
		panel.add(lblMontant, gbc_lblMontant);

		textMontant = new JTextField();
		textMontant.setFont(new Font("DialogInput", Font.BOLD, 12));
		textMontant.setMinimumSize(new Dimension(0, 20));
		textMontant.setColumns(13);
		GridBagConstraints gbc_textMontant = new GridBagConstraints();
		gbc_textMontant.anchor = GridBagConstraints.NORTHWEST;
		gbc_textMontant.insets = new Insets(5, 5, 5, 5);
		gbc_textMontant.gridx = 2;
		gbc_textMontant.gridy = 6;
		panel.add(textMontant, gbc_textMontant);

		JButton btnExecuter = new JButton("Executer Operation");
		btnExecuter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// retrait from source				
				executerRetrait();				
				// deposit in destinataire
				if(!Session.retraitExceptionFired)
					executerDepot();
				else {
					lblMessage.setText("Echoue: Manque de fond");
					lblMessage.setForeground(Color.RED);
					textMontant.setText(null);
					textSource.setText(null);
					textDestinataire.setText(null);
				}
			}
		});

		JComboBox<String> cboDevise = new JComboBox<String>();
		cboDevise.setModel(new DefaultComboBoxModel<String>(new String[] {"CAD", "USD", "EUR", "YEN", "PND"}));
		GridBagConstraints gbc_cboDevise = new GridBagConstraints();
		gbc_cboDevise.anchor = GridBagConstraints.LINE_START;
		gbc_cboDevise.insets = new Insets(0, 0, 5, 5);
		gbc_cboDevise.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboDevise.gridx = 3;
		gbc_cboDevise.gridy = 6;
		panel.add(cboDevise, gbc_cboDevise);
		btnExecuter.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExecuter.setMinimumSize(new Dimension(50, 23));
		btnExecuter.setMaximumSize(new Dimension(100, 23));
		GridBagConstraints gbc_btnExecuter = new GridBagConstraints();
		gbc_btnExecuter.weightx = 0.5;
		gbc_btnExecuter.ipadx = 30;
		gbc_btnExecuter.anchor = GridBagConstraints.LINE_START;
		gbc_btnExecuter.gridwidth = 3;
		gbc_btnExecuter.insets = new Insets(5, 5, 0, 5);
		gbc_btnExecuter.gridx = 2;
		gbc_btnExecuter.gridy = 8;
		panel.add(btnExecuter, gbc_btnExecuter);



	}

	/**
	 * 
	 */
	protected void executerRetrait() {
		Session.retraitExceptionFired = false;
		Session.typeTransaction = 1;

		if(cboTypeSource.getSelectedIndex() == 0) {
						
			CompteCheque cpt = new CompteCheque(textSource.getText());
			cpt = (CompteCheque) cpt.find(textSource.getText()); /* limite decouvert and solde recu */

			
			try {

				cpt.debiter(Double.parseDouble(textMontant.getText()));
				cpt.edit(textSource.getText());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CompteException ex) {
				Session.retraitExceptionFired = true;					

			}
		} else {

			CompteEpargne cpte = new CompteEpargne(textSource.getText());
			cpte = (CompteEpargne) cpte.find(textSource.getText());				
			try {
				cpte.debiter(Double.parseDouble(textMontant.getText()));
				cpte.edit(textSource.getText());

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (CompteException ex) {
				Session.retraitExceptionFired = true;
			}
		}


		if(!Session.retraitExceptionFired) {
			if(Session.statusTransaction) {

			} else {

				Session.retraitExceptionFired = true;
			}
		} else { /* exception fired */

			lblMessage.setText("Echoue: Manque du fond");
			lblMessage.setForeground(Color.RED);
			textMontant.setText(null);
			textSource.setText(null);
		}



	} // exeucter depot

	/**
	 * 
	 */
	protected void executerDepot() {

		//compte and montant dispo

		Session.typeTransaction = 0;
		if(cboTypeBenificiare.getSelectedIndex() == 0) {

			CompteCheque cpt = new CompteCheque(textDestinataire.getText());
			cpt = (CompteCheque) cpt.find(textDestinataire.getText());
			cpt.crediter(Double.parseDouble(textMontant.getText()));
			cpt.edit(textDestinataire.getText());
		} else {

			CompteEpargne cpte = new CompteEpargne(textDestinataire.getText());
			cpte = (CompteEpargne) cpte.find(textDestinataire.getText());				
			cpte.crediter(Double.parseDouble(textMontant.getText()));
			cpte.edit(textDestinataire.getText());


		}

		if(Session.statusTransaction) {
			
			lblMessage.setText("Operation reussie");
			lblMessage.setForeground(Color.GREEN);
			textMontant.setText(null);
			textDestinataire.setText(null);
			textSource.setText(null);

		} else {
			lblMessage.setText("Operation echoue");
			lblMessage.setForeground(Color.RED);
			textMontant.setText(null);
			textDestinataire.setText(null);
			textSource.setText(null);
		}
		
		Session.retraitExceptionFired = false;


	}// executer depot


	public JComboBox<String> getCboTypeSource() {
		return cboTypeSource;
	}
	public JComboBox getCboTypeBenificiare() {
		return cboTypeBenificiare;
	}
	public JLabel getLblMessage() {
		return lblMessage;
	}
}// class
