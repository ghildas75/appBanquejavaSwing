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
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class FenetreRetrait extends JInternalFrame {





	private JTextField textNumCompte;
	private JTextField textMontant;
	private BufferedImage img;

	public FenetreRetrait() {
		super("", true, true, false, true);
		setFont(new Font("Tahoma", Font.BOLD, 13));
		setTitle("Operations");
		setSize(500, 250);
		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		this.setFrameIcon(new ImageIcon(img));
		getContentPane().setLayout(new BorderLayout(0, 0));
		construireUI();
		
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);
	}

	private void construireUI() {


		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Retrait", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMessage.setForeground(Color.RED);
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.ipadx = 10;
		gbc_lblMessage.anchor = GridBagConstraints.WEST;
		gbc_lblMessage.gridwidth = 3;
		gbc_lblMessage.insets = new Insets(5, 5, 5, 5);
		gbc_lblMessage.gridx = 2;
		gbc_lblMessage.gridy = 1;
		panel.add(lblMessage, gbc_lblMessage);

		JLabel lblNumCompte = new JLabel("Numero compte: ");
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
		lblNumCompte.setLabelFor(textNumCompte);

		textNumCompte = new JTextField();
		textNumCompte.setFont(new Font("DialogInput", Font.BOLD, 12));
		textNumCompte.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textUserName = new GridBagConstraints();
		gbc_textUserName.insets = new Insets(5, 5, 5, 5);
		gbc_textUserName.anchor = GridBagConstraints.NORTHWEST;
		gbc_textUserName.gridx = 2;
		gbc_textUserName.gridy = 2;
		panel.add(textNumCompte, gbc_textUserName);
		textNumCompte.setColumns(20);

		JComboBox<String> cboType = new JComboBox<String>();
		cboType.setModel(new DefaultComboBoxModel<String>(new String[] {"Cheque", "Epargne"}));
		GridBagConstraints gbc_cboType = new GridBagConstraints();
		gbc_cboType.ipadx = 10;
		gbc_cboType.anchor = GridBagConstraints.LINE_START;
		gbc_cboType.insets = new Insets(5, 5, 5, 5);
		gbc_cboType.gridx = 3;
		gbc_cboType.gridy = 2;
		panel.add(cboType, gbc_cboType);

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
		gbc_lblMontant.gridy = 4;
		panel.add(lblMontant, gbc_lblMontant);

		textMontant = new JTextField();
		textMontant.setFont(new Font("DialogInput", Font.BOLD, 12));
		textMontant.setMinimumSize(new Dimension(0, 20));
		textMontant.setColumns(13);
		GridBagConstraints gbc_textMontant = new GridBagConstraints();
		gbc_textMontant.anchor = GridBagConstraints.NORTHWEST;
		gbc_textMontant.insets = new Insets(5, 5, 5, 5);
		gbc_textMontant.gridx = 2;
		gbc_textMontant.gridy = 4;
		panel.add(textMontant, gbc_textMontant);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setMinimumSize(new Dimension(0, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"CAD", "USD", "EUR", "YEN", "PND"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.ipadx = 10;
		gbc_comboBox.anchor = GridBagConstraints.LINE_START;
		gbc_comboBox.insets = new Insets(5, 5, 5, 5);
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 4;
		panel.add(comboBox, gbc_comboBox);

		JButton btnExecuter = new JButton("Executer Operation");
		btnExecuter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Session.retraitExceptionFired = false;
				Session.typeTransaction = 1;

				if(cboType.getSelectedIndex() == 0) {

					CompteCheque cpt = new CompteCheque(textNumCompte.getText());
					cpt = (CompteCheque) cpt.find(textNumCompte.getText()); /* limite decouvert and solde recu */
					try {
						cpt.debiter(Double.parseDouble(textMontant.getText()));
						cpt.edit(textNumCompte.getText());

					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (CompteException ex) {

						Session.retraitExceptionFired = true;					

					}
				} else {

					CompteEpargne cpte = new CompteEpargne(textNumCompte.getText());
					cpte = (CompteEpargne) cpte.find(textNumCompte.getText());				
					try {
						cpte.debiter(Double.parseDouble(textMontant.getText()));
						cpte.edit(textNumCompte.getText());

					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (CompteException ex) {

						Session.retraitExceptionFired = true;
					}
				}


				if(!Session.retraitExceptionFired) {
					if(Session.statusTransaction) {

						lblMessage.setText("Operation reussie");
						lblMessage.setForeground(Color.GREEN);
						textMontant.setText(null);
						textNumCompte.setText(null);
					} else {

						lblMessage.setText("Operation echoue");
						lblMessage.setForeground(Color.RED);
						textMontant.setText(null);
						textNumCompte.setText(null);
					}
				} else { /* exception fired */
					
					lblMessage.setText("Echoue: Manque du fond");
					lblMessage.setForeground(Color.RED);
					textMontant.setText(null);
					textNumCompte.setText(null);
					Session.retraitExceptionFired = false;
					
					
				}

			}




		});
		btnExecuter.setFont(new Font("Dialog", Font.BOLD, 12));
		btnExecuter.setMinimumSize(new Dimension(50, 23));
		btnExecuter.setMaximumSize(new Dimension(100, 23));
		GridBagConstraints gbc_btnExecuter = new GridBagConstraints();
		gbc_btnExecuter.anchor = GridBagConstraints.WEST;
		gbc_btnExecuter.ipadx = 30;
		gbc_btnExecuter.weightx = 0.5;
		gbc_btnExecuter.gridwidth = 3;
		gbc_btnExecuter.insets = new Insets(5, 5, 0, 5);
		gbc_btnExecuter.gridx = 2;
		gbc_btnExecuter.gridy = 6;
		panel.add(btnExecuter, gbc_btnExecuter);

	}



}
