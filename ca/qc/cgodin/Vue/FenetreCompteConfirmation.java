/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import ca.qc.cgodin.Modele.Session;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class FenetreCompteConfirmation extends JInternalFrame {

	private BufferedImage img;
	private JTextField textCompte;
	protected FenetreAffichTransact fAffichageTrois;
	protected FenetreAffichTransact fAffichageDouze;

	/**
	 * @param selection 
	 * 
	 */
	public FenetreCompteConfirmation(int selection) {

		super("", true, true, false, true);

		setFont(new Font("Tahoma", Font.BOLD, 13));
		setTitle("Confirmation compte");
		setSize(550, 180);	

		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		this.setFrameIcon(new ImageIcon(img));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Compte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblNumeroCompte = new JLabel("Numero compte: ");
		lblNumeroCompte.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblNumeroCompte = new GridBagConstraints();
		gbc_lblNumeroCompte.ipadx = 10;
		gbc_lblNumeroCompte.ipady = 10;
		gbc_lblNumeroCompte.insets = new Insets(20, 20, 20, 20);
		gbc_lblNumeroCompte.anchor = GridBagConstraints.LINE_END;
		gbc_lblNumeroCompte.gridwidth = 2;
		gbc_lblNumeroCompte.gridx = 1;
		gbc_lblNumeroCompte.gridy = 2;
		panel.add(lblNumeroCompte, gbc_lblNumeroCompte);
		lblNumeroCompte.setLabelFor(textCompte);

		textCompte = new JTextField();
		textCompte.setFont(new Font("DialogInput", Font.BOLD, 14));
		GridBagConstraints gbc_textCompte = new GridBagConstraints();
		gbc_textCompte.fill = GridBagConstraints.BOTH;
		gbc_textCompte.insets = new Insets(20, 20, 20, 20);
		gbc_textCompte.anchor = GridBagConstraints.LINE_START;
		gbc_textCompte.gridx = 3;
		gbc_textCompte.gridy = 2;
		panel.add(textCompte, gbc_textCompte);
		textCompte.setColumns(20);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				Session.numeroDeCompte = textCompte.getText();
				FenetreCompteConfirmation.this.dispose();

				if(selection == 0) {
					fAffichageTrois = new FenetreAffichTransact(0);

					Dimension desktopSize = Session.desk.getSize();
					fAffichageTrois.setLocation(5, 5);
					fAffichageTrois.setSize(desktopSize.width - 20,
							desktopSize.height - 20);
					fAffichageTrois.setTitle("Compte# " + textCompte.getText() + " -- transactions en trois mois");
					
					Session.desk.add(fAffichageTrois);
					Session.desk.setSelectedFrame(fAffichageTrois);
					try {
						fAffichageTrois.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					fAffichageDouze = new FenetreAffichTransact(1);

					Dimension desktopSize = Session.desk.getSize();
					fAffichageDouze.setLocation(5, 5);
					fAffichageDouze.setSize(desktopSize.width - 20,
							desktopSize.height - 20);
					
					fAffichageDouze.setTitle("Compte# " + textCompte.getText() + " -- transactions en 12 mois");


					Session.desk.add(fAffichageDouze);
					Session.desk.setSelectedFrame(fAffichageDouze);
					try {
						fAffichageDouze.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		btnOk.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.LINE_END;
		gbc_btnOk.ipadx = 20;
		gbc_btnOk.insets = new Insets(10, 10, 10, 10);
		gbc_btnOk.gridx = 3;
		gbc_btnOk.gridy = 4;
		panel.add(btnOk, gbc_btnOk);
		
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);

	}//constructor

}
