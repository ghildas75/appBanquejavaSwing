/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import ca.qc.cgodin.Modele.Compte;
import ca.qc.cgodin.Modele.CompteCheque;
import ca.qc.cgodin.Modele.CompteEpargne;
import ca.qc.cgodin.Modele.ConnexionToDB;
import ca.qc.cgodin.Modele.SQLQueries;
import ca.qc.cgodin.Modele.Session;
import ca.qc.cgodin.Modele.Transaction;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class FenetreIdConfirmation extends JInternalFrame {

	private BufferedImage img;
	private JTextField textId;
	protected FenetreAffichTransact fAffichageTrois;
	protected FenetreAffichTransact fAffichageDouze;
	protected FenetreVisualiserCpt fVisualier;
	private ArrayList<CompteEpargne> comptesEpargne = null;
	private ArrayList<CompteCheque> comptesCheque = null;

	/**
	 * 
	 */
	public FenetreIdConfirmation() {

		super("", true, true, false, true);

		setFont(new Font("Tahoma", Font.BOLD, 13));
		setTitle("Confirmation Identifiant");
		setSize(550, 180);	
		comptesCheque = new ArrayList<CompteCheque>();
		comptesEpargne = new ArrayList<CompteEpargne>();

		try {

			img = ImageIO.read(new File("resources/gerald_odin.png"));

		} catch (IOException ex) {

			JOptionPane.showMessageDialog(this, " Issue to set icon/ background.");

		}
		this.setFrameIcon(new ImageIcon(img));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Identifiant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblId = new JLabel("Identifiant client: ");
		lblId.setFont(new Font("Dialog", Font.BOLD, 13));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.ipadx = 10;
		gbc_lblId.ipady = 10;
		gbc_lblId.insets = new Insets(20, 20, 20, 20);
		gbc_lblId.anchor = GridBagConstraints.LINE_END;
		gbc_lblId.gridwidth = 2;
		gbc_lblId.gridx = 1;
		gbc_lblId.gridy = 2;
		panel.add(lblId, gbc_lblId);
		lblId.setLabelFor(textId);

		textId = new JTextField();
		textId.setFont(new Font("DialogInput", Font.BOLD, 14));
		GridBagConstraints gbc_textId = new GridBagConstraints();
		gbc_textId.fill = GridBagConstraints.BOTH;
		gbc_textId.insets = new Insets(20, 20, 20, 20);
		gbc_textId.anchor = GridBagConstraints.LINE_START;
		gbc_textId.gridx = 3;
		gbc_textId.gridy = 2;
		panel.add(textId, gbc_textId);
		textId.setColumns(20);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				
				String nomClient = ConnexionToDB.getCustomerName(textId.getText());
				fVisualier = new FenetreVisualiserCpt(chargerComptesCheque(), chargerComptesEpargne(), nomClient );
				Dimension desktopSize = Session.desk.getSize();

				fVisualier.setLocation(10, 10);
				fVisualier.setSize(desktopSize.width - 10,
						desktopSize.height - 10);
				

				Session.desk.add(fVisualier);
				Session.desk.setSelectedFrame(fVisualier);
				try {
					fVisualier.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
				
				fVisualier.setTitle("Client# " + textId.getText()); 
				FenetreIdConfirmation.this.dispose();

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

	/**
	 * @return
	 */
	protected ArrayList<CompteCheque> chargerComptesCheque() {


		Connection conn = ConnexionToDB.getInstance().getConnexion();

		try {


			PreparedStatement ps = conn.prepareStatement(SQLQueries.query33);
			ps.setString(1, textId.getText());
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				
				CompteCheque cpt = new CompteCheque(rs.getString(1), rs.getDouble(2), rs.getString(3),

						rs.getDouble(4), format(rs.getTimestamp(5)));	
				comptesCheque.add(cpt);

			}

			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} /* cheque */

		return comptesCheque;


	}

	/**
	 * @return
	 */
	protected ArrayList<CompteEpargne> chargerComptesEpargne() {

		Connection conn = ConnexionToDB.getInstance().getConnexion();

		try {

			PreparedStatement ps = conn.prepareStatement(SQLQueries.query34);
			ps.setString(1, textId.getText());

			ResultSet rs = ps.executeQuery();


			while(rs.next()){
				
				CompteEpargne cpte = new CompteEpargne(rs.getString(1), rs.getDouble(2), rs.getString(3),

						rs.getDouble(4), format(rs.getTimestamp(5)));

				comptesEpargne.add(cpte);

			}
			ps.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} /* cheque */

		return comptesEpargne;
	}

	
	/**
	 * @param timestamp
	 * @return
	 */
	private String  format(Timestamp timestamp) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd HH:mm:ss");
		return dt.format(timestamp);
	}

}
