/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import ca.qc.cgodin.Modele.Login;
import ca.qc.cgodin.Modele.Session;

import java.awt.Color;


@SuppressWarnings("serial")
public class FenetrePrincipale extends JFrame implements WindowListener {

	//public static int nbrFrames = 0;

	private BufferedImage image; // gerald godin icon
	private String imageFile = "resources/gerald_odin.png";
	private JDesktopPane desk;
	private JMenuItem itemLogin;
	private JMenuItem itemLogout;
	private BufferedImage img; // background image
	private FenetreLogin fLogin;
	private FenetreDepot fDepot;
	private JMenuBar barreMenu;
	private JMenuItem itemNouveau;
	private JMenuItem itemFermiture;
	private JMenuItem itemDepot;
	private JMenuItem itemRetrait;
	private JMenuItem itemVirement;
	private JMenuItem itemTroisMois;
	private JMenuItem itemDouzeMois;
	private JMenuItem itemTauxInteret;
	private JMenuItem itemRClients;
	private JMenuItem itemRCompte;
	private JMenu menuCompte;
	private JMenu menuOperations;
	private JMenu menuGestion;
	private JButton btnNouveau;
	private JButton btnDepot;
	private JButton btnRetrait;
	private JButton btnTaux;
	private JButton btnRapportClient;
	protected FenetreRetrait fRetrait;
	protected FenetreVirement fVirement;
	protected FenetreTaux fTaux;
	protected FenetreNouveau fNouveau;
	public String poste;
	private JMenu itemHistorique;
	protected FenetreAffichTransact fAffichageTrois;
	protected FenetreAffichTransact fAffichageDouze;
	private JMenuItem itemVisualiser;
	protected FenetreVisualiserCpt fVisualier;
	private JButton btnVisualiser;

	private static boolean logged = false; // check if user logged or not


	/**
	 * Create the frame.
	 */
	public FenetrePrincipale() {

		setTitle("BANQUE GERALD GODIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.addWindowListener(this);
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension dimension = tool.getScreenSize();
		int width = (int) (0.8 * dimension.getWidth());
		int height = (int) (0.8 * dimension.getHeight());
		int x = (int) (0.1 * dimension.getWidth());
		int y = (int) (0.1 * dimension.getHeight());
		setBounds(x, y, width, height);

		try {
			image = ImageIO.read(new File(imageFile));
			img = ImageIO.read(new File("resources/background.jpg"));
		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		setIconImage(image);
		construireUI();
		setVisible(true);

	} // constructor

	/**
	 * 
	 */
	private void construireUI() {

		/* creer menu */
		creerBarreMenus();
		/* Internal frame for login */

		desk = new JDesktopPane() {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), this);			
			}		
		}; 
		Session.desk = desk;
		desk.setBackground(Color.YELLOW);
		getContentPane().add(desk, BorderLayout.CENTER);
		desk.setLayout(null);
		//setContentPane(desk);

	} 

	private void creerBarreMenus(){
		//Création de la barre de menu
		barreMenu = new JMenuBar();

		//Création des menus
		JMenu menuAction = new JMenu("Quitter");
		JMenu menuAide = new JMenu("Aide");

		//Ajout des menus à la barre de menus
		barreMenu.add(menuAction);
		barreMenu.add(menuAide);

		//Ajout de la barre de menus à la fenêtre
		setJMenuBar(barreMenu);
		itemLogout = new JMenuItem("Quitter App", getIcon("resources/LogOut.png"));
		itemLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});

		//Ajout des options dans le menu edition
		//menuAction.add(itemLogin);
		menuAction.addSeparator();
		menuAction.add(itemLogout);

		//Création des items du menu Aide
		JMenuItem itemAPropos = new JMenuItem("A propos", getIcon("resources/CompanyParent.png"));
		itemAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


			}
		});
		JMenuItem itemGuideUtilisateur = new JMenuItem("Guide d'utilisateur", getIcon("resources/icons8-User Manual-40.png"));

		//Ajout des options dans le menu Aide
		menuAide.add(itemAPropos);	
		menuAide.addSeparator();
		menuAide.add(itemGuideUtilisateur);
	} // creer barre menus

	public void createToolBar(int i) {

		JToolBar toolbar = new JToolBar();

		/* setting up toolbar buttons*/

		btnNouveau = new JButton(getIcon("resources/icons8-Agreement-64.png"));
		btnNouveau.setToolTipText("Nouveau client");
		btnNouveau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fNouveau = new FenetreNouveau();
				Dimension desktopSize = desk.getSize();
				fNouveau.setLocation(10, 10);
				fNouveau.setSize(desktopSize.width - 10,
						desktopSize.height - 10);

				desk.add(fNouveau);
				desk.setSelectedFrame(fNouveau);
				try {
					fNouveau.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnVisualiser = new JButton(getIcon("resources/icons8-Documents-64.png"));
		btnVisualiser.setToolTipText("Visualiser compte");
		btnVisualiser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

							
				FenetreIdConfirmation fConfirmId = new FenetreIdConfirmation();

				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fConfirmId.getSize();
				fConfirmId.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2 );

				desk.add(fConfirmId);
				desk.setSelectedFrame(fConfirmId);
				
				try {
					fConfirmId.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});


		btnDepot = new JButton(getIcon("resources/icons8-Deposit-48.png"));
		btnDepot.setToolTipText("Depot");
		btnDepot.addActionListener(new ActionListener() {



			@Override
			public void actionPerformed(ActionEvent e) {

				fDepot = new FenetreDepot();
				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fDepot.getSize();
				fDepot.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2 );
				desk.add(fDepot);
				desk.setSelectedFrame(fDepot);
				try {
					fDepot.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnRetrait = new JButton(getIcon("resources/icons8-Withdrawal-48.png"));
		btnRetrait.setToolTipText("Retrait");
		btnRetrait.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fRetrait = new FenetreRetrait();
				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fRetrait.getSize();
				fRetrait.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2);
				desk.add(fRetrait);
				desk.setSelectedFrame(fRetrait);
				try {
					fRetrait.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnTaux = new JButton(getIcon("resources/icons8-Ratings-48.png"));
		btnTaux.setToolTipText("Changer le taux interet");
		btnTaux.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fTaux = new FenetreTaux();
				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fTaux.getSize();
				fTaux.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2);
				desk.add(fTaux);
				desk.setSelectedFrame(fTaux);
				try {
					fTaux.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnRapportClient = new JButton(getIcon("resources/rapport-clients.png"));
		btnRapportClient.setToolTipText("Generer rapport client");
		btnRapportClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		/* adding toolbar buttons*/

		toolbar.add(btnNouveau);
		toolbar.add(btnVisualiser);
		toolbar.add(btnDepot);
		toolbar.add(btnRetrait);
		toolbar.add(btnTaux);
		toolbar.add(btnRapportClient);

		disableButtons(i);

		getContentPane().add(toolbar, BorderLayout.NORTH);
	}


	/**
	 * @param i
	 */
	private void disableButtons(int i) {

		switch(i)
		{
		case 0: // caissier
			btnNouveau.setEnabled(false);
			btnRapportClient.setEnabled(false);
			btnTaux.setEnabled(false);
			break;
		case 1: // directeur

			btnRapportClient.setEnabled(false);
			btnTaux.setEnabled(false);

			break;
		case 2: // Gestionnaire
			break;

		}

	}// disable buttons

	/**
	 * @param path 
	 * @return
	 */
	/* generate scaled menu icons*/

	private Icon getIcon(String path) {

		ImageIcon icon = new ImageIcon(path);
		Image image = icon .getImage(); // transform it
		Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);  // transform it back
		return icon;
	}

	public void creerOperationalMenu(int userType) {

		barreMenu.removeAll();  //barreMenu.getMenu(0)

		if(userType == 0)  { // caissier

			ajouterCaissierMenu();
		}
		else if(userType == 1) // directeur
			ajouterDirecteurMenu();
		else if(userType == 2) // gestionnaire
			ajouterGestionnaireMenu();
		else
			System.out.println("Bad choice.");

	}

	private void ajouterGestionnaireMenu() {

		ajouterLesMenus();
		disableFeaturesGestionnaire();

	}

	private void disableFeaturesGestionnaire() {

		//barreMenu.getMenu(1).setEnabled(false);

	}

	private void ajouterDirecteurMenu() {
		ajouterLesMenus();
		disableFeaturesDirecteur();

	}

	private void disableFeaturesDirecteur() {

		barreMenu.getMenu(2).setEnabled(false);

	}

	private void ajouterCaissierMenu() {

		ajouterLesMenus();
		disableFeaturesCaissier();	
	}

	private void disableFeaturesCaissier() {

		barreMenu.getMenu(0).setEnabled(false);
		itemHistorique.getItem(1).setEnabled(false);
		barreMenu.getMenu(2).setEnabled(false);
	}

	private void ajouterLesMenus() {


		//Création des menus
		menuCompte = new JMenu("Compte");
		menuOperations = new JMenu("Operations");
		menuGestion = new JMenu("Gestion");
		JMenu menuHistorique = new JMenu("Historique");
		itemHistorique = menuHistorique; /* to keep it for future use*/
		JMenu menuModifier = new JMenu("Modifier");
		JMenu menuRapport = new JMenu("Rapports");
		JMenu menuAide = new JMenu("?");

		//Ajout des menus à la barre de menus
		barreMenu.add(menuCompte);
		barreMenu.add(menuOperations);
		barreMenu.add(menuGestion);
		//barreMenu.add(Box.createHorizontalGlue());
		barreMenu.add(menuAide);



		//Création des items du menu compte
		itemNouveau = new JMenuItem("Nouveau",getIcon("resources/icons8-Agreement-64.png"));
		itemNouveau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fNouveau = new FenetreNouveau();
				Dimension desktopSize = desk.getSize();
				fNouveau.setLocation(10, 10);
				fNouveau.setSize(desktopSize.width - 10,
						desktopSize.height - 10);

				desk.add(fNouveau);
				desk.setSelectedFrame(fNouveau);
				try {
					fNouveau.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		itemFermiture = new JMenuItem("Fermiture", getIcon("resources/icons8-Assignment Return-50.png"));
		itemFermiture.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		itemLogout = new JMenuItem("Quitter App", getIcon("resources/LogOut.png"));
		itemLogout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String logout = format.format(Calendar.getInstance().getTime());
				Login loginRecord = new Login(logout);
				if(Session.loginAgent != null)
					loginRecord.create(loginRecord);
				
				System.exit(0);

			}
		});

		//Ajout des options dans le menu compte
		menuCompte.add(itemNouveau);
		menuCompte.add(itemFermiture);
		menuCompte.addSeparator();
		menuCompte.add(itemLogout);

		//Création des items du menu Aide
		JMenuItem itemAPropos = new JMenuItem("A propos", getIcon("resources/CompanyParent.png"));
		itemAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


			}
		});
		JMenuItem itemGuideUtilisateur = new JMenuItem("Guide d'utilisateur", getIcon("resources/icons8-User Manual-40.png"));

		//Ajout des options dans le menu Aide
		menuAide.add(itemAPropos);	
		menuAide.addSeparator();
		menuAide.add(itemGuideUtilisateur);

		/// MENU OPERATIONS

		//Création des items du menu operations
		itemVisualiser = new JMenuItem("Visualiser", getIcon("resources/icons8-Documents-64.png"));
		itemVisualiser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

		
				FenetreIdConfirmation fConfirmId = new FenetreIdConfirmation();

				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fConfirmId.getSize();
				fConfirmId.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2 );

				desk.add(fConfirmId);
				desk.setSelectedFrame(fConfirmId);
				try {
					fConfirmId.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});



		itemDepot = new JMenuItem("Depot", getIcon("resources/icons8-Deposit-48.png"));
		itemDepot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fDepot = new FenetreDepot();
				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fDepot.getSize();
				fDepot.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2);
				desk.add(fDepot);
				desk.setSelectedFrame(fDepot);
				try {
					fDepot.setSelected(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
		itemRetrait = new JMenuItem("Retrait", getIcon("resources/icons8-Withdrawal-48.png"));
		itemRetrait.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fRetrait = new FenetreRetrait();
				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fRetrait.getSize();
				fRetrait.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2 );
				desk.add(fRetrait);
				desk.setSelectedFrame(fRetrait);
				try {
					fRetrait.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		itemVirement = new JMenuItem("Virement", getIcon("resources/icons8-Money Transfer-64.png"));
		itemVirement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fVirement = new FenetreVirement();
				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fVirement.getSize();
				fVirement.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2);
				desk.add(fVirement);
				desk.setSelectedFrame(fVirement);
				try {
					fVirement.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});


		//Ajout des options dans le menu operations
		menuOperations.add(itemVisualiser);
		menuOperations.addSeparator();
		menuOperations.add(itemDepot);
		menuOperations.add(itemRetrait);
		menuOperations.add(itemVirement);
		menuOperations.addSeparator();
		menuOperations.add(menuHistorique);

		//Ajout des options au menu historique

		itemTroisMois = new JMenuItem("3 mois", getIcon("resources/3mois.jpg"));
		itemTroisMois.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Session.transactionSelect = 0;

				FenetreCompteConfirmation fConfirm = new FenetreCompteConfirmation(0);

				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fConfirm.getSize();
				fConfirm.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2 );

				desk.add(fConfirm);
				desk.setSelectedFrame(fConfirm);
				
				try {
					fConfirm.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		itemDouzeMois = new JMenuItem("12 mois", getIcon("resources/12mois.png"));
		itemDouzeMois.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Session.transactionSelect = 1;

				FenetreCompteConfirmation fConfirm = new FenetreCompteConfirmation(1);

				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fConfirm.getSize();
				fConfirm.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2 );

				desk.add(fConfirm);
				desk.setSelectedFrame(fConfirm);
				try {
					fConfirm.setSelected(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		menuHistorique.add(itemTroisMois);
		menuHistorique.add(itemDouzeMois);

		// ajout au menu Gestion

		menuGestion.add(menuModifier);
		menuGestion.add(menuRapport);

		itemTauxInteret = new JMenuItem("Taux Interet", getIcon("resources/icons8-Ratings-48.png"));
		itemTauxInteret.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				fTaux = new FenetreTaux();
				Dimension desktopSize = desk.getSize();
				Dimension jInternalFrameSize = fTaux.getSize();
				fTaux.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
						(desktopSize.height- jInternalFrameSize.height)/2);
				desk.add(fTaux);
				desk.setSelectedFrame(fTaux);
				try {
					fTaux.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		itemRClients = new JMenuItem("Clients", getIcon("resources/rapport-clients.png"));
		itemRClients.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		itemRCompte = new JMenuItem("Comptes", getIcon("resources/icons8-Documents-64.png"));
		itemRCompte.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// ajout au menu Modifier

		menuModifier.add(itemTauxInteret);

		// ajout au menu Rapports

		menuRapport.add(itemRClients);
		menuRapport.add(itemRCompte);


	}// ajouterMenus

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// register value on login table
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String logout = format.format(Calendar.getInstance().getTime());
		Login loginRecord = new Login(logout);
		if(Session.loginAgent != null)
			loginRecord.create(loginRecord);
	
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		
		fLogin = new FenetreLogin(FenetrePrincipale.this);
		Dimension desktopSize = desk.getSize();
		Dimension jInternalFrameSize = fLogin.getSize();
		fLogin.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height- jInternalFrameSize.height)/2);
		desk.add(fLogin);
		desk.setSelectedFrame(fLogin);
		//barreMenu.getMenu(0).getItem(0).setEnabled(false);
	}

}// class
