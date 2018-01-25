/**
 * 
 */
package ca.qc.cgodin.Vue;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.Image;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.border.TitledBorder;

import ca.qc.cgodin.Modele.CompteCheque;
import ca.qc.cgodin.Modele.CompteEpargne;
import ca.qc.cgodin.Modele.ConnexionToDB;
import ca.qc.cgodin.Modele.Entreprise;
import ca.qc.cgodin.Modele.Particulier;
import ca.qc.cgodin.Modele.SQLQueries;
import ca.qc.cgodin.Modele.Session;

import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.SystemColor;

@SuppressWarnings("serial")
public class FenetreNouveau extends JInternalFrame {

	private BufferedImage img;
	private JTextField textNom;
	private JTextField textNas;
	private JTextField textCourriel;
	private JPanel panelIdentite;
	private JTextField textLimite;
	private JTextField textDepot;
	private JPanel panelCompte;
	private JPanel panelAdresse;
	private JPanel panelTelephone;
	private JTextField textPrincipal;
	private JTextField textPrincipalE;
	private JTextField textPoste;
	private JTextField textPosteE;
	private JTextField textSecondaire;
	private JTextField textSecondaireE;
	private JTextField textPosteSecondaire;
	private JTextField textPosteSecondaireE;
	private JTextField textMobile;
	private JTextField textMobileE;
	private JTextField textTelecopieur;
	private JTextField textTelecopieurE;
	private JTextField textCivic;
	private JTextField textRue;
	private JTextField textApp;
	private JTextField textVille;
	private JTextField textProvince;
	private JTextField textCodePostal;
	private JTextField textPays;
	private JPanel panelAdresseEnt;
	private JPanel panelInfoE;
	private JPanel panelTelephoneEnt;
	private JTextField textSuite;
	private JTextField textRueEnt;
	private JTextField textVilleEnt;
	private JTextField textProvinceEnt;
	private JTextField textCodePostalEnt;
	private JTextField textPaysEnt;
	private JTextField textNomEnt;
	private JTextField textNeq;
	private JTextField textCourrielE;
	private JTextField textUrl;
	private JTextField textSoldeMin;
	private JComboBox<String> cboTypePart;
	private JTextField textDepotE;
	private JTextField textLimiteE;
	private JTextField textSoldeMinE;
	private PreparedStatement[] psTab = null; /* I know a number of ps will be generated on new customer case */
	private ConnexionToDB instance = null; /* singleton instance */
	private JComboBox<String> cboDevise;
	private JComboBox<String> cboTypeE;
	private JTextField textNumRueEnt;
	private JComboBox<String> cboDevisePart;
	private JComboBox<String> cboSexe;
	private JTabbedPane tabbedPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	public FenetreNouveau() {

		super("", true, true, true, true);

		setFont(new Font("Tahoma", Font.BOLD, 13));
		setTitle("Nouveau client");
		setSize(950, 900);
		instance = ConnexionToDB.getInstance();
		psTab = new PreparedStatement[9];
		construireUI();

		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		this.setFrameIcon(new ImageIcon(img));
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				FenetreNouveau.this.setVisible(true);
			}
		});

	} //constructor


	/**
	 * 
	 */
	private void construireUI() {

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JScrollPane scroll = new JScrollPane(tabbedPane);
		getContentPane().add(scroll, BorderLayout.CENTER);

		JPanel tabParticulier = new JPanel();
		tabParticulier.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabbedPane.addTab("Particulier", getIcon("resources/icons8-Contract Job-48.png"), tabParticulier, null);
		tabParticulier.setLayout(new GridLayout(0, 2, 2, 2));

		panelIdentite = new JPanel();
		panelIdentite.setBorder(new TitledBorder(null, "Identite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabParticulier.add(panelIdentite);
		uiForIdentite();


		panelCompte = new JPanel();
		panelCompte.setBorder(new TitledBorder(null, "Info compte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabParticulier.add(panelCompte);
		uiForInfoCompte();

		panelAdresse = new JPanel();
		panelAdresse.setBorder(new TitledBorder(null, "Adresse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabParticulier.add(panelAdresse);
		uiForAdresse();

		panelTelephone = new JPanel();
		panelTelephone.setBorder(new TitledBorder(null, "Telephone", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabParticulier.add(panelTelephone);
		uiForTelephone();

		JPanel tabEntreprise = new JPanel();
		tabbedPane.addTab("Entreprise", getIcon("resources/icons8-Business-50 (1).png"), tabEntreprise, null);
		tabEntreprise.setLayout(new GridLayout(0, 1, 2, 2));

		panelInfoE = new JPanel();
		panelInfoE.setPreferredSize(new Dimension(500, 250));
		panelInfoE.setMinimumSize(new Dimension(0, 0));

		panelInfoE.setBorder(new TitledBorder(null, "Identite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabEntreprise.add(panelInfoE);
		GridBagLayout gbl_panelInfoE = new GridBagLayout();
		gbl_panelInfoE.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelInfoE.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelInfoE.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelInfoE.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInfoE.setLayout(gbl_panelInfoE);

		JLabel lblNomEnt = new JLabel("Nom: ");
		lblNomEnt.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblNomEnt = new GridBagConstraints();
		gbc_lblNomEnt.gridwidth = 2;
		gbc_lblNomEnt.insets = new Insets(5, 5, 5, 5);
		gbc_lblNomEnt.ipady = 10;
		gbc_lblNomEnt.ipadx = 10;
		gbc_lblNomEnt.anchor = GridBagConstraints.LINE_END;
		gbc_lblNomEnt.gridx = 1;
		gbc_lblNomEnt.gridy = 0;
		panelInfoE.add(lblNomEnt, gbc_lblNomEnt);

		textNomEnt = new JTextField();
		textNomEnt.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_textNomEnt = new GridBagConstraints();
		gbc_textNomEnt.insets = new Insets(5, 5, 5, 5);
		gbc_textNomEnt.anchor = GridBagConstraints.NORTHWEST;
		gbc_textNomEnt.gridx = 4;
		gbc_textNomEnt.gridy = 0;
		panelInfoE.add(textNomEnt, gbc_textNomEnt);
		textNomEnt.setColumns(27);

		JLabel lblNeq = new JLabel("NEQ: ");
		lblNeq.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblNeq = new GridBagConstraints();
		gbc_lblNeq.gridwidth = 2;
		gbc_lblNeq.ipady = 10;
		gbc_lblNeq.ipadx = 10;
		gbc_lblNeq.anchor = GridBagConstraints.EAST;
		gbc_lblNeq.insets = new Insets(5, 5, 5, 5);
		gbc_lblNeq.gridx = 5;
		gbc_lblNeq.gridy = 0;
		panelInfoE.add(lblNeq, gbc_lblNeq);

		textNeq = new JTextField();
		textNeq.setFont(new Font("Dialog", Font.BOLD, 12));
		textNeq.setColumns(27);
		GridBagConstraints gbc_textNeq = new GridBagConstraints();
		gbc_textNeq.anchor = GridBagConstraints.NORTHWEST;
		gbc_textNeq.insets = new Insets(5, 5, 5, 5);
		gbc_textNeq.gridx = 7;
		gbc_textNeq.gridy = 0;
		panelInfoE.add(textNeq, gbc_textNeq);

		JLabel lblCourriel = new JLabel("Courriel: ");
		lblCourriel.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblCourriel = new GridBagConstraints();
		gbc_lblCourriel.gridwidth = 2;
		gbc_lblCourriel.ipady = 10;
		gbc_lblCourriel.ipadx = 10;
		gbc_lblCourriel.anchor = GridBagConstraints.EAST;
		gbc_lblCourriel.insets = new Insets(5, 5, 5, 5);
		gbc_lblCourriel.gridx = 1;
		gbc_lblCourriel.gridy = 1;
		panelInfoE.add(lblCourriel, gbc_lblCourriel);

		textCourrielE = new JTextField();
		textCourrielE.setFont(new Font("Dialog", Font.BOLD, 12));
		textCourrielE.setColumns(27);
		GridBagConstraints gbc_textCourrielE = new GridBagConstraints();
		gbc_textCourrielE.anchor = GridBagConstraints.NORTHWEST;
		gbc_textCourrielE.insets = new Insets(5, 5, 5, 5);
		gbc_textCourrielE.gridx = 4;
		gbc_textCourrielE.gridy = 1;
		panelInfoE.add(textCourrielE, gbc_textCourrielE);

		JLabel lblUrl = new JLabel("Url: ");
		lblUrl.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblUrl = new GridBagConstraints();
		gbc_lblUrl.gridwidth = 2;
		gbc_lblUrl.ipady = 10;
		gbc_lblUrl.ipadx = 10;
		gbc_lblUrl.anchor = GridBagConstraints.LINE_END;
		gbc_lblUrl.insets = new Insets(5, 5, 5, 5);
		gbc_lblUrl.gridx = 5;
		gbc_lblUrl.gridy = 1;
		panelInfoE.add(lblUrl, gbc_lblUrl);

		textUrl = new JTextField();
		textUrl.setFont(new Font("Dialog", Font.BOLD, 12));
		textUrl.setColumns(27);
		GridBagConstraints gbc_textUrl = new GridBagConstraints();
		gbc_textUrl.anchor = GridBagConstraints.NORTHWEST;
		gbc_textUrl.insets = new Insets(5, 5, 5, 5);
		gbc_textUrl.gridx = 7;
		gbc_textUrl.gridy = 1;
		panelInfoE.add(textUrl, gbc_textUrl);

		JLabel lblTypeE = new JLabel("Type: ");
		lblTypeE.setMinimumSize(new Dimension(0, 14));
		lblTypeE.setHorizontalAlignment(SwingConstants.LEFT);
		lblTypeE.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblTypeE = new GridBagConstraints();
		gbc_lblTypeE.gridwidth = 2;
		gbc_lblTypeE.ipady = 10;
		gbc_lblTypeE.ipadx = 10;
		gbc_lblTypeE.anchor = GridBagConstraints.LINE_END;
		gbc_lblTypeE.insets = new Insets(5, 5, 5, 5);
		gbc_lblTypeE.gridx = 1;
		gbc_lblTypeE.gridy = 2;
		panelInfoE.add(lblTypeE, gbc_lblTypeE);

		cboTypeE = new JComboBox<String>();
		cboTypeE.setModel(new DefaultComboBoxModel<String>(new String[] {"Cheque", "Epargne"}));

		cboTypeE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(cboTypeE.getSelectedIndex() == 0) {

					textSoldeMinE.setEnabled(false);
					textSoldeMinE.setBackground(Color.BLUE);
					textLimiteE.setEnabled(true);
					textLimiteE.setBackground(Color.WHITE);

				} else {
					textSoldeMinE.setEnabled(true);
					textSoldeMinE.setBackground(Color.WHITE);
					textLimiteE.setEnabled(false);
					textLimiteE.setBackground(Color.BLUE);

				}

			}
		});


		GridBagConstraints gbc_cboTypeE = new GridBagConstraints();
		gbc_cboTypeE.anchor = GridBagConstraints.LINE_START;
		gbc_cboTypeE.insets = new Insets(5, 5, 5, 5);
		gbc_cboTypeE.gridx = 3;
		gbc_cboTypeE.gridy = 2;
		panelInfoE.add(cboTypeE, gbc_cboTypeE);

		JLabel lblDepotE = new JLabel("Depot: ");
		lblDepotE.setMinimumSize(new Dimension(0, 14));
		lblDepotE.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepotE.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblDepotE = new GridBagConstraints();
		gbc_lblDepotE.gridwidth = 2;
		gbc_lblDepotE.ipady = 10;
		gbc_lblDepotE.ipadx = 10;
		gbc_lblDepotE.anchor = GridBagConstraints.EAST;
		gbc_lblDepotE.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepotE.gridx = 5;
		gbc_lblDepotE.gridy = 2;
		panelInfoE.add(lblDepotE, gbc_lblDepotE);

		textDepotE = new JTextField();
		textDepotE.setMinimumSize(new Dimension(0, 20));
		textDepotE.setFont(new Font("DialogInput", Font.BOLD, 12));
		textDepotE.setColumns(14);
		GridBagConstraints gbc_textDepotE = new GridBagConstraints();
		gbc_textDepotE.anchor = GridBagConstraints.NORTHWEST;
		gbc_textDepotE.insets = new Insets(5, 5, 5, 5);
		gbc_textDepotE.gridx = 7;
		gbc_textDepotE.gridy = 2;
		panelInfoE.add(textDepotE, gbc_textDepotE);

		JLabel lblDeviseE = new JLabel("Devise: ");
		lblDeviseE.setMinimumSize(new Dimension(0, 20));
		lblDeviseE.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblDeviseE = new GridBagConstraints();
		gbc_lblDeviseE.anchor = GridBagConstraints.LINE_END;
		gbc_lblDeviseE.ipady = 10;
		gbc_lblDeviseE.ipadx = 10;
		gbc_lblDeviseE.gridwidth = 2;
		gbc_lblDeviseE.insets = new Insets(5, 5, 5, 5);
		gbc_lblDeviseE.gridx = 1;
		gbc_lblDeviseE.gridy = 3;
		panelInfoE.add(lblDeviseE, gbc_lblDeviseE);

		cboDevise = new JComboBox<String>();
		cboDevise.setModel(new DefaultComboBoxModel<String>(new String[] {"CAD", "USD", "EUR", "YEN", "PND"}));
		GridBagConstraints gbc_cboDevise = new GridBagConstraints();
		gbc_cboDevise.anchor = GridBagConstraints.LINE_START;
		gbc_cboDevise.insets = new Insets(5, 5, 5, 5);
		gbc_cboDevise.gridx = 3;
		gbc_cboDevise.gridy = 3;
		panelInfoE.add(cboDevise, gbc_cboDevise);

		JLabel lblLimiteE = new JLabel("Limite decouvert: ");
		lblLimiteE.setMinimumSize(new Dimension(0, 14));
		lblLimiteE.setHorizontalAlignment(SwingConstants.LEFT);
		lblLimiteE.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblLimiteE = new GridBagConstraints();
		gbc_lblLimiteE.insets = new Insets(0, 0, 5, 5);
		gbc_lblLimiteE.gridx = 4;
		gbc_lblLimiteE.gridy = 3;
		panelInfoE.add(lblLimiteE, gbc_lblLimiteE);

		JLabel lblSoldeMinE = new JLabel("Solde minimale: ");
		lblSoldeMinE.setMinimumSize(new Dimension(0, 14));
		lblSoldeMinE.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoldeMinE.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblSoldeMinE = new GridBagConstraints();
		gbc_lblSoldeMinE.insets = new Insets(0, 0, 5, 5);
		gbc_lblSoldeMinE.gridx = 7;
		gbc_lblSoldeMinE.gridy = 3;
		panelInfoE.add(lblSoldeMinE, gbc_lblSoldeMinE);

		textLimiteE = new JTextField();
		textLimiteE.setMinimumSize(new Dimension(0, 20));
		textLimiteE.setFont(new Font("DialogInput", Font.BOLD, 12));
		textLimiteE.setColumns(14);
		GridBagConstraints gbc_textLimiteE = new GridBagConstraints();
		gbc_textLimiteE.insets = new Insets(5, 5, 5, 5);
		gbc_textLimiteE.gridx = 4;
		gbc_textLimiteE.gridy = 4;
		panelInfoE.add(textLimiteE, gbc_textLimiteE);
		
		lblNewLabel_1 = new JLabel(new ImageIcon("resources/icons8-Business-50 (1).png"));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridheight = 2;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 5;
		gbc_lblNewLabel_1.gridy = 4;
		panelInfoE.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textSoldeMinE = new JTextField();
		textSoldeMinE.setMinimumSize(new Dimension(0, 20));
		textSoldeMinE.setFont(new Font("DialogInput", Font.BOLD, 12));
		textSoldeMinE.setEnabled(false);
		textSoldeMinE.setColumns(14);
		textSoldeMinE.setBackground(Color.BLUE);
		GridBagConstraints gbc_textSoldeMinE = new GridBagConstraints();
		gbc_textSoldeMinE.insets = new Insets(5, 5, 5, 5);
		gbc_textSoldeMinE.gridx = 7;
		gbc_textSoldeMinE.gridy = 4;
		panelInfoE.add(textSoldeMinE, gbc_textSoldeMinE);
		uiForInfoE();
		uiForCompteE();

		uiForAdresseEnt();
		uiForTelephoneEnt();

		JPanel panel = new JPanel();
		tabEntreprise.add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));
		panelAdresseEnt = new JPanel();
		panel.add(panelAdresseEnt);
		panelAdresseEnt.setBorder(new TitledBorder(null, "Adresse", TitledBorder.LEADING, TitledBorder.TOP, null, null));



		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAdresseEnt.setLayout(gbl_panel);

		JLabel lblSuite = new JLabel("Suite: ");
		lblSuite.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblCivic = new GridBagConstraints();
		gbc_lblCivic.ipady = 10;
		gbc_lblCivic.ipadx = 10;
		gbc_lblCivic.anchor = GridBagConstraints.EAST;
		gbc_lblCivic.insets = new Insets(5, 5, 5, 5);
		gbc_lblCivic.gridx = 0;
		gbc_lblCivic.gridy = 0;
		panelAdresseEnt.add(lblSuite, gbc_lblCivic);

		textSuite = new JTextField();
		textSuite.setColumns(7);
		GridBagConstraints gbc_textCivic = new GridBagConstraints();
		gbc_textCivic.anchor = GridBagConstraints.NORTHWEST;
		gbc_textCivic.insets = new Insets(5, 5, 5, 5);
		gbc_textCivic.gridx = 1;
		gbc_textCivic.gridy = 0;
		panelAdresseEnt.add(textSuite, gbc_textCivic);

		JLabel lblRueEnt = new JLabel("Rue: ");
		lblRueEnt.setMinimumSize(new Dimension(0, 14));
		lblRueEnt.setHorizontalAlignment(SwingConstants.LEFT);
		lblRueEnt.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblRue = new GridBagConstraints();
		gbc_lblRue.insets = new Insets(0, 0, 5, 5);
		gbc_lblRue.weightx = 1.0;
		gbc_lblRue.anchor = GridBagConstraints.EAST;
		gbc_lblRue.ipadx = 10;
		gbc_lblRue.ipady = 10;
		gbc_lblRue.gridx = 0;
		gbc_lblRue.gridy = 1;
		panelAdresseEnt.add(lblRueEnt, gbc_lblRue);

		textRueEnt = new JTextField();
		lblRueEnt.setLabelFor(textRueEnt);
		textRueEnt.setFont(new Font("DialogInput", Font.BOLD, 12));
		textRueEnt.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textRue = new GridBagConstraints();
		gbc_textRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRue.insets = new Insets(5, 5, 5, 5);
		gbc_textRue.anchor = GridBagConstraints.NORTHWEST;
		gbc_textRue.gridx = 1;
		gbc_textRue.gridy = 1;
		panelAdresseEnt.add(textRueEnt, gbc_textRue);
		textRueEnt.setColumns(14);

		JLabel lblNumeroRueE = new JLabel("Numero:  ");
		lblNumeroRueE.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblNumeroRueE = new GridBagConstraints();
		gbc_lblNumeroRueE.ipady = 10;
		gbc_lblNumeroRueE.ipadx = 10;
		gbc_lblNumeroRueE.anchor = GridBagConstraints.LINE_END;
		gbc_lblNumeroRueE.insets = new Insets(5, 5, 5, 5);
		gbc_lblNumeroRueE.gridx = 3;
		gbc_lblNumeroRueE.gridy = 1;
		panelAdresseEnt.add(lblNumeroRueE, gbc_lblNumeroRueE);

		textNumRueEnt = new JTextField();
		textNumRueEnt.setColumns(7);
		GridBagConstraints gbc_textNumRueEnt = new GridBagConstraints();
		gbc_textNumRueEnt.ipady = 2;
		gbc_textNumRueEnt.ipadx = 2;
		gbc_textNumRueEnt.anchor = GridBagConstraints.NORTHWEST;
		gbc_textNumRueEnt.insets = new Insets(5, 5, 5, 5);
		gbc_textNumRueEnt.gridx = 4;
		gbc_textNumRueEnt.gridy = 1;
		panelAdresseEnt.add(textNumRueEnt, gbc_textNumRueEnt);


		JLabel lblVilleEnt = new JLabel("Ville: ");
		lblVilleEnt.setMinimumSize(new Dimension(0, 14));
		lblVilleEnt.setHorizontalAlignment(SwingConstants.LEFT);
		lblVilleEnt.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.ipady = 10;
		gbc_lblVille.ipadx = 10;
		gbc_lblVille.anchor = GridBagConstraints.LINE_END;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 0;
		gbc_lblVille.gridy = 2;
		panelAdresseEnt.add(lblVilleEnt, gbc_lblVille);

		textVilleEnt = new JTextField();
		textVilleEnt.setMinimumSize(new Dimension(0, 20));
		textVilleEnt.setFont(new Font("DialogInput", Font.BOLD, 12));
		textVilleEnt.setColumns(14);
		GridBagConstraints gbc_textVille = new GridBagConstraints();
		gbc_textVille.anchor = GridBagConstraints.NORTHWEST;
		gbc_textVille.insets = new Insets(5, 5, 5, 5);
		gbc_textVille.gridx = 1;
		gbc_textVille.gridy = 2;
		panelAdresseEnt.add(textVilleEnt, gbc_textVille);

		JLabel lblProvince = new JLabel("Province: ");
		lblProvince.setMinimumSize(new Dimension(0, 14));
		lblProvince.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvince.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblProvince = new GridBagConstraints();
		gbc_lblProvince.anchor = GridBagConstraints.LINE_END;
		gbc_lblProvince.ipady = 10;
		gbc_lblProvince.ipadx = 10;
		gbc_lblProvince.insets = new Insets(0, 0, 5, 5);
		gbc_lblProvince.gridx = 3;
		gbc_lblProvince.gridy = 2;
		panelAdresseEnt.add(lblProvince, gbc_lblProvince);

		textProvinceEnt = new JTextField();
		textProvinceEnt.setMinimumSize(new Dimension(0, 20));
		textProvinceEnt.setFont(new Font("DialogInput", Font.BOLD, 12));
		textProvinceEnt.setColumns(14);
		GridBagConstraints gbc_textProvince = new GridBagConstraints();
		gbc_textProvince.anchor = GridBagConstraints.LINE_START;
		gbc_textProvince.insets = new Insets(5, 5, 5, 5);
		gbc_textProvince.gridx = 4;
		gbc_textProvince.gridy = 2;
		panelAdresseEnt.add(textProvinceEnt, gbc_textProvince);

		JLabel lblCodePostal = new JLabel("Code postal: ");
		lblCodePostal.setMinimumSize(new Dimension(0, 14));
		lblCodePostal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodePostal.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.LINE_END;
		gbc_lblCodePostal.ipady = 10;
		gbc_lblCodePostal.ipadx = 10;
		gbc_lblCodePostal.insets = new Insets(5, 5, 5, 5);
		gbc_lblCodePostal.gridx = 0;
		gbc_lblCodePostal.gridy = 3;
		panelAdresseEnt.add(lblCodePostal, gbc_lblCodePostal);

		textCodePostalEnt = new JTextField();
		textCodePostalEnt.setMinimumSize(new Dimension(0, 20));
		textCodePostalEnt.setFont(new Font("DialogInput", Font.BOLD, 12));
		textCodePostalEnt.setColumns(7);
		GridBagConstraints gbc_textCodePostal = new GridBagConstraints();
		gbc_textCodePostal.anchor = GridBagConstraints.NORTHWEST;
		gbc_textCodePostal.insets = new Insets(5, 5, 5, 5);
		gbc_textCodePostal.gridx = 1;
		gbc_textCodePostal.gridy = 3;
		panelAdresseEnt.add(textCodePostalEnt, gbc_textCodePostal);

		JLabel label = new JLabel("Pays: ");
		label.setMinimumSize(new Dimension(0, 14));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.ipady = 10;
		gbc_label.ipadx = 10;
		gbc_label.anchor = GridBagConstraints.LINE_END;
		gbc_label.insets = new Insets(5, 5, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 3;
		panelAdresseEnt.add(label, gbc_label);

		textPaysEnt = new JTextField();
		textPaysEnt.setMinimumSize(new Dimension(0, 20));
		textPaysEnt.setFont(new Font("DialogInput", Font.BOLD, 12));
		textPaysEnt.setColumns(14);
		GridBagConstraints gbc_textPaysEnt = new GridBagConstraints();
		gbc_textPaysEnt.anchor = GridBagConstraints.LINE_START;
		gbc_textPaysEnt.insets = new Insets(5, 5, 5, 5);
		gbc_textPaysEnt.gridx = 4;
		gbc_textPaysEnt.gridy = 3;
		panelAdresseEnt.add(textPaysEnt, gbc_textPaysEnt);
		panelTelephoneEnt = new JPanel();
		panel.add(panelTelephoneEnt);
		panelTelephoneEnt.setBorder(new TitledBorder(null, "Telephone", TitledBorder.LEADING, TitledBorder.TOP, null, null));


		GridBagLayout gbl_panelInfoCompte = new GridBagLayout();
		gbl_panelInfoCompte.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelInfoCompte.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelInfoCompte.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelInfoCompte.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTelephoneEnt.setLayout(gbl_panelInfoCompte);

		JLabel lblPrincipal = new JLabel("Telephone principal: ");
		lblPrincipal.setMinimumSize(new Dimension(0, 14));
		lblPrincipal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrincipal.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblPrincipal = new GridBagConstraints();
		gbc_lblPrincipal.gridwidth = 2;
		gbc_lblPrincipal.weightx = 1.0;
		gbc_lblPrincipal.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrincipal.anchor = GridBagConstraints.LINE_END;
		gbc_lblPrincipal.ipadx = 10;
		gbc_lblPrincipal.ipady = 10;
		gbc_lblPrincipal.gridx = 0;
		gbc_lblPrincipal.gridy = 0;
		panelTelephoneEnt.add(lblPrincipal, gbc_lblPrincipal);

		textPrincipalE = new JTextField();
		lblPrincipal.setLabelFor(textPrincipalE);
		textPrincipalE.setFont(new Font("DialogInput", Font.BOLD, 12));
		textPrincipalE.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textPrincipalE = new GridBagConstraints();
		gbc_textPrincipalE.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPrincipalE.insets = new Insets(5, 5, 5, 5);
		gbc_textPrincipalE.anchor = GridBagConstraints.LINE_START;
		gbc_textPrincipalE.gridx = 2;
		gbc_textPrincipalE.gridy = 0;
		panelTelephoneEnt.add(textPrincipalE, gbc_textPrincipalE);
		textPrincipalE.setColumns(14);

		JLabel lblPoste = new JLabel("Poste: ");
		lblPoste.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblPoste = new GridBagConstraints();
		gbc_lblPoste.ipady = 10;
		gbc_lblPoste.ipadx = 10;
		gbc_lblPoste.anchor = GridBagConstraints.LINE_END;
		gbc_lblPoste.insets = new Insets(5, 5, 5, 5);
		gbc_lblPoste.gridx = 3;
		gbc_lblPoste.gridy = 0;
		panelTelephoneEnt.add(lblPoste, gbc_lblPoste);

		textPosteE = new JTextField();
		GridBagConstraints gbc_textPosteE = new GridBagConstraints();
		gbc_textPosteE.anchor = GridBagConstraints.LINE_START;
		gbc_textPosteE.insets = new Insets(5, 5, 5, 5);
		gbc_textPosteE.gridx = 4;
		gbc_textPosteE.gridy = 0;
		panelTelephoneEnt.add(textPosteE, gbc_textPosteE);
		textPosteE.setColumns(7);

		JLabel lbalSecondaire = new JLabel("Telephone secondaire: ");
		lbalSecondaire.setMinimumSize(new Dimension(0, 14));
		lbalSecondaire.setHorizontalAlignment(SwingConstants.LEFT);
		lbalSecondaire.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lbalSecondaire = new GridBagConstraints();
		gbc_lbalSecondaire.ipady = 10;
		gbc_lbalSecondaire.ipadx = 10;
		gbc_lbalSecondaire.gridwidth = 2;
		gbc_lbalSecondaire.anchor = GridBagConstraints.LINE_END;
		gbc_lbalSecondaire.insets = new Insets(0, 0, 5, 5);
		gbc_lbalSecondaire.gridx = 0;
		gbc_lbalSecondaire.gridy = 1;
		panelTelephoneEnt.add(lbalSecondaire, gbc_lbalSecondaire);

		textSecondaireE = new JTextField();
		textSecondaireE.setMinimumSize(new Dimension(0, 20));
		textSecondaireE.setFont(new Font("DialogInput", Font.BOLD, 12));
		textSecondaireE.setColumns(14);
		GridBagConstraints gbc_textSecondaireE = new GridBagConstraints();
		gbc_textSecondaireE.anchor = GridBagConstraints.NORTHWEST;
		gbc_textSecondaireE.insets = new Insets(5, 5, 5, 5);
		gbc_textSecondaireE.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSecondaireE.gridx = 2;
		gbc_textSecondaireE.gridy = 1;
		panelTelephoneEnt.add(textSecondaireE, gbc_textSecondaireE);

		JLabel lblPosteSecond = new JLabel("Poste: ");
		lblPosteSecond.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblPosteSecond = new GridBagConstraints();
		gbc_lblPosteSecond.ipady = 10;
		gbc_lblPosteSecond.ipadx = 10;
		gbc_lblPosteSecond.anchor = GridBagConstraints.EAST;
		gbc_lblPosteSecond.insets = new Insets(0, 0, 5, 5);
		gbc_lblPosteSecond.gridx = 3;
		gbc_lblPosteSecond.gridy = 1;
		panelTelephoneEnt.add(lblPosteSecond, gbc_lblPosteSecond);

		textPosteSecondaireE = new JTextField();
		textPosteSecondaireE.setColumns(7);
		GridBagConstraints gbc_textPosteSecondaireE = new GridBagConstraints();
		gbc_textPosteSecondaireE.anchor = GridBagConstraints.LINE_START;
		gbc_textPosteSecondaireE.insets = new Insets(5, 5, 5, 5);
		gbc_textPosteSecondaireE.gridx = 4;
		gbc_textPosteSecondaireE.gridy = 1;
		panelTelephoneEnt.add(textPosteSecondaireE, gbc_textPosteSecondaireE);

		JLabel lblMobile = new JLabel("Telephone mobile: ");
		lblMobile.setMinimumSize(new Dimension(0, 14));
		lblMobile.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobile.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblMobile = new GridBagConstraints();
		gbc_lblMobile.anchor = GridBagConstraints.LINE_END;
		gbc_lblMobile.ipady = 10;
		gbc_lblMobile.ipadx = 10;
		gbc_lblMobile.gridwidth = 2;
		gbc_lblMobile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMobile.gridx = 0;
		gbc_lblMobile.gridy = 2;
		panelTelephoneEnt.add(lblMobile, gbc_lblMobile);

		textMobileE = new JTextField();
		textMobileE.setMinimumSize(new Dimension(0, 20));
		textMobileE.setFont(new Font("DialogInput", Font.BOLD, 12));
		textMobileE.setColumns(14);
		GridBagConstraints gbc_textMobileE = new GridBagConstraints();
		gbc_textMobileE.insets = new Insets(5, 5, 5, 5);
		gbc_textMobileE.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMobileE.gridx = 2;
		gbc_textMobileE.gridy = 2;
		panelTelephoneEnt.add(textMobileE, gbc_textMobileE);

		JLabel lblTelecopieur = new JLabel("Telecopieur: ");
		lblTelecopieur.setMinimumSize(new Dimension(0, 14));
		lblTelecopieur.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelecopieur.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblTelecopieur = new GridBagConstraints();
		gbc_lblTelecopieur.anchor = GridBagConstraints.LINE_END;
		gbc_lblTelecopieur.ipady = 10;
		gbc_lblTelecopieur.ipadx = 10;
		gbc_lblTelecopieur.gridwidth = 2;
		gbc_lblTelecopieur.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelecopieur.gridx = 0;
		gbc_lblTelecopieur.gridy = 3;
		panelTelephoneEnt.add(lblTelecopieur, gbc_lblTelecopieur);

		textTelecopieurE = new JTextField();
		textTelecopieurE.setMinimumSize(new Dimension(0, 20));
		textTelecopieurE.setFont(new Font("DialogInput", Font.BOLD, 12));
		textTelecopieurE.setColumns(14);
		GridBagConstraints gbc_textTelecopieurE = new GridBagConstraints();
		gbc_textTelecopieurE.anchor = GridBagConstraints.NORTHWEST;
		gbc_textTelecopieurE.insets = new Insets(5, 5, 5, 5);
		gbc_textTelecopieurE.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelecopieurE.gridx = 2;
		gbc_textTelecopieurE.gridy = 3;
		panelTelephoneEnt.add(textTelecopieurE, gbc_textTelecopieurE);

		JButton btnOuvrirEnt = new JButton("Nouveau Client");
		btnOuvrirEnt.setOpaque(false);
		btnOuvrirEnt.setFont(new Font("Dialog", Font.BOLD, 13));
		btnOuvrirEnt.setBackground(SystemColor.inactiveCaption);

		btnOuvrirEnt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// lancer requetes

				if(cboTypeE.getSelectedIndex() == 0) {

					preparePreparedStatementTab(2);
				}else {

					preparePreparedStatementTab(3);
				}

			}
		});

		GridBagConstraints gbc_btnOuvrirEnt = new GridBagConstraints();
		gbc_btnOuvrirEnt.ipady = 10;
		gbc_btnOuvrirEnt.ipadx = 10;
		gbc_btnOuvrirEnt.gridwidth = 3;
		gbc_btnOuvrirEnt.insets = new Insets(10, 10, 10, 10);
		gbc_btnOuvrirEnt.gridx = 3;
		gbc_btnOuvrirEnt.gridy = 6;
		panelTelephoneEnt.add(btnOuvrirEnt, gbc_btnOuvrirEnt);


	}


	/**
	 * 
	 */
	private void uiForTelephoneEnt() {


	}


	/**
	 * 
	 */
	private void uiForAdresseEnt() {



	}


	/**
	 * 
	 */
	private void uiForCompteE() {
		// TODO Auto-generated method stub

	}


	/**
	 * 
	 */
	private void uiForInfoE() {
		// TODO Auto-generated method stub

	}


	/**
	 * 
	 */
	private void uiForAdresse() {

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelAdresse.setLayout(gbl_panel);

		JLabel lblCivic = new JLabel("Numero civic: ");
		lblCivic.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblCivic = new GridBagConstraints();
		gbc_lblCivic.ipady = 10;
		gbc_lblCivic.ipadx = 10;
		gbc_lblCivic.anchor = GridBagConstraints.EAST;
		gbc_lblCivic.insets = new Insets(5, 5, 5, 5);
		gbc_lblCivic.gridx = 0;
		gbc_lblCivic.gridy = 1;
		panelAdresse.add(lblCivic, gbc_lblCivic);

		textCivic = new JTextField();
		textCivic.setColumns(7);
		GridBagConstraints gbc_textCivic = new GridBagConstraints();
		gbc_textCivic.anchor = GridBagConstraints.NORTHWEST;
		gbc_textCivic.insets = new Insets(5, 5, 5, 5);
		gbc_textCivic.gridx = 2;
		gbc_textCivic.gridy = 1;
		panelAdresse.add(textCivic, gbc_textCivic);

		JLabel lblRue = new JLabel("Rue: ");
		lblRue.setMinimumSize(new Dimension(0, 14));
		lblRue.setHorizontalAlignment(SwingConstants.LEFT);
		lblRue.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblRue = new GridBagConstraints();
		gbc_lblRue.insets = new Insets(0, 0, 5, 5);
		gbc_lblRue.weightx = 1.0;
		gbc_lblRue.anchor = GridBagConstraints.EAST;
		gbc_lblRue.ipadx = 10;
		gbc_lblRue.ipady = 10;
		gbc_lblRue.gridx = 0;
		gbc_lblRue.gridy = 2;
		panelAdresse.add(lblRue, gbc_lblRue);

		textRue = new JTextField();
		lblRue.setLabelFor(textRue);
		textRue.setFont(new Font("DialogInput", Font.BOLD, 12));
		textRue.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textRue = new GridBagConstraints();
		gbc_textRue.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRue.insets = new Insets(5, 5, 5, 5);
		gbc_textRue.anchor = GridBagConstraints.NORTHWEST;
		gbc_textRue.gridx = 2;
		gbc_textRue.gridy = 2;
		panelAdresse.add(textRue, gbc_textRue);
		textRue.setColumns(14);

		JLabel lblApart = new JLabel("App: ");
		lblApart.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblApart = new GridBagConstraints();
		gbc_lblApart.ipady = 10;
		gbc_lblApart.ipadx = 10;
		gbc_lblApart.anchor = GridBagConstraints.EAST;
		gbc_lblApart.insets = new Insets(5, 5, 5, 5);
		gbc_lblApart.gridx = 3;
		gbc_lblApart.gridy = 2;
		panelAdresse.add(lblApart, gbc_lblApart);

		textApp = new JTextField();
		GridBagConstraints gbc_textApp = new GridBagConstraints();
		gbc_textApp.anchor = GridBagConstraints.LINE_START;
		gbc_textApp.insets = new Insets(5, 5, 5, 5);
		gbc_textApp.gridx = 4;
		gbc_textApp.gridy = 2;
		panelAdresse.add(textApp, gbc_textApp);
		textApp.setColumns(7);

		JLabel lblVille = new JLabel("Ville: ");
		lblVille.setMinimumSize(new Dimension(0, 14));
		lblVille.setHorizontalAlignment(SwingConstants.LEFT);
		lblVille.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.ipady = 10;
		gbc_lblVille.ipadx = 10;
		gbc_lblVille.anchor = GridBagConstraints.LINE_END;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 0;
		gbc_lblVille.gridy = 3;
		panelAdresse.add(lblVille, gbc_lblVille);

		textVille = new JTextField();
		textVille.setMinimumSize(new Dimension(0, 20));
		textVille.setFont(new Font("DialogInput", Font.BOLD, 12));
		textVille.setColumns(14);
		GridBagConstraints gbc_textVille = new GridBagConstraints();
		gbc_textVille.anchor = GridBagConstraints.NORTHWEST;
		gbc_textVille.insets = new Insets(5, 5, 5, 5);
		gbc_textVille.gridx = 2;
		gbc_textVille.gridy = 3;
		panelAdresse.add(textVille, gbc_textVille);

		JLabel lblProvince = new JLabel("Province: ");
		lblProvince.setMinimumSize(new Dimension(0, 14));
		lblProvince.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvince.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblProvince = new GridBagConstraints();
		gbc_lblProvince.anchor = GridBagConstraints.LINE_END;
		gbc_lblProvince.ipady = 10;
		gbc_lblProvince.ipadx = 10;
		gbc_lblProvince.insets = new Insets(0, 0, 5, 5);
		gbc_lblProvince.gridx = 3;
		gbc_lblProvince.gridy = 3;
		panelAdresse.add(lblProvince, gbc_lblProvince);

		textProvince = new JTextField();
		textProvince.setMinimumSize(new Dimension(0, 20));
		textProvince.setFont(new Font("DialogInput", Font.BOLD, 12));
		textProvince.setColumns(14);
		GridBagConstraints gbc_textProvince = new GridBagConstraints();
		gbc_textProvince.anchor = GridBagConstraints.LINE_START;
		gbc_textProvince.insets = new Insets(5, 5, 5, 5);
		gbc_textProvince.gridx = 4;
		gbc_textProvince.gridy = 3;
		panelAdresse.add(textProvince, gbc_textProvince);

		JLabel lblCodePostal = new JLabel("Code postal: ");
		lblCodePostal.setMinimumSize(new Dimension(0, 14));
		lblCodePostal.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodePostal.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblCodePostal = new GridBagConstraints();
		gbc_lblCodePostal.anchor = GridBagConstraints.LINE_END;
		gbc_lblCodePostal.ipady = 10;
		gbc_lblCodePostal.ipadx = 10;
		gbc_lblCodePostal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodePostal.gridx = 0;
		gbc_lblCodePostal.gridy = 4;
		panelAdresse.add(lblCodePostal, gbc_lblCodePostal);

		textCodePostal = new JTextField();
		textCodePostal.setMinimumSize(new Dimension(0, 20));
		textCodePostal.setFont(new Font("DialogInput", Font.BOLD, 12));
		textCodePostal.setColumns(7);
		GridBagConstraints gbc_textCodePostal = new GridBagConstraints();
		gbc_textCodePostal.anchor = GridBagConstraints.NORTHWEST;
		gbc_textCodePostal.insets = new Insets(5, 5, 5, 5);
		gbc_textCodePostal.gridx = 2;
		gbc_textCodePostal.gridy = 4;
		panelAdresse.add(textCodePostal, gbc_textCodePostal);

		JLabel label = new JLabel("Pays: ");
		label.setMinimumSize(new Dimension(0, 14));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.ipady = 10;
		gbc_label.ipadx = 10;
		gbc_label.anchor = GridBagConstraints.LINE_END;
		gbc_label.insets = new Insets(5, 5, 5, 5);
		gbc_label.gridx = 3;
		gbc_label.gridy = 4;
		panelAdresse.add(label, gbc_label);

		textPays = new JTextField();
		textPays.setMinimumSize(new Dimension(0, 20));
		textPays.setFont(new Font("DialogInput", Font.BOLD, 12));
		textPays.setColumns(14);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.LINE_START;
		gbc_textField.insets = new Insets(5, 5, 5, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 4;
		panelAdresse.add(textPays, gbc_textField);

	}


	/**
	 * 
	 */
	private void uiForTelephone() {


		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTelephone.setLayout(gbl_panel);

		JLabel lblPrincipal = new JLabel("Telephone principal: ");
		lblPrincipal.setMinimumSize(new Dimension(0, 14));
		lblPrincipal.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrincipal.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblPrincipal = new GridBagConstraints();
		gbc_lblPrincipal.gridwidth = 2;
		gbc_lblPrincipal.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrincipal.weightx = 1.0;
		gbc_lblPrincipal.anchor = GridBagConstraints.EAST;
		gbc_lblPrincipal.ipadx = 10;
		gbc_lblPrincipal.ipady = 10;
		gbc_lblPrincipal.gridx = 0;
		gbc_lblPrincipal.gridy = 2;
		panelTelephone.add(lblPrincipal, gbc_lblPrincipal);

		textPrincipal = new JTextField();
		lblPrincipal.setLabelFor(textPrincipal);
		textPrincipal.setFont(new Font("DialogInput", Font.BOLD, 12));
		textPrincipal.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textPrincipal = new GridBagConstraints();
		gbc_textPrincipal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPrincipal.insets = new Insets(5, 5, 5, 5);
		gbc_textPrincipal.anchor = GridBagConstraints.NORTHWEST;
		gbc_textPrincipal.gridx = 2;
		gbc_textPrincipal.gridy = 2;
		panelTelephone.add(textPrincipal, gbc_textPrincipal);
		textPrincipal.setColumns(14);

		JLabel lblPoste = new JLabel("Poste: ");
		lblPoste.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblPoste = new GridBagConstraints();
		gbc_lblPoste.ipady = 10;
		gbc_lblPoste.ipadx = 10;
		gbc_lblPoste.anchor = GridBagConstraints.EAST;
		gbc_lblPoste.insets = new Insets(5, 5, 5, 5);
		gbc_lblPoste.gridx = 3;
		gbc_lblPoste.gridy = 2;
		panelTelephone.add(lblPoste, gbc_lblPoste);

		textPoste = new JTextField();
		GridBagConstraints gbc_textPoste = new GridBagConstraints();
		gbc_textPoste.anchor = GridBagConstraints.LINE_START;
		gbc_textPoste.insets = new Insets(5, 5, 5, 5);
		gbc_textPoste.gridx = 4;
		gbc_textPoste.gridy = 2;
		panelTelephone.add(textPoste, gbc_textPoste);
		textPoste.setColumns(7);

		JLabel lbalSecondaire = new JLabel("Telephone secondaire: ");
		lbalSecondaire.setMinimumSize(new Dimension(0, 14));
		lbalSecondaire.setHorizontalAlignment(SwingConstants.LEFT);
		lbalSecondaire.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lbalSecondaire = new GridBagConstraints();
		gbc_lbalSecondaire.ipady = 10;
		gbc_lbalSecondaire.ipadx = 10;
		gbc_lbalSecondaire.gridwidth = 2;
		gbc_lbalSecondaire.anchor = GridBagConstraints.LINE_END;
		gbc_lbalSecondaire.insets = new Insets(0, 0, 5, 5);
		gbc_lbalSecondaire.gridx = 0;
		gbc_lbalSecondaire.gridy = 3;
		panelTelephone.add(lbalSecondaire, gbc_lbalSecondaire);

		textSecondaire = new JTextField();
		textSecondaire.setMinimumSize(new Dimension(0, 20));
		textSecondaire.setFont(new Font("DialogInput", Font.BOLD, 12));
		textSecondaire.setColumns(14);
		GridBagConstraints gbc_textSecondaire = new GridBagConstraints();
		gbc_textSecondaire.anchor = GridBagConstraints.NORTHWEST;
		gbc_textSecondaire.insets = new Insets(5, 5, 5, 5);
		gbc_textSecondaire.fill = GridBagConstraints.HORIZONTAL;
		gbc_textSecondaire.gridx = 2;
		gbc_textSecondaire.gridy = 3;
		panelTelephone.add(textSecondaire, gbc_textSecondaire);

		JLabel lblPosteSecond = new JLabel("Poste: ");
		lblPosteSecond.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblPosteSecond = new GridBagConstraints();
		gbc_lblPosteSecond.ipady = 10;
		gbc_lblPosteSecond.ipadx = 10;
		gbc_lblPosteSecond.anchor = GridBagConstraints.EAST;
		gbc_lblPosteSecond.insets = new Insets(0, 0, 5, 5);
		gbc_lblPosteSecond.gridx = 3;
		gbc_lblPosteSecond.gridy = 3;
		panelTelephone.add(lblPosteSecond, gbc_lblPosteSecond);

		textPosteSecondaire = new JTextField();
		textPosteSecondaire.setColumns(7);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.LINE_START;
		gbc_textField.insets = new Insets(5, 5, 5, 5);
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 3;
		panelTelephone.add(textPosteSecondaire, gbc_textField);

		JLabel lblMobile = new JLabel("Telephone mobile: ");
		lblMobile.setMinimumSize(new Dimension(0, 14));
		lblMobile.setHorizontalAlignment(SwingConstants.LEFT);
		lblMobile.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblMobile = new GridBagConstraints();
		gbc_lblMobile.anchor = GridBagConstraints.LINE_END;
		gbc_lblMobile.ipady = 10;
		gbc_lblMobile.ipadx = 10;
		gbc_lblMobile.gridwidth = 2;
		gbc_lblMobile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMobile.gridx = 0;
		gbc_lblMobile.gridy = 4;
		panelTelephone.add(lblMobile, gbc_lblMobile);

		textMobile = new JTextField();
		textMobile.setMinimumSize(new Dimension(0, 20));
		textMobile.setFont(new Font("DialogInput", Font.BOLD, 12));
		textMobile.setColumns(14);
		GridBagConstraints gbc_textMobile = new GridBagConstraints();
		gbc_textMobile.insets = new Insets(5, 5, 5, 5);
		gbc_textMobile.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMobile.gridx = 2;
		gbc_textMobile.gridy = 4;
		panelTelephone.add(textMobile, gbc_textMobile);

		JLabel lblTelecopieur = new JLabel("Telecopieur: ");
		lblTelecopieur.setMinimumSize(new Dimension(0, 14));
		lblTelecopieur.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelecopieur.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblTelecopieur = new GridBagConstraints();
		gbc_lblTelecopieur.anchor = GridBagConstraints.LINE_END;
		gbc_lblTelecopieur.ipady = 10;
		gbc_lblTelecopieur.ipadx = 10;
		gbc_lblTelecopieur.gridwidth = 2;
		gbc_lblTelecopieur.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelecopieur.gridx = 0;
		gbc_lblTelecopieur.gridy = 5;
		panelTelephone.add(lblTelecopieur, gbc_lblTelecopieur);

		textTelecopieur = new JTextField();
		textTelecopieur.setMinimumSize(new Dimension(0, 20));
		textTelecopieur.setFont(new Font("DialogInput", Font.BOLD, 12));
		textTelecopieur.setColumns(14);
		GridBagConstraints gbc_textTelecopieur = new GridBagConstraints();
		gbc_textTelecopieur.anchor = GridBagConstraints.NORTHWEST;
		gbc_textTelecopieur.insets = new Insets(5, 5, 5, 5);
		gbc_textTelecopieur.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTelecopieur.gridx = 2;
		gbc_textTelecopieur.gridy = 5;
		panelTelephone.add(textTelecopieur, gbc_textTelecopieur);

		JButton btnOuvrirCompte = new JButton("Nouveau Client");
		btnOuvrirCompte.setBackground(SystemColor.inactiveCaption);
		btnOuvrirCompte.setOpaque(false);
		btnOuvrirCompte.setFont(new Font("Dialog", Font.BOLD, 13));
		btnOuvrirCompte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// lancer requetes

				if(cboTypePart.getSelectedIndex() == 0)
					preparePreparedStatementTab(0);
				else
					preparePreparedStatementTab(1);		

			}
		});
		GridBagConstraints gbc_btnOuvrirCompte = new GridBagConstraints();
		gbc_btnOuvrirCompte.ipady = 10;
		gbc_btnOuvrirCompte.ipadx = 10;
		gbc_btnOuvrirCompte.gridwidth = 2;
		gbc_btnOuvrirCompte.insets = new Insets(10, 10, 10, 10);
		gbc_btnOuvrirCompte.gridx = 3;
		gbc_btnOuvrirCompte.gridy = 7;
		panelTelephone.add(btnOuvrirCompte, gbc_btnOuvrirCompte);
	}


	/**
	 * 
	 */
	private void uiForInfoCompte() {

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCompte.setLayout(gbl_panel);

		JLabel lblType = new JLabel("Type: ");
		lblType.setMinimumSize(new Dimension(0, 14));
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.gridwidth = 2;
		gbc_lblType.ipady = 10;
		gbc_lblType.ipadx = 10;
		gbc_lblType.anchor = GridBagConstraints.LINE_END;
		gbc_lblType.insets = new Insets(5, 5, 5, 5);
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 1;
		panelCompte.add(lblType, gbc_lblType);

		cboTypePart = new JComboBox<String>();
		cboTypePart.setModel(new DefaultComboBoxModel<String>(new String[] {"Cheque\t", "Epargne"}));

		cboTypePart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(cboTypePart.getSelectedIndex() == 0) {

					textSoldeMin.setEnabled(false);
					textSoldeMin.setBackground(Color.BLUE);
					textLimite.setEnabled(true);
					textLimite.setBackground(Color.WHITE);


				} else {
					textLimite.setEnabled(false);
					textLimite.setBackground(Color.BLUE);
					textSoldeMin.setEnabled(true);
					textSoldeMin.setBackground(Color.WHITE);
				}

			}
		});
		GridBagConstraints gbc_cboTypePart = new GridBagConstraints();
		gbc_cboTypePart.anchor = GridBagConstraints.LINE_START;
		gbc_cboTypePart.insets = new Insets(5, 5, 5, 5);
		gbc_cboTypePart.gridx = 2;
		gbc_cboTypePart.gridy = 1;
		panelCompte.add(cboTypePart, gbc_cboTypePart);

		JLabel lblDepot = new JLabel("Depot: ");
		lblDepot.setMinimumSize(new Dimension(0, 14));
		lblDepot.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepot.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblDepot = new GridBagConstraints();
		gbc_lblDepot.gridwidth = 2;
		gbc_lblDepot.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepot.weightx = 1.0;
		gbc_lblDepot.anchor = GridBagConstraints.EAST;
		gbc_lblDepot.ipadx = 10;
		gbc_lblDepot.ipady = 10;
		gbc_lblDepot.gridx = 0;
		gbc_lblDepot.gridy = 2;
		panelCompte.add(lblDepot, gbc_lblDepot);

		textDepot = new JTextField();
		lblDepot.setLabelFor(textDepot);
		textDepot.setFont(new Font("DialogInput", Font.BOLD, 12));
		textDepot.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textDepot = new GridBagConstraints();
		gbc_textDepot.gridwidth = 4;
		gbc_textDepot.insets = new Insets(5, 5, 5, 0);
		gbc_textDepot.anchor = GridBagConstraints.NORTHWEST;
		gbc_textDepot.gridx = 2;
		gbc_textDepot.gridy = 2;
		panelCompte.add(textDepot, gbc_textDepot);
		textDepot.setColumns(14);

		JLabel lblLimite = new JLabel("Limite decouvert: ");
		lblLimite.setMinimumSize(new Dimension(0, 14));
		lblLimite.setHorizontalAlignment(SwingConstants.LEFT);
		lblLimite.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblLimite = new GridBagConstraints();
		gbc_lblLimite.ipady = 10;
		gbc_lblLimite.ipadx = 10;
		gbc_lblLimite.anchor = GridBagConstraints.LINE_END;
		gbc_lblLimite.gridwidth = 2;
		gbc_lblLimite.insets = new Insets(0, 0, 5, 5);
		gbc_lblLimite.gridx = 0;
		gbc_lblLimite.gridy = 3;
		panelCompte.add(lblLimite, gbc_lblLimite);

		textLimite = new JTextField();
		textLimite.setMinimumSize(new Dimension(0, 20));
		textLimite.setFont(new Font("DialogInput", Font.BOLD, 12));
		textLimite.setColumns(14);
		GridBagConstraints gbc_textLimite = new GridBagConstraints();
		gbc_textLimite.anchor = GridBagConstraints.NORTHWEST;
		gbc_textLimite.gridwidth = 4;
		gbc_textLimite.insets = new Insets(5, 5, 5, 0);
		gbc_textLimite.gridx = 2;
		gbc_textLimite.gridy = 3;
		panelCompte.add(textLimite, gbc_textLimite);

		JLabel lblSoldeMin = new JLabel("Solde minimale: ");
		lblSoldeMin.setMinimumSize(new Dimension(0, 14));
		lblSoldeMin.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoldeMin.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblSoldeMin = new GridBagConstraints();
		gbc_lblSoldeMin.ipady = 10;
		gbc_lblSoldeMin.ipadx = 10;
		gbc_lblSoldeMin.anchor = GridBagConstraints.LINE_END;
		gbc_lblSoldeMin.gridwidth = 2;
		gbc_lblSoldeMin.insets = new Insets(5, 5, 5, 5);
		gbc_lblSoldeMin.gridx = 0;
		gbc_lblSoldeMin.gridy = 4;
		panelCompte.add(lblSoldeMin, gbc_lblSoldeMin);

		textSoldeMin = new JTextField();
		textSoldeMin.setEnabled(false);
		textSoldeMin.setBackground(Color.BLUE);
		textSoldeMin.setMinimumSize(new Dimension(0, 20));
		textSoldeMin.setFont(new Font("DialogInput", Font.BOLD, 12));
		textSoldeMin.setColumns(14);
		GridBagConstraints gbc_textSoldeMin = new GridBagConstraints();
		gbc_textSoldeMin.anchor = GridBagConstraints.NORTHWEST;
		gbc_textSoldeMin.insets = new Insets(5, 5, 5, 5);
		gbc_textSoldeMin.gridx = 2;
		gbc_textSoldeMin.gridy = 4;
		panelCompte.add(textSoldeMin, gbc_textSoldeMin);

		JLabel lblDevise = new JLabel("Devise: ");
		lblDevise.setMinimumSize(new Dimension(0, 20));
		lblDevise.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblDevise = new GridBagConstraints();
		gbc_lblDevise.ipady = 10;
		gbc_lblDevise.ipadx = 10;
		gbc_lblDevise.anchor = GridBagConstraints.LINE_END;
		gbc_lblDevise.gridwidth = 2;
		gbc_lblDevise.insets = new Insets(0, 0, 5, 5);
		gbc_lblDevise.gridx = 0;
		gbc_lblDevise.gridy = 5;
		panelCompte.add(lblDevise, gbc_lblDevise);

		cboDevisePart = new JComboBox<String>();
		cboDevisePart.setModel(new DefaultComboBoxModel<String>(new String[] {"CAD", "USD", "EUR", "YEN", "PND"}));
		GridBagConstraints gbc_cboDevisePart = new GridBagConstraints();
		gbc_cboDevisePart.anchor = GridBagConstraints.LINE_START;
		gbc_cboDevisePart.insets = new Insets(5, 5, 5, 5);
		gbc_cboDevisePart.gridx = 2;
		gbc_cboDevisePart.gridy = 5;
		panelCompte.add(cboDevisePart, gbc_cboDevisePart);



	} // ui compte


	private Icon getIcon(String path) {

		ImageIcon icon = new ImageIcon(path);
		Image image = icon .getImage(); // transform it
		Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);  // transform it back
		return icon;
	}

	private void uiForIdentite() {

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelIdentite.setLayout(gbl_panel);

		JLabel lblNom = new JLabel("Nom: ");
		lblNom.setMinimumSize(new Dimension(0, 14));
		lblNom.setHorizontalAlignment(SwingConstants.LEFT);
		lblNom.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.gridwidth = 2;
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.weightx = 1.0;
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.ipadx = 10;
		gbc_lblNom.ipady = 10;
		gbc_lblNom.gridx = 0;
		gbc_lblNom.gridy = 2;
		panelIdentite.add(lblNom, gbc_lblNom);

		textNom = new JTextField();
		lblNom.setLabelFor(textNom);
		textNom.setFont(new Font("DialogInput", Font.BOLD, 12));
		textNom.setMinimumSize(new Dimension(0, 20));
		GridBagConstraints gbc_textCompte = new GridBagConstraints();
		gbc_textCompte.gridwidth = 4;
		gbc_textCompte.insets = new Insets(5, 5, 5, 0);
		gbc_textCompte.anchor = GridBagConstraints.NORTHWEST;
		gbc_textCompte.gridx = 2;
		gbc_textCompte.gridy = 2;
		panelIdentite.add(textNom, gbc_textCompte);
		textNom.setColumns(27);

		JLabel lblNas = new JLabel("Nas: ");
		lblNas.setMinimumSize(new Dimension(0, 14));
		lblNas.setHorizontalAlignment(SwingConstants.LEFT);
		lblNas.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblNas = new GridBagConstraints();
		gbc_lblNas.ipady = 10;
		gbc_lblNas.ipadx = 10;
		gbc_lblNas.anchor = GridBagConstraints.LINE_END;
		gbc_lblNas.gridwidth = 2;
		gbc_lblNas.insets = new Insets(0, 0, 5, 5);
		gbc_lblNas.gridx = 0;
		gbc_lblNas.gridy = 3;
		panelIdentite.add(lblNas, gbc_lblNas);

		textNas = new JTextField();
		textNas.setMinimumSize(new Dimension(0, 20));
		textNas.setFont(new Font("DialogInput", Font.BOLD, 12));
		textNas.setColumns(27);
		GridBagConstraints gbc_textNas = new GridBagConstraints();
		gbc_textNas.anchor = GridBagConstraints.NORTHWEST;
		gbc_textNas.gridwidth = 4;
		gbc_textNas.insets = new Insets(5, 5, 5, 0);
		gbc_textNas.gridx = 2;
		gbc_textNas.gridy = 3;
		panelIdentite.add(textNas, gbc_textNas);

		JLabel lblCourriel = new JLabel("Courriel: ");
		lblCourriel.setMinimumSize(new Dimension(0, 20));
		lblCourriel.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblCourriel = new GridBagConstraints();
		gbc_lblCourriel.gridwidth = 2;
		gbc_lblCourriel.weightx = 1.0;
		gbc_lblCourriel.ipady = 10;
		gbc_lblCourriel.ipadx = 10;
		gbc_lblCourriel.anchor = GridBagConstraints.EAST;
		gbc_lblCourriel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourriel.gridx = 0;
		gbc_lblCourriel.gridy = 4;
		panelIdentite.add(lblCourriel, gbc_lblCourriel);

		textCourriel = new JTextField();
		textCourriel.setFont(new Font("DialogInput", Font.BOLD, 12));
		textCourriel.setMinimumSize(new Dimension(0, 20));
		textCourriel.setColumns(27);
		GridBagConstraints gbc_textCourriel = new GridBagConstraints();
		gbc_textCourriel.anchor = GridBagConstraints.NORTHWEST;
		gbc_textCourriel.insets = new Insets(5, 5, 5, 5);
		gbc_textCourriel.gridx = 2;
		gbc_textCourriel.gridy = 4;
		panelIdentite.add(textCourriel, gbc_textCourriel);

		JLabel lblSexe = new JLabel("Sexe: ");
		lblSexe.setMinimumSize(new Dimension(0, 20));
		lblSexe.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblSexe = new GridBagConstraints();
		gbc_lblSexe.ipady = 10;
		gbc_lblSexe.ipadx = 10;
		gbc_lblSexe.anchor = GridBagConstraints.LINE_END;
		gbc_lblSexe.gridwidth = 2;
		gbc_lblSexe.insets = new Insets(0, 0, 5, 5);
		gbc_lblSexe.gridx = 0;
		gbc_lblSexe.gridy = 5;
		panelIdentite.add(lblSexe, gbc_lblSexe);

		cboSexe = new JComboBox<String>();
		cboSexe.setModel(new DefaultComboBoxModel<String>(new String[] {"M", "F"}));
		GridBagConstraints gbc_cboSexe = new GridBagConstraints();
		gbc_cboSexe.anchor = GridBagConstraints.LINE_START;
		gbc_cboSexe.insets = new Insets(5, 5, 5, 5);
		gbc_cboSexe.gridx = 2;
		gbc_cboSexe.gridy = 5;
		panelIdentite.add(cboSexe, gbc_cboSexe);
		
		lblNewLabel = new JLabel(new ImageIcon("resources/icons8-Contract Job-48.png"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 7;
		panelIdentite.add(lblNewLabel, gbc_lblNewLabel);

	}//

	@SuppressWarnings("unused")
	private void preparePreparedStatementTab(int key) {

		switch (key) {
		case 0: // client particulier compte cheque
			prepareTabForPartCh();
			if(instance.executeTransaction(psTab)) {
				
				resetFields();
				
				DialogConfirmationForNewCust  message =new DialogConfirmationForNewCust(Session.idClient, Session.numeroDeCompte);
				message.setVisible(true);
				
			} else
				JOptionPane.showMessageDialog(this, "La transaction a echoue recommencer SVP");			break;
		case 1: // client particulier compte epargne

			prepareTabForPartEpr();
			if(instance.executeTransaction(psTab)) {
				resetFields();

				DialogConfirmationForNewCust  message =new DialogConfirmationForNewCust(Session.idClient, Session.numeroDeCompte);
				message.setVisible(true);
			} else
				JOptionPane.showMessageDialog(this, "La transaction a echoue recommencer SVP");



			break;
		case 2: // client corporatif compte cheque

			prepareTabForEntCh();
			if(instance.executeTransaction(psTab)) {
				
				resetFields();

				DialogConfirmationForNewCust  message =new DialogConfirmationForNewCust(Session.idClient, Session.numeroDeCompte);
				message.setVisible(true);
			} else
				JOptionPane.showMessageDialog(this, "La transaction a echoue recommencer SVP");
			break;
		case 3:  // client corporatif compte epargne

			prepareTabForEntEpr();
			if(instance.executeTransaction(psTab)) {
				
				resetFields();

				DialogConfirmationForNewCust  message =new DialogConfirmationForNewCust(Session.idClient, Session.numeroDeCompte);
				message.setVisible(true);
				
			} else
				JOptionPane.showMessageDialog(this, "La transaction a echoue recommencer SVP");

			break;

		default:
			break;
		}




	} // method prepared statments tab

	/**
	 * 
	 */
	private void prepareTabForEntEpr() {
		// TODO Auto-generated method stub  5 6 8 9 11 14 17 18 26



		Connection conn = instance.getConnexion();
		try {

			Entreprise entr = new Entreprise("", "", "", "");
			CompteEpargne cptEpargne = new CompteEpargne(0.0, "", null, 0.0);
			PreparedStatement ps = conn.prepareStatement(SQLQueries.query5);

			ps.setString(1, entr.getId());
			ps.setString(2, textNomEnt.getText());
			ps.setString(3, textCourrielE.getText());
			
			Session.idClient = entr.getId();

			psTab[0] = ps;
			ps = conn.prepareStatement(SQLQueries.query6);

			ps.setString(1, entr.getId());
			ps.setString(2, textNeq.getText());
			ps.setString(3, textUrl.getText());

			psTab[1] = ps;
			ps = conn.prepareStatement(SQLQueries.query8);

			ps.setString(1, cptEpargne.getNumero());
			ps.setString(2, entr.getId());
			ps.setDouble(3, Double.parseDouble(textDepotE.getText()));
			ps.setString(4, (String) cboDevise.getSelectedItem());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_ouverture = df.format(Calendar.getInstance().getTime());
			ps.setString(5, date_ouverture);
			
			Session.numeroDeCompte = cptEpargne.getNumero(); 

			psTab[2] = ps;
			ps = conn.prepareStatement(SQLQueries.query9);

			ps.setString(1, cptEpargne.getNumero());
			ps.setDouble(2, Double.parseDouble(textSoldeMinE.getText()));
			psTab[3] = ps;
			ps = conn.prepareStatement(SQLQueries.query11);

			ps.setInt(1, Session.nbrClient);
			ps.setInt(2, Session.nbrClientEntreprise);


			psTab[4] = ps;
			ps = conn.prepareStatement(SQLQueries.query14);

			ps.setInt(1, ++Session.nbrCompte);
			ps.setInt(2, ++Session.nbrCompteEpargne);

			psTab[5] = ps;
			ps = conn.prepareStatement(SQLQueries.query17);

			ps.setString(1, entr.getId());
			ps.setInt (2, Integer.parseInt(textNumRueEnt.getText()));
			ps.setString(3, "");
			ps.setString(4, textSuite.getText());
			ps.setString(5, textRueEnt.getText());
			ps.setString(6, textVilleEnt.getText());
			ps.setString(7, textProvinceEnt.getText());
			ps.setString(8, textCodePostalEnt.getText());
			ps.setString(9, textPaysEnt.getText());

			psTab[6] = ps;
			ps = conn.prepareStatement(SQLQueries.query18);

			ps.setString(1, entr.getId());
			ps.setString(2, textPrincipalE.getText());
			ps.setInt (3, Integer.parseInt(textPosteE.getText()));
			ps.setString(4, textSecondaireE.getText());
			ps.setInt (5, Integer.parseInt(textPosteSecondaireE.getText()));
			ps.setString(6, textMobileE.getText());
			ps.setString(7, textTelecopieurE.getText());

			psTab[7] = ps;

			ps = conn.prepareStatement(SQLQueries.query16);

			ps.setString(1, Session.loginAgent);
			ps.setString(2, cptEpargne.getNumero());
			ps.setString(3, "Ouverture");
			ps.setString(4, textDepotE.getText());
			ps.setString(5, textDepotE.getText());
			String date_transaction = df.format(Calendar.getInstance().getTime());
			ps.setString(6, date_transaction);

			psTab[8] = ps;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**
	 * 
	 */
	private void prepareTabForEntCh() {
		// TODO Auto-generated method stub  5 6 8 10 11 13 17 18 26


		Connection conn = instance.getConnexion();
		try {

			Entreprise entr = new Entreprise("", "", "", "");
			CompteCheque chq = new CompteCheque(0.0, "", null, 0.0);

			PreparedStatement ps = conn.prepareStatement(SQLQueries.query5);

			ps.setString(1, entr.getId());
			ps.setString(2, textNomEnt.getText());
			ps.setString(3, textCourrielE.getText());
			Session.idClient = entr.getId(); 

			psTab[0] = ps;
			ps = conn.prepareStatement(SQLQueries.query6);

			ps.setString(1, entr.getId());
			ps.setString(2, textNeq.getText());
			ps.setString(3, textUrl.getText());

			psTab[1] = ps;
			ps = conn.prepareStatement(SQLQueries.query8);

			ps.setString(1, chq.getNumero());
			ps.setString(2, entr.getId());
			ps.setString(3, textDepotE.getText());
			ps.setString(4, (String) cboDevise.getSelectedItem());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_ouverture = df.format(Calendar.getInstance().getTime());
			ps.setString(5, date_ouverture);
			
			Session.numeroDeCompte = chq.getNumero();

			psTab[2] = ps;
			ps = conn.prepareStatement(SQLQueries.query10); /* A FAIRE*/

			ps.setString(1, chq.getNumero());
			ps.setString(2, textLimiteE.getText());		

			psTab[3] = ps;
			ps = conn.prepareStatement(SQLQueries.query11);

			ps.setInt(1, Session.nbrClient);
			ps.setInt(2, Session.nbrClientEntreprise);

			psTab[4] = ps;
			ps = conn.prepareStatement(SQLQueries.query13);  /* A FAIRE*/

			ps.setInt(1, Session.nbrCompte);
			ps.setInt(2, Session.nbrCompteCheque);

			psTab[5] = ps;
			ps = conn.prepareStatement(SQLQueries.query17);

			ps.setString(1, entr.getId());
			ps.setInt (2, Integer.parseInt(textNumRueEnt.getText()));
			ps.setString(3, "");
			ps.setString(4, textSuite.getText());
			ps.setString(5, textRueEnt.getText());
			ps.setString(6, textVilleEnt.getText());
			ps.setString(7, textProvinceEnt.getText());
			ps.setString(8, textCodePostalEnt.getText());
			ps.setString(9, textPaysEnt.getText());

			psTab[6] = ps;
			ps = conn.prepareStatement(SQLQueries.query18);

			ps.setString(1, entr.getId());
			ps.setString(2, textPrincipalE.getText());
			ps.setInt (3, Integer.parseInt(textPosteE.getText()));
			ps.setString(4, textSecondaireE.getText());
			ps.setInt (5, Integer.parseInt(textPosteSecondaireE.getText()));
			ps.setString(6, textMobileE.getText());
			ps.setString(7, textTelecopieurE.getText());


			psTab[7] = ps;

			ps = conn.prepareStatement(SQLQueries.query16);

			ps.setString(1, Session.loginAgent);
			ps.setString(2, chq.getNumero());
			ps.setString(3, "Ouverture");
			ps.setString(4, textDepotE.getText());
			ps.setString(5, textDepotE.getText());
			String date_transaction = df.format(Calendar.getInstance().getTime());
			ps.setString(6, date_transaction);

			psTab[8] = ps;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**
	 * 
	 */
	private void prepareTabForPartEpr() {
		// TODO Auto-generated method stub  5 7 8 9 12 14 17 18 25



		Connection conn = instance.getConnexion();
		Particulier particl = new Particulier("", null, null, "", "", true);
		CompteEpargne epr = new CompteEpargne(0.0, "", null, 0.0);
		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.query5);

			ps.setString(1, particl.getId()); //
			ps.setString(2, textNom.getText());
			ps.setString(3, textCourriel.getText());
			
			Session.idClient = particl.getId();


			psTab[0] = ps;
			ps = conn.prepareStatement(SQLQueries.query7); /* A FAIRE */

			ps.setString(1, particl.getId());
			ps.setString(2, textNas.getText());
			int index = cboSexe.getSelectedIndex();
			ps.setString(3, (index == 0)? "M": "F" );

			psTab[1] = ps;
			ps = conn.prepareStatement(SQLQueries.query8);

			ps.setString(1, epr.getNumero());
			ps.setString(2, particl.getId());
			ps.setDouble(3, Double.parseDouble(textDepot.getText()));			ps.setString(4, (String) cboDevisePart.getSelectedItem());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_ouverture = df.format(Calendar.getInstance().getTime());
			ps.setString(5, date_ouverture);
			
			Session.numeroDeCompte =  epr.getNumero();

			psTab[2] = ps;
			ps = conn.prepareStatement(SQLQueries.query9);

			ps.setString(1, epr.getNumero());
			ps.setDouble(2, Double.parseDouble(textSoldeMin.getText()));			
			psTab[3] = ps;

			ps = conn.prepareStatement(SQLQueries.query12); /* A FAIRE */

			ps.setInt(1, Session.nbrClient);
			ps.setInt(2, Session.nbrClientParticulier);

			psTab[4] = ps;
			ps = conn.prepareStatement(SQLQueries.query14);

			ps.setInt(1, Session.nbrCompte);
			ps.setInt(2, Session.nbrCompteEpargne);

			psTab[5] = ps;
			ps = conn.prepareStatement(SQLQueries.query17);

			ps.setString(1, particl.getId());

			ps.setInt (2, Integer.parseInt(textCivic.getText()));

			ps.setString(3, textApp.getText());

			ps.setString(4, "");

			ps.setString(5, textRue.getText());

			ps.setString(6, textVille.getText());

			ps.setString(7, textProvince.getText());

			ps.setString(8, textCodePostal.getText());

			ps.setString(9, textPays.getText());

			psTab[6] = ps;
			ps = conn.prepareStatement(SQLQueries.query18);

			ps.setString(1, particl.getId());
			ps.setString(2, textPrincipal.getText());
			ps.setInt (3, Integer.parseInt(textPoste.getText()));
			ps.setString(4, textSecondaire.getText());
			ps.setInt (5, Integer.parseInt(textPosteSecondaire.getText()));
			ps.setString(6, textMobile.getText());
			ps.setString(7, textTelecopieur.getText());

			psTab[7] = ps;
			
			ps = conn.prepareStatement(SQLQueries.query16);

			ps.setString(1, Session.loginAgent);
			ps.setString(2, epr.getNumero());
			ps.setString(3, "Ouverture");
			ps.setString(4, textDepot.getText());
			ps.setString(5, textDepot.getText());
			String date_transaction = df.format(Calendar.getInstance().getTime());
			ps.setString(6, date_transaction);

			psTab[8] = ps;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	/**
	 * 
	 */
	private void prepareTabForPartCh() {
		// TODO Auto-generated method stub  5 7 8 10 12 13 17 18 25



		Connection conn = instance.getConnexion();

		Particulier particl = new Particulier("", null, null, "", "", true);
		CompteCheque chq = new CompteCheque(0.0, "", null, 0.0);


		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.query5);

			ps.setString(1, particl.getId());
			ps.setString(2, textNom.getText());
			ps.setString(3, textCourriel.getText());
			
			Session.idClient = particl.getId();

			psTab[0] = ps;
			ps = conn.prepareStatement(SQLQueries.query7); /* A FAIRE */

			ps.setString(1, particl.getId());
			ps.setString(2, textNas.getText());
			int index = cboSexe.getSelectedIndex();
			ps.setString(3, (index == 0)? "M": "F" );

			psTab[1] = ps;
			ps = conn.prepareStatement(SQLQueries.query8);

			ps.setString(1, chq.getNumero());
			ps.setString(2, particl.getId());
			ps.setDouble(3, Double.parseDouble(textDepot.getText()));			
			ps.setString(4, (String) cboDevisePart.getSelectedItem());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_ouverture = df.format(Calendar.getInstance().getTime());
			ps.setString(5, date_ouverture);
			
			Session.numeroDeCompte =  chq.getNumero();

			psTab[2] = ps;
			ps = conn.prepareStatement(SQLQueries.query10); /* A FAIRE */			
			ps.setString(1, chq.getNumero());
			ps.setDouble(2, Double.parseDouble(textLimite.getText()));
			psTab[3] = ps;
			ps = conn.prepareStatement(SQLQueries.query12);  /* A FAIRE */
			ps.setInt(1, Session.nbrClient);
			ps.setInt(2, Session.nbrClientParticulier);
			psTab[4] = ps;
			ps = conn.prepareStatement(SQLQueries.query13);  /* A FAIRE */
			ps.setInt(1, Session.nbrCompte);
			ps.setInt(2, Session.nbrCompteCheque);

			psTab[5] = ps;
			ps = conn.prepareStatement(SQLQueries.query17);  /* A FAIRE */
			ps.setString(1, particl.getId());
			ps.setInt(2, Integer.parseInt(textCivic.getText()));
			ps.setString(3, textApp.getText());
			ps.setString(4, " ");
			ps.setString(5, textRue.getText());
			ps.setString(6, textVille.getText());
			ps.setString(7, textProvince.getText());
			ps.setString(8, textCodePostal.getText());
			ps.setString(9, textPays.getText());


			psTab[6] = ps;
			ps = conn.prepareStatement(SQLQueries.query18);
			ps.setString(1, particl.getId());
			ps.setString(2, textPrincipal.getText());
			ps.setInt (3, Integer.parseInt(textPoste.getText()));
			ps.setString(4, textSecondaire.getText());
			ps.setInt (5, Integer.parseInt(textPosteSecondaire.getText()));
			ps.setString(6, textMobile.getText());
			ps.setString(7, textTelecopieur.getText());

			psTab[7] = ps;

			ps = conn.prepareStatement(SQLQueries.query16);

			ps.setString(1, Session.loginAgent);
			ps.setString(2, chq.getNumero());
			ps.setString(3, "Ouverture");
			ps.setString(4, textDepot.getText());
			ps.setString(5, textDepot.getText());
			String date_transaction = df.format(Calendar.getInstance().getTime());
			ps.setString(6, date_transaction);

			psTab[8] = ps;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


	public PreparedStatement[] getTab() {
		return psTab;

	}


	public JComboBox getCboTypeE() {
		return cboTypeE;
	}
	public JComboBox getCboDevise() {
		return cboDevise;
	}
	public JTextField getTextNumRueEnt() {
		return textNumRueEnt;
	}
	public JComboBox getCboDevisePart() {
		return cboDevisePart;
	}
	public JComboBox getCboTypePart() {
		return cboTypePart;
	}
	public JTextField getTextDepot() {
		return textDepot;
	}
	public JTextField getTextLimite() {
		return textLimite;
	}
	public JTextField getTextCivic() {
		return textCivic;
	}
	public JTextField getTextRue() {
		return textRue;
	}
	public JComboBox getCboSexe() {
		return cboSexe;
	}
	
	void resetFields() {
		
		textApp.setText(null);
		textCivic.setText(null);
		textCodePostal.setText(null);
		textCodePostalEnt.setText(null);
		textCourriel.setText(null);
		textCourrielE.setText(null);
		textDepot.setText(null);
		textDepotE.setText(null);
		textLimite.setText(null);
		textLimiteE.setText(null);
		textMobile.setText(null);
		textMobileE.setText(null);
		textNas.setText(null);
		textNeq.setText(null);
		textNom.setText(null);
		textNomEnt.setText(null);
		textNumRueEnt.setText(null);
		textPays.setText(null);
		textPaysEnt.setText(null);
		textPoste.setText(null);
		textPosteE.setText(null);
		textPosteSecondaire.setText(null);
		textPosteSecondaireE.setText(null);
		textPrincipal.setText(null);
		textPrincipalE.setText(null);
		textProvince.setText(null);
		textProvinceEnt.setText(null);
		textRue.setText(null);
		textRueEnt.setText(null);
		textSecondaire.setText(null);
		textSecondaireE.setText(null);
		textSoldeMin.setText(null);
		textSoldeMinE.setText(null);
		textSuite.setText(null);
		textTelecopieur.setText(null);
		textTelecopieurE.setText(null);
		textVille.setText(null);
		textVilleEnt.setText(null);
		textUrl.setText(null);
		
		cboDevise.setSelectedIndex(0);
		cboDevisePart.setSelectedIndex(0);
		cboSexe.setSelectedIndex(0);
		cboTypeE.setSelectedIndex(0);
		cboTypePart.setSelectedIndex(0);
		
		
		
	}
}//class
