/**
 * 
 */
package ca.qc.cgodin.Vue;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ca.qc.cgodin.Modele.Session;


public class ClassMain {

	public static void main(String[] args) {

		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "Issue to set look and feel");
		}

		EventQueue.invokeLater(new Runnable() {


			public void run() {
				try {
					FenetrePrincipale frame = new FenetrePrincipale();
					Session.mainWindow = frame;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}// class
