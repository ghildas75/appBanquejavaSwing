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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

import ca.qc.cgodin.Modele.ConnexionToDB;
import ca.qc.cgodin.Modele.SQLQueries;
import ca.qc.cgodin.Modele.Session;
import ca.qc.cgodin.Modele.Utilitaire;


@SuppressWarnings("serial")
public class FenetreTaux extends JInternalFrame {
	private BufferedImage img;
	private JTextField textTaux;

	public FenetreTaux() {

		super("", true,true, false, true);
		setFont(new Font("Tahoma", Font.BOLD, 13));
		setTitle("Taux Interet");
		setSize(400, 200);
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

	}//constructor

	private void construireUI() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Modifier Taux", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 15));
		lblMessage.setForeground(Color.RED);
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.anchor = GridBagConstraints.WEST;
		gbc_lblMessage.gridwidth = 3;
		gbc_lblMessage.insets = new Insets(5, 5, 5, 5);
		gbc_lblMessage.gridx = 2;
		gbc_lblMessage.gridy = 1;
		panel.add(lblMessage, gbc_lblMessage);

		JLabel lblMontant = new JLabel("Nouveau Taux: ");
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
		gbc_lblMontant.gridy = 3;
		panel.add(lblMontant, gbc_lblMontant);

		textTaux = new JTextField();
		textTaux.setFont(new Font("DialogInput", Font.BOLD, 12));
		textTaux.setMinimumSize(new Dimension(0, 20));
		textTaux.setColumns(13);
		GridBagConstraints gbc_textTaux = new GridBagConstraints();
		gbc_textTaux.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTaux.anchor = GridBagConstraints.NORTHWEST;
		gbc_textTaux.insets = new Insets(5, 5, 5, 5);
		gbc_textTaux.gridx = 2;
		gbc_textTaux.gridy = 3;
		panel.add(textTaux, gbc_textTaux);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection conn =null; 
				conn = ConnexionToDB.getInstance().getConnexion();
				PreparedStatement ps = null;
				int status = 0;
				try {
					
					ps = conn.prepareStatement(SQLQueries.query32);
					ps.setDouble(1, Double.parseDouble(textTaux.getText()));
					status = ps.executeUpdate();
					ps.close();
					
				} catch (SQLException ex) {
					
					//JOptionPane.showMessageDialog(null, " sql issues" + ex.getMessage());
					
				} 
				
				if(status != 0) {
					lblMessage.setText("Operation reussie");
					lblMessage.setForeground(Color.GREEN);
					textTaux.setText(null);
				} else {
					lblMessage.setText("Operation echoue");
					lblMessage.setForeground(Color.RED);
					textTaux.setText(null);					
				}

			}// action performed
		});
		btnModifier.setFont(new Font("Dialog", Font.BOLD, 12));
		btnModifier.setMinimumSize(new Dimension(50, 23));
		btnModifier.setMaximumSize(new Dimension(100, 23));
		GridBagConstraints gbc_btnModifier = new GridBagConstraints();
		gbc_btnModifier.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModifier.weightx = 0.5;
		gbc_btnModifier.ipadx = 30;
		gbc_btnModifier.anchor = GridBagConstraints.LINE_START;
		gbc_btnModifier.insets = new Insets(5, 5, 5, 5);
		gbc_btnModifier.gridx = 2;
		gbc_btnModifier.gridy = 5;
		panel.add(btnModifier, gbc_btnModifier);



	}



}// class
