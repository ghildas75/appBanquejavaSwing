package ca.qc.cgodin.Modele;

public abstract class SQLQueries {



	public final static String query1 = "select * from utilisateur where IDLOGINNOMUTILISATEUR=?";
	public final static String query2 = "select * from transactions where IDNUMEROCOMPTE=? and DateTransaction >= DATE_SUB(NOW(), INTERVAL 3 MONTH)";
	public final static String query3 = "select * from transactions where IDNUMEROCOMPTE=? and DateTransaction >= DATE_SUB(NOW(), INTERVAL 12 MONTH)";
	public final static String query4 = "insert into logins values (null,?,?,?)";

	/* transaction d ouverture*/

	public final static String query5 = "insert into client values (?,?,?)";
	public final static String query6 = "insert into client_entreprise values (?,?,?)";
	public final static String query7 = "insert into client_particulier values (?,?,?)";
	public final static String query8 = "insert into compte values (?,?,?,?,?)";
	public final static String query9 = "insert into compte_epargne values (?,?)";
	public final static String query10 = "insert into compte_cheque values (?,?)";

	/* mettre a jour utilitaire client counters*/  

	public final static String query11 = "update utilitaire set NBRCLIENT =?, NBRCLIENTEntr=?";
	public final static String query12 = "update utilitaire set NBRCLIENT =?, NBRCLIENTPart=? ";



	/* mettre a jour utilitaire compte counters*/
	public final static String query13 = "update utilitaire set NBRCOMPTE=?, NBRCOMPTECheque=?";
	public final static String query14 = "update utilitaire set NBRCOMPTE=?, NBRCOMPTEEpargne=?";
	public final static String query25 = "update utilitaire set NBRCLIENT=?, NBRCLIENTPart=?";
	public final static String query26 = "update utilitaire set NBRCLIENT=?, NBRCLIENTEntr=?";

	/* mettre a jour utilitaire taux interets value*/
	public final static String query15 = "update utilitaire set TAUXINTERETS =?";

	/* insertion d une trace d ouverture dans table transactions */
	public final static String query16 = "insert into transactions values (null,?,?,?,?,?,?)";

	public final static String query17 = "insert into adresse values (null,?,?,?,?,?,?,?,?,?)";

	public final static String query18 = "insert into telephone values (null,?,?,?,?,?,?,?)";

	/* fin trans ouverture*/ 
	/*************************************************************************************************/	
	/* queries related to get or set counters sate in db */

	public final static String query19 = "select NBRCLIENT from utilitaire";
	public final static String query20 = "select NBRCLIENTPart from utilitaire";
	public final static String query21 = "select NBRCLIENTEntr from utilitaire";
	public final static String query22 = "select NBRCOMPTE from utilitaire";

	public final static String query23 = "update utilitaire set NBRCOMPTE=?, NBRCOMPTECheque=?";
	public final static String query24 = "update utilitaire set NBRCOMPTE=?, NBRCOMPTEEpargne=?";

	public final static String query27 = "select * from  utilitaire";


	/**************************************************************************************************/
	public final static String query28 = "select solde from  compte where IDNUMEROCOMPTE=?";
	public final static String query29 = "select SOLDE_MINIMUM from  compte_epargne  where IDNUMEROCOMPTE=?";
	public final static String query30 = "select LIMITE_DECOUVERT from  compte_cheque  where IDNUMEROCOMPTE=?";
	public final static String query31 = "update compte set solde=? where IDNUMEROCOMPTE=? ";


	/******************************************************************************************************///

	public final static String query32 = "update utilitaire set TAUXINTERETS=?";
	
	/* joins les tables en heritage */
	
	public final static String query33 = "select compte.idnumerocompte, compte.solde, compte.devise, compte_cheque.limite_decouvert, compte.date_ouverture from compte join compte_cheque on compte.idnumerocompte=compte_cheque.idnumerocompte where compte.idclient=?";

	public final static String query34 = "select compte.idnumerocompte, compte.solde, compte.devise, compte_epargne.solde_minimum, compte.date_ouverture from compte join compte_epargne on compte.idnumerocompte = compte_epargne.idnumerocompte where compte.idclient=?";

	public final static String query35 = "select  nom from client where idclient=?";






}// class
