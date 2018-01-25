package ca.qc.cgodin.Vue;



import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import ca.qc.cgodin.Modele.Session;
import ca.qc.cgodin.Modele.Utilisateur;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class FenetreLogin extends JInternalFrame {

	private JPasswordField passwordField;
	private JTextField textUserName;
	private BufferedImage img;
	private FenetrePrincipale parent = null;
	private JLabel lblError;
	protected String poste;

	/**
	 * Create the frame.
	 */
	public FenetreLogin(FenetrePrincipale parent) {	

		super("Acces", true, false, false, true);
		this.parent = parent;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}

		this.setFrameIcon(new ImageIcon(img));

		setFont(new Font("Tahoma", Font.BOLD, 13));
		setSize(400, 200);
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



	void construireUI() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Connexion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblError = new JLabel();
		lblError.setFont(new Font("Arial Unicode MS", Font.BOLD, 11));
		lblError.setForeground(Color.RED);
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 3;
		gbc_lblError.anchor = GridBagConstraints.LINE_START;
		gbc_lblError.insets = new Insets(5, 5, 5, 5);
		gbc_lblError.gridx = 2;
		gbc_lblError.gridy = 1;
		panel.add(lblError, gbc_lblError);

		JLabel lblUserName = new JLabel("User Name: ");
		lblUserName.setMinimumSize(new Dimension(0, 14));
		lblUserName.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserName.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblUserName = new GridBagConstraints();
		gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserName.weightx = 1.0;
		gbc_lblUserName.anchor = GridBagConstraints.EAST;
		gbc_lblUserName.ipadx = 10;
		gbc_lblUserName.ipady = 10;
		gbc_lblUserName.gridx = 1;
		gbc_lblUserName.gridy = 2;
		panel.add(lblUserName, gbc_lblUserName);
		lblUserName.setLabelFor(textUserName);

		textUserName = new JTextField();
		textUserName.setFont(new Font("DialogInput", Font.BOLD, 12));
		textUserName.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textUserName = new GridBagConstraints();
		gbc_textUserName.gridwidth = 3;
		gbc_textUserName.insets = new Insets(5, 5, 5, 5);
		gbc_textUserName.anchor = GridBagConstraints.NORTHWEST;
		gbc_textUserName.gridx = 2;
		gbc_textUserName.gridy = 2;
		panel.add(textUserName, gbc_textUserName);
		textUserName.setColumns(26);

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setMinimumSize(new Dimension(0, 20));
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.weightx = 1.0;
		gbc_lblPassword.ipady = 10;
		gbc_lblPassword.ipadx = 10;
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 4;
		panel.add(lblPassword, gbc_lblPassword);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setFont(new Font("DialogInput", Font.BOLD, 12));
		passwordField.setMinimumSize(new Dimension(0, 20));
		passwordField.setColumns(26);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.anchor = GridBagConstraints.NORTHWEST;
		gbc_passwordField.insets = new Insets(5, 5, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		panel.add(passwordField, gbc_passwordField);

		JButton btnConnexion = new JButton("Connexion");

		btnConnexion.setFont(new Font("Dialog", Font.BOLD, 12));
		btnConnexion.setMinimumSize(new Dimension(50, 23));
		btnConnexion.setMaximumSize(new Dimension(100, 23));
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				String password = new String( passwordField.getPassword());


				if(isValidUser(textUserName.getText(), password)) {

					if(parent.poste.equalsIgnoreCase("caissier")) {
						parent.creerOperationalMenu(0);
						parent.createToolBar(0);

					} else if (parent.poste.equalsIgnoreCase("directeur")) {

						parent.creerOperationalMenu(1);
						parent.createToolBar(1);

					}

					else if (parent.poste.equalsIgnoreCase("gestionnaire")) {

						parent.creerOperationalMenu(2);
						parent.createToolBar(2);

					} else {}

					FenetreLogin.this.dispose();


				} else {
					lblError.setText("Erreur, verifiez vos informations");

				}


			}
		});
		GridBagConstraints gbc_btnConnexion = new GridBagConstraints();
		gbc_btnConnexion.weightx = 0.5;
		gbc_btnConnexion.ipadx = 30;
		gbc_btnConnexion.anchor = GridBagConstraints.LINE_START;
		gbc_btnConnexion.gridwidth = 3;
		gbc_btnConnexion.insets = new Insets(5, 5, 0, 5);
		gbc_btnConnexion.gridx = 2;
		gbc_btnConnexion.gridy = 6;
		panel.add(btnConnexion, gbc_btnConnexion);




	}

	/**
	 * @return
	 */
	protected boolean isValidUser(String username, String password) {

		boolean message = false;

		Utilisateur user = new Utilisateur();
		user = user.find(username);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Session.dateLogin = df.format(Calendar.getInstance().getTime());
		Session.nomAgent = user.getNom();
		Session.posteAgent = user.getPoste();
		Session.loginAgent = user.getLogin();
		

		if (!(user.getLogin().equalsIgnoreCase("undefined"))) {

			if (user.getPassword().equals(password)) {
				message = true;
				parent.poste = user.getPoste();
			}
		}

		return message;
	}


} // class
