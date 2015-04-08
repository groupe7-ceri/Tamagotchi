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
	private JPanel principal;	// conteneur principal
	public Interface(int x, int y)
	{
		System.out.println("Initialisation de l'interface graphique ...");
		this.x = x;
		this.y = y;
		this.fenetre = new JFrame();
		this.configFenetre();

		// Définition des conteneurs
		JPanel panNom = new JPanel();
		//panNom.setPreferredSize(new Dimension(60, 40));
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
		JButton btQuitter = new JButton("Quitter");
		JButton btRefresh = new JButton("Rafraichir");
		JButton btConfig = new JButton("Config");
		JButton btAbout = new JButton("A propos");
		JButton btManger = new JButton("Manger");
		JButton btDormir = new JButton("Dormir");
		JButton btDouche = new JButton("Douche");
		JButton btWC = new JButton("Toilettes");
		JButton btMoral = new JButton("S'amuser avec");

		// Barres de progression dans panBarre
		JProgressBar barreFaim = new JProgressBar();
		JProgressBar barreEnergie = new JProgressBar();
		JProgressBar barreHygiene = new JProgressBar();
		JProgressBar barreWC = new JProgressBar();
		JProgressBar barreMoral = new JProgressBar();

		// Gestion de la liste déroulante action (les options varient en fonction du type de tamagotchi)
		Choice selectActions = new Choice();
		selectActions.addItem("Sélectionnez");
		selectActions.addItem("Action 1");
		selectActions.addItem("Action 2");

		// Image
		Animation image = new Animation("pikachu.png", panImage.getWidth(), panImage.getHeight());
		panImage = image.getPanel();

		// On rempli la fenetre
		panBoutons.add(btQuitter);
		panBoutons.add(btRefresh);
		panBoutons.add(btConfig);

		panSelect.add(btAbout);
		panSelect.add(selectActions);

		panBarre.add(barreFaim);
		panBarre.add(btManger);
		panBarre.add(barreEnergie);
		panBarre.add(btDormir);
		panBarre.add(barreHygiene);
		panBarre.add(btDouche);
		panBarre.add(barreWC);
		panBarre.add(btWC);
		panBarre.add(barreMoral);
		panBarre.add(btMoral);

		// Placements des JPanel
		this.principal = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		this.principal.add(panNom, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		this.principal.add(panType, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		this.principal.add(panHumeur, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		this.principal.add(panLieu, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		this.principal.add(panLastMAJ, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		this.principal.add(panNextMAJ, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		this.principal.add(panImage, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 3;
		this.principal.add(panBarre, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 2;
		c.gridy = 3;
		this.principal.add(panShortActions, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 4;
		this.principal.add(panBoutons, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 4;
		this.principal.add(panSelect, c);
		// Génération de la fenetre
		this.fenetre.setContentPane(this.principal);
		/*this.fenetre.setLayout(new GridLayout(5, 2));
		this.fenetre.getContentPane().add(panNom);
		this.fenetre.getContentPane().add(panType);
		this.fenetre.getContentPane().add(panHumeur);
		this.fenetre.getContentPane().add(panLieu);
		this.fenetre.getContentPane().add(panLastMAJ);
		this.fenetre.getContentPane().add(panNextMAJ);
		this.fenetre.getContentPane().add(panImage);
		this.fenetre.getContentPane().add(panBarre);
		this.fenetre.getContentPane().add(panShortActions);
		this.fenetre.getContentPane().add(panBoutons);
		this.fenetre.getContentPane().add(panSelect);//*/

		// Rafraichissement de l'interface
		this.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
	}
	private void configFenetre()
	{
		// Configuration de la fenetre
		this.fenetre.setTitle("Tamagotchi");
		this.fenetre.setSize(this.x, this.y);
		this.fenetre.setLocationRelativeTo(null);
		this.fenetre.setResizable(false);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrete le programme quand la fenetre est fermée
	}
	public void rafraichir()
	{

	}
	public void majEtat(String etat, int valeur)
	{
		System.out.println("Met à jour l'indicateur " + etat + " avec la valeur " + valeur);
	}
}
