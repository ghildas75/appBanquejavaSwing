package ca.qc.cgodin.Modele;



import javax.swing.JDesktopPane;

import ca.qc.cgodin.Vue.FenetrePrincipale;

/* represete une session du travail */

public abstract class Session {
	
	public static String loginAgent; /* login de l employee*/
	public static String nomAgent; /* nom de l employee*/
	public static String posteAgent; /* poste de l employee*/
	public static String seccursalle; /* lieu de travail */
	public static String dateLogin; /* date  de connexion */
	public static String idClient; /* id de client en traitement par l agent */
	public static String numeroDeCompte = "123456789102345"; /* num de compte en traitement par l agent */
	public static FenetrePrincipale mainWindow; /* ref a la fenetre principale */
	public static int transactionSelect = 0; /* type d affichage selectionnees */
	public static String devise ="CAD" /* la devise selectionne */;
	public static String monitor = "";
	public static JDesktopPane desk = null; /* refrence to desktop */
	public static int nbrClient; /* nombre de client dans le DB */
	public static int nbrClientParticulier; /* nombre de client particulier dans le DB */
	public static int nbrClientEntreprise; /* nombre de client corporatif dans le DB */
	public static int nbrCompte; /* nombre de compte dans le DB */
	public static int nbrCompteEpargne; /* nombre de compte epargne dans le DB */
	public static int nbrCompteCheque; /* nombre de compte cheque dans le DB */
	public static int typeTransaction = 0; /* 0 depot et 1 retrait */
	public static boolean statusTransaction = true; /* false if failed */
	public static boolean retraitExceptionFired = false; /* true if compteexception fired */

	
	

}//class generic
