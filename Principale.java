/* Fichier Principale.java
Crée le dimanche 12 avril 2015
MAJ : dimanche 12 avril 2015
Description : Fenetre principale du programme */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowListener;

public class Principale extends Interface
{
	public Principale(Tamagotchi tama)
	{
		super(800, 300); // 800, 300
		super.configFenetre("Tamagotchi - Nom");
		System.out.println("Initialisation de l'interface graphique ...");
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
		JLabel type = new JLabel("Type :");
		JLabel humeur = new JLabel("Humeur :");
		JLabel lieu = new JLabel("Lieu :");
		JLabel lastMAJ = new JLabel("Dernière MAJ :");
		JLabel nextMAJ = new JLabel("Prochaine MAJ :");
		
		// Boutons (panel raccourci action et boutons divers)
		JButton btQuitter = new JButton("Quitter");
		JButton btRefresh = new JButton("Rafraichir");
		JButton btConfig = new JButton("Config");
		JButton btAbout = new JButton("A propos");
		JButton btManger = new JButton("Manger");
		JButton btDormir = new JButton("Dormir");
		JButton btDouche = new JButton("Douche");
		JButton btWC = new JButton("Toilettes");
		JButton btMoral = new JButton("S'amuser avec");

		// Barres de progression dans panBarre (et textes)
		JProgressBar barreFaim = new JProgressBar();
		JProgressBar barreEnergie = new JProgressBar();
		JProgressBar barreHygiene = new JProgressBar();
		JProgressBar barreWC = new JProgressBar();
		JProgressBar barreMoral = new JProgressBar();
		JLabel lbFaim = new JLabel("Nourriture");
		JLabel lbEnergie = new JLabel("Energie");
		JLabel lbHygiene = new JLabel("Hygiène");
		JLabel lbWC = new JLabel("Toilettes");
		JLabel lbMoral = new JLabel("Moral / Bonheur");

		// Gestion de la liste déroulante action (les options varient en fonction du type de tamagotchi)
		Choice selectActions = new Choice();
		selectActions.addItem("Sélectionnez");
		selectActions.addItem("Action 1");
		selectActions.addItem("Action 2");
		JButton btAction = new JButton("Effectuer");

		// Image
		Animation image = new Animation("images/pikachu.png", panImage.getWidth(), panImage.getHeight());
		panImage = image.getPanel();

		// On rempli la fenetre (et configuration des layouts)
		panNom.add(nom);
		panType.add(type);
		panHumeur.add(humeur);
		panLieu.add(lieu);
		panLastMAJ.add(lastMAJ);
		panNextMAJ.add(nextMAJ);

		panBoutons.add(btQuitter);
		panBoutons.add(btRefresh);
		panBoutons.add(btConfig);

		panSelect.add(selectActions);
		panSelect.add(btAction);
		panSelect.add(btAbout);

		panBarre.setLayout(new BoxLayout(panBarre, BoxLayout.PAGE_AXIS));
		panBarre.add(lbFaim);
		panBarre.add(barreFaim);
		panBarre.add(lbEnergie);
		panBarre.add(barreEnergie);
		panBarre.add(lbHygiene);
		panBarre.add(barreHygiene);
		panBarre.add(lbWC);
		panBarre.add(barreWC);
		panBarre.add(lbMoral);
		panBarre.add(barreMoral);

		panShortActions.setLayout(new BoxLayout(panShortActions, BoxLayout.PAGE_AXIS));
		panShortActions.add(btManger);
		panShortActions.add(btDormir);
		panShortActions.add(btDouche);
		panShortActions.add(btWC);
		panShortActions.add(btMoral);

		// Placements des JPanel
		super.principal = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		super.principal.add(panNom, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		super.principal.add(panType, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		super.principal.add(panHumeur, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		super.principal.add(panLieu, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		super.principal.add(panLastMAJ, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		super.principal.add(panNextMAJ, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 3;
		super.principal.add(panImage, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 3;
		super.principal.add(panBarre, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 2;
		c.gridy = 3;
		super.principal.add(panShortActions, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 4;
		super.principal.add(panBoutons, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 1;
		c.gridy = 4;
		super.principal.add(panSelect, c);

		// Attachement des évenements sur les boutons
		//btValider.addActionListener(this);

		// Génération de la fenetre
		super.fenetre.setContentPane(this.principal);
		super.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
	}
}