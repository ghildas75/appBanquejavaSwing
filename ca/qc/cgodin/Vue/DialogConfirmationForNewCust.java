/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;


public class DialogConfirmationForNewCust extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private BufferedImage img;

	/**
	 * Create the dialog.
	 */
	public DialogConfirmationForNewCust(String idClient, String idCompte ) {
		
		
		setFont(new Font("Dialog", Font.PLAIN, 14));
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Nouveau Client");
		setModalityType(ModalityType.APPLICATION_MODAL);
		
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dimension = tool.getScreenSize();
		int width = (int) (0.35 * dimension.getWidth());
		int height = (int) (0.32 * dimension.getHeight());
		int x = (int) (0.3 * dimension.getWidth());
		int y = (int) (0.3 * dimension.getHeight());
		setBounds(x, y, width, height);
		
		//setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE, 0.0, 0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Transaction Reussie");
			lblNewLabel.setForeground(new Color(0, 100, 0));
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 6;
			gbc_lblNewLabel.ipady = 10;
			gbc_lblNewLabel.ipadx = 10;
			gbc_lblNewLabel.insets = new Insets(5, 5, 5, 5);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblIdClient = new JLabel("Identifiant Client:  ");
			lblIdClient.setFont(new Font("Dialog", Font.BOLD, 16));
			GridBagConstraints gbc_lblIdClient = new GridBagConstraints();
			gbc_lblIdClient.anchor = GridBagConstraints.LINE_END;
			gbc_lblIdClient.ipady = 10;
			gbc_lblIdClient.ipadx = 10;
			gbc_lblIdClient.insets = new Insets(5, 5, 5, 5);
			gbc_lblIdClient.gridwidth = 2;
			gbc_lblIdClient.gridx = 1;
			gbc_lblIdClient.gridy = 1;
			contentPanel.add(lblIdClient, gbc_lblIdClient);
		}
		{
			JLabel lblNumClient = new JLabel(idClient);
			lblNumClient.setForeground(new Color(128, 0, 0));
			lblNumClient.setFont(new Font("Dialog", Font.BOLD, 16));
			GridBagConstraints gbc_lblNumClient = new GridBagConstraints();
			gbc_lblNumClient.anchor = GridBagConstraints.LINE_START;
			gbc_lblNumClient.gridwidth = 4;
			gbc_lblNumClient.ipady = 10;
			gbc_lblNumClient.ipadx = 10;
			gbc_lblNumClient.insets = new Insets(5, 5, 5, 5);
			gbc_lblNumClient.gridx = 4;
			gbc_lblNumClient.gridy = 1;
			contentPanel.add(lblNumClient, gbc_lblNumClient);
		}
		{
			JLabel lblCompteClient = new JLabel("Compte Client: ");
			GridBagConstraints gbc_lblCompteClient = new GridBagConstraints();
			gbc_lblCompteClient.anchor = GridBagConstraints.LINE_END;
			gbc_lblCompteClient.gridwidth = 2;
			gbc_lblCompteClient.ipady = 10;
			gbc_lblCompteClient.ipadx = 10;
			gbc_lblCompteClient.insets = new Insets(5, 5, 0, 5);
			gbc_lblCompteClient.gridx = 1;
			gbc_lblCompteClient.gridy = 2;
			contentPanel.add(lblCompteClient, gbc_lblCompteClient);
			lblCompteClient.setFont(new Font("Dialog", Font.BOLD, 16));
		}
		{
			JLabel lblNumCompte = new JLabel(idCompte);
			lblNumCompte.setForeground(new Color(128, 0, 0));
			lblNumCompte.setFont(new Font("Dialog", Font.BOLD, 16));
			GridBagConstraints gbc_lblNumCompte = new GridBagConstraints();
			gbc_lblNumCompte.ipady = 10;
			gbc_lblNumCompte.ipadx = 10;
			gbc_lblNumCompte.insets = new Insets(5, 5, 0, 5);
			gbc_lblNumCompte.anchor = GridBagConstraints.LINE_START;
			gbc_lblNumCompte.gridwidth = 4;
			gbc_lblNumCompte.gridx = 4;
			gbc_lblNumCompte.gridy = 2;
			contentPanel.add(lblNumCompte, gbc_lblNumCompte);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						DialogConfirmationForNewCust.this.dispose();
					}
				});
				okButton.setFont(new Font("Dialog", Font.BOLD, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		this.setIconImage(img);
	} // constructor

} // class
