/* Fichier Interface.java
Crée le jeudi 2 avril 2015
MAJ : lundi 20 avril 2015
Description : Classe de base des fenetres */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.awt.event.WindowListener;
import java.util.Observable;

public class Interface extends Observable implements ActionListener, WindowListener//, Runnable
{
	protected int x, y;			// taille de la fenetre
	protected JFrame fenetre;
	protected JPanel principal;	// conteneur principal
	protected boolean etat;		// détermine si la fenetre est fermé
	// Constructeur principal, permet de générer la fenetre principale
	public Interface(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.fenetre = new JFrame();
		this.principal = new JPanel();
	}
	protected void configFenetre(String nomFenetre)
	{
		// Configuration de la fenetre
		this.fenetre.setTitle(nomFenetre);
		this.fenetre.setSize(this.x, this.y);
		this.fenetre.setLocationRelativeTo(null);
		this.fenetre.setResizable(false);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrete le programme quand la fenetre est fermée
	}
	protected void afficherMessage(String message, String titre, int type)
	{
		JOptionPane boite = new JOptionPane();
		boite.showMessageDialog(null, message, titre, type);
	}
	// utile ?
	public boolean getEtat()
	{
		return this.etat;
	}
	/*@Override
	public void run()
	{

	}*/
	@Override
	public void actionPerformed(ActionEvent arg0)
	{

	}
	@Override
    public void windowOpened(WindowEvent e)
    {

    }
    @Override
    public void windowClosing(WindowEvent e)
    {

    }
    @Override
    public void windowClosed(WindowEvent e)
    {
    	System.out.println("Fermeture de la fenetre détectée");
    }
    @Override
    public void windowIconified(WindowEvent e)
    {

    }
    @Override
    public void windowDeiconified(WindowEvent e)
    {

    }
    @Override
    public void windowActivated(WindowEvent e)
    {

    }
    @Override
    public void windowDeactivated(WindowEvent e)
    {

    }
}
