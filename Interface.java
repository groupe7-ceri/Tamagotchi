/* Fichier Interface.java
Description du fichier
Crée le 02/04/2015
MAJ : 06/04/2015 */

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Interface extends JFrame
{
	private int x, y;			// taille de la fenetre
	private JFrame fenetre;
	private JPanel pan;
	public Interface(int x, int y)
	{
		System.out.println("Initialisation de l'interface graphique ...");
		this.x = x;
		this.y = y;
		this.fenetre = new JFrame();
		this.pan = new JPanel(new GridBagLayout());

		// Configuration de la fenetre
		this.fenetre.setTitle("Tamagotchi");
		this.fenetre.setSize(this.x, this.y);
		this.fenetre.setLocationRelativeTo(null);
		this.fenetre.setResizable(false);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrete le programme quand la fenetre est fermée
		
		// Définition des conteneurs
		JPanel panNom = new JPanel();
		JPanel panType = new JPanel();
		JPanel panHumeur = new JPanel();
		JPanel panLieu = new JPanel();
		JPanel panLastMAJ = new JPanel();
		JPanel panNextMAJ = new JPanel();
		JPanel panImage = new JPanel();
		JPanel panBarre = new JPanel();
		JPanel panShortActions = new JPanel();
		JPanel panBoutons = new JPanel();
		JPanel panSelect = new JPanel();
		// Textes résumé haut
		JLabel nom = new JLabel("Nom :");
		panNom.add(nom);
		JLabel type = new JLabel("Type :");
		panType.add(type);
		JLabel humeur = new JLabel("Humeur :");
		panHumeur.add(humeur);
		JLabel lieu = new JLabel("Lieu :");
		panLieu.add(lieu);
		JLabel lastMAJ = new JLabel("Dernière MAJ :");
		panLastMAJ.add(lastMAJ);
		JLabel nextMAJ = new JLabel("Prochaine MAJ :");
		panNextMAJ.add(nextMAJ);
		// Boutons
		JButton bQuitter = new JButton("Quitter");		// panBoutons
		JButton bRefresh = new JButton("Rafraichir");	// panBoutons
		JButton bConfig = new JButton("Config");		// panBoutons
		JButton bAbout = new JButton("A propos");		// panSelect
		JButton bManger = new JButton("Manger");		// panShortActions
		JButton bDormir = new JButton("Dormir");		// panShortActions
		JButton bDouche = new JButton("Douche");		// panShortActions
		JButton bWC = new JButton("Toilettes");			// panShortActions
		JButton bMoral = new JButton("S'amuser avec");	// panShortActions
		// Barres de progression dans panBarre
		JProgressBar barreFaim = new JProgressBar();
		JProgressBar barreEnergie = new JProgressBar();
		JProgressBar barreHygiene = new JProgressBar();
		JProgressBar barreWC = new JProgressBar();
		JProgressBar barreMoral = new JProgressBar();
		// Gestion de la liste déroulante action (en fonction du type de tamagotchi)
		Choice selectActions = new Choice();
		selectActions.addItem("Sélectionnez");
		selectActions.addItem("Action 1");
		selectActions.addItem("Action 2");
		// Image
		Animation image = new Animation("pikachu.png", panImage.getWidth(), panImage.getHeight());
		panImage = image.getPanel();
		// On rempli la fenetre
		panNom.add(bQuitter);
		panNom.add(bRefresh);
		panNom.add(bConfig);
		panSelect.add(bAbout);
		panSelect.add(selectActions);
		
		panBarre.add(barreFaim);
		panBarre.add(bManger);
		panBarre.add(barreEnergie);
		panBarre.add(bDormir);
		panBarre.add(barreHygiene);
		panBarre.add(bDouche);
		panBarre.add(barreWC);
		panBarre.add(bWC);
		panBarre.add(barreMoral);
		panBarre.add(bMoral);
		this.fenetre.setLayout(new GridLayout(5, 2));
		this.fenetre.getContentPane().add(panNom);
		this.fenetre.getContentPane().add(panType);
		this.fenetre.getContentPane().add(panHumeur);
		this.fenetre.getContentPane().add(panLieu);
		this.fenetre.getContentPane().add(panLastMAJ);
		this.fenetre.getContentPane().add(panNextMAJ);
		this.fenetre.getContentPane().add(panImage);
		this.fenetre.getContentPane().add(panBarre);
		this.fenetre.getContentPane().add(panShortActions);
		this.fenetre.getContentPane().add(panNom);
		this.fenetre.getContentPane().add(panSelect);
		// Rafraichissement de l'interface
		this.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
	}
	public void rafraichir()
	{

	}
	public void majEtat(String etat, int valeur)
	{
		System.out.println("Met à jour l'indicateur " + etat + " avec la valeur " + valeur);
	}
}
