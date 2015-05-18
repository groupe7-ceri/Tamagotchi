/* Fichier Principale.java
Crée le dimanche 12 avril 2015
MAJ : lundi 18 mai 2015
Description : Fenetre principale du programme */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowListener;
import java.util.Observer;
import java.util.Observable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.Format;
import java.sql.Timestamp;


public class Principale extends Interface implements Observer, Runnable
{
	private Choice selectActions;
	private Tamagotchi tama;
	private JProgressBar barreVie, barreFaim, barreEnergie, barreHygiene, barreWC, barreMoral;
	private JLabel humeur, lieu, lastMAJ, nextMAJ;
	@Override
	public void update(Observable obs, Object o)
	{
		String msg = ((String) o).toString();
		switch(msg)
		{
			case "vie":
				this.barreVie.setValue(this.tama.getEtatInt("vie"));
				this.barreVie.setStringPainted(true);
				break;
			case "nourriture":
				this.barreFaim.setValue(this.tama.getEtatInt("nourriture"));
				this.barreFaim.setStringPainted(true);
				break;
			case "moral":
				this.barreMoral.setValue(this.tama.getEtatInt("moral"));
				this.barreMoral.setStringPainted(true);
				break;
			case "toilettes":
				this.barreWC.setValue(this.tama.getEtatInt("toilettes"));
				this.barreWC.setStringPainted(true);
				break;
			case "hygiene":
				this.barreHygiene.setValue(this.tama.getEtatInt("hygiene"));
				this.barreHygiene.setStringPainted(true);
				break;
			case "fatigue":
				this.barreEnergie.setValue(this.tama.getEtatInt("fatigue"));
				this.barreEnergie.setStringPainted(true);
				break;
			case "energie":
				this.barreMoral.setValue(this.tama.getEtatInt("energie"));
				this.barreMoral.setStringPainted(true);
				break;
			case "sante":
				break;
			default:
				break;
		}
		// Rafraichi les JLabel
		this.humeur.setText("Humeur : " + tama.getHumeur());
		this.lieu.setText("Lieu : " + this.afficheLieu(tama.getEtatBool("maison")));
		this.lastMAJ.setText("Dernière MAJ : " + this.timestampToDate(tama.getMaj("fichier")));
		this.nextMAJ.setText("Prochaine MAJ : " + this.timestampToDate(tama.getMaj("") + 60)); // faire un get sur l'intervalle pour le 60 dans le tamagotchi
		this.rafraichir();
	}
	@Override
	public void run()
	{
		
	}
	public Principale(Tamagotchi tama)
	{
		super(800, 400);
		this.tama = tama;
		super.configFenetre("Tamagotchi " + this.tama.getNom());
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
		JLabel nom = new JLabel("Nom : " + tama.getNom());
		JLabel type = new JLabel("Type : " + tama.getType());
		this.humeur = new JLabel("Humeur : " + tama.getHumeur());
		this.lieu = new JLabel("Lieu : " + this.afficheLieu(tama.getEtatBool("maison")));
		this.lastMAJ = new JLabel("Dernière MAJ : " + this.timestampToDate(tama.getMaj("fichier")));
		this.nextMAJ = new JLabel("Prochaine MAJ :");
		
		// Boutons (panel raccourci action et boutons divers)
		JButton btQuitter = new JButton("Quitter");
		JButton btRefresh = new JButton("Rafraichir");
		JButton btAbout = new JButton("A propos");
		JButton btManger = new JButton("Manger");
		JButton btDormir = new JButton("Dormir");
		JButton btDouche = new JButton("Douche");
		JButton btWC = new JButton("Toilettes");
		JButton btMoral = new JButton("S'amuser avec");

		// Barres de progression dans panBarre (et textes)
		this.barreVie = new JProgressBar(0, 100);
		this.barreFaim = new JProgressBar(0, 100);
		this.barreEnergie = new JProgressBar();
		this.barreHygiene = new JProgressBar();
		this.barreWC = new JProgressBar();
		this.barreMoral = new JProgressBar();
		JLabel lbVie = new JLabel("Vie");
		JLabel lbFaim = new JLabel("Nourriture");
		JLabel lbEnergie = new JLabel("Energie");
		JLabel lbHygiene = new JLabel("Hygiène");
		JLabel lbWC = new JLabel("Toilettes");
		JLabel lbMoral = new JLabel("Moral / Bonheur");

		// Gestion de la liste déroulante action (les options varient en fonction du type de tamagotchi)
		this.selectActions = new Choice();
		this.selectActions.addItem("Sélectionnez");
		String[] actions = this.tama.getActions();
		for(int i = 0; i < actions.length; i++)
			this.selectActions.addItem(actions[i]);
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

		panSelect.add(selectActions);
		panSelect.add(btAction);
		panSelect.add(btAbout);

		panBarre.setLayout(new BoxLayout(panBarre, BoxLayout.PAGE_AXIS));
		panBarre.add(lbVie);
		panBarre.add(this.barreVie);
		panBarre.add(lbFaim);
		panBarre.add(this.barreFaim);
		panBarre.add(lbEnergie);
		panBarre.add(this.barreEnergie);
		panBarre.add(lbHygiene);
		panBarre.add(this.barreHygiene);
		panBarre.add(lbWC);
		panBarre.add(this.barreWC);
		panBarre.add(lbMoral);
		panBarre.add(this.barreMoral);

		int espace = 15;
		panShortActions.setLayout(new BoxLayout(panShortActions, BoxLayout.PAGE_AXIS));
		panShortActions.add(btManger);
		panShortActions.add(Box.createRigidArea(new Dimension(0, espace)));
		panShortActions.add(btDormir);
		panShortActions.add(Box.createRigidArea(new Dimension(0, espace)));
		panShortActions.add(btDouche);
		panShortActions.add(Box.createRigidArea(new Dimension(0, espace)));
		panShortActions.add(btWC);
		panShortActions.add(Box.createRigidArea(new Dimension(0, espace)));
		panShortActions.add(btMoral);

		if(!(tama instanceof Vivant))
		{
			btManger.setEnabled(false);
			btDormir.setEnabled(false);
			btDouche.setEnabled(false);
			btWC.setEnabled(false);
			btMoral.setEnabled(false);
		}
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

		// Initialisation des progress barres
		this.barreVie.setStringPainted(true);
		this.barreVie.setValue(this.tama.getEtatInt("vie"));
		this.barreFaim.setStringPainted(true);
		this.barreFaim.setValue(tama.getEtatInt("nourriture"));
		this.barreEnergie.setStringPainted(true);
		this.barreEnergie.setValue(tama.getEtatInt("energie"));
		this.barreWC.setStringPainted(true);
		this.barreWC.setValue(tama.getEtatInt("toilettes"));
		this.barreHygiene.setStringPainted(true);
		this.barreHygiene.setValue(tama.getEtatInt("hygiene"));
		this.barreMoral.setStringPainted(true);
		this.barreMoral.setValue(tama.getEtatInt("moral"));

		// Attachement des évenements sur les boutons
		btQuitter.addActionListener(this);
		btRefresh.addActionListener(this);
		btAction.addActionListener(this);
		btAbout.addActionListener(this);
		btManger.addActionListener(this);
		btDormir.addActionListener(this);
		btDouche.addActionListener(this);
		btWC.addActionListener(this);
		btMoral.addActionListener(this);

		this.rafraichir();
		// Génération de la fenetre
		super.fenetre.setContentPane(this.principal);
		super.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
	}
	private String afficheLieu(boolean maison)
	{
		if(maison)
			return "Chez lui";
		else
		{
			return "Dehors";
		}
	}
	private String timestampToDate(long timestamp)
	{
		//System.out.println("Timestamp en entrée : " + timestamp);
		Date date = new Date(timestamp * 1000L);
		Format format = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
		return format.format(date);
	}
	public void actionPerformed(ActionEvent arg0)
	{
		boolean refresh = false;
		JButton bt = (JButton) arg0.getSource();
		switch(bt.getText())
		{
			case "Quitter":
			{
				System.out.println("Sauvegarde, autres actions nécessaire et arret de l'application");
				this.tama.sauvegarde();
				System.exit(0);
			}
				break;
			case "Rafraichir":
			{
				refresh = true;
			}
				break;
			case "Manger":
			{
				if(!this.tama.getEtatBool("dormir") && this.tama.getEtatBool("maison"))
					tama.majBesoin("nourriture", 20);
				else
					super.afficherMessage(this.tama.getNom() + " est en train de dormir ou n'est pas chez lui, il ne peut pas manger. ", "Erreur", JOptionPane.ERROR_MESSAGE);
				refresh = true;
			}
				break;
			case "Dormir":
			{
				if(!this.tama.getEtatBool("maison"))
				{
					JOptionPane jop = new JOptionPane();
					// le tamagotchi doit etre dehors pour s'amuser
					int option = jop.showConfirmDialog(null, this.tama.getNom() + " n'est pas chez lui, voulez vous le faire rentrer pour qu'il dorme ?", "Sieste", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option == JOptionPane.OK_OPTION)
					{
						this.tama.majEtat("maison", true);
						this.tama.majEtat("dormir", true);
						super.afficherMessage(this.tama.getNom() + " est en train de dormir. ", "Dodo", JOptionPane.INFORMATION_MESSAGE);
						refresh = true;
					}
					else
					{

					}
				}
				refresh = true;
			}
				break;
			case "Douche":
			{
				tama.majBesoin("hygiene", 20);
				refresh = true;
			}
				break;
			case "Toilettes":
			{
				tama.majBesoin("toilettes", 20);
				refresh = true;
			}
				break;
			case "S'amuser avec":
			{
				if(this.tama.getEtatBool("maison"))
				{
					JOptionPane jop = new JOptionPane();
					// le tamagotchi doit etre dehors pour s'amuser
					int option = jop.showConfirmDialog(null, this.tama.getNom() + " est chez lui, voulez vous le faire sortir pour qu'il s'amuse ?", "Jeu", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(option == JOptionPane.OK_OPTION)
					{
						refresh = true;
					}
					else
					{

					}
				}
			}
				break;
			case "Effectuer":
			{
				this.tama.effectuerAction(this.selectActions.getSelectedItem());
				refresh = true;
			}
				break;
			case "A propos":
			{
				String texte = "UE - Modélisation objet et UML\n";
				texte += "Projet Conception, modélisation et réalisation d'un tamagotchi\n";
				texte += "Version : 0.4.5\n";
				texte += "Auteurs :\n";
				texte += "- Elodie Boloré\n";
				texte += "- Jérémie Décome\n";
				texte += "- Thibaut Miranda\n";
				super.afficherMessage(texte, "A propos", JOptionPane.ERROR_MESSAGE);
			}
				break;
			default:
				break;
		}
		if(refresh)
		{
			this.rafraichir();
		}
	}
	//@Override
	public void rafraichir()
	{
		System.out.println("Modif de la couleur des JProgressbar et de l'animation");
		this.barreFaim.setStringPainted(true);
		if(this.tama.getEtatInt("nourriture") > 80)
			this.barreFaim.setForeground(Color.RED);
		else
			this.barreFaim.setForeground(Color.GREEN);
		this.barreFaim.repaint();

		this.barreEnergie.setStringPainted(true);
		if(this.tama.getEtatInt("energie") > 80)
			this.barreEnergie.setForeground(Color.RED);
		else
			this.barreEnergie.setForeground(Color.GREEN);
		this.barreEnergie.repaint();

		this.barreHygiene.setStringPainted(true);
		if(this.tama.getEtatInt("hygiene") > 80)
			this.barreHygiene.setForeground(Color.RED);
		else
			this.barreHygiene.setForeground(Color.GREEN);
		this.barreHygiene.repaint();

		this.barreWC.setStringPainted(true);
		if(this.tama.getEtatInt("toilettes") > 80)
			this.barreWC.setForeground(Color.RED);
		else
			this.barreWC.setForeground(Color.GREEN);
		this.barreWC.repaint();

		this.barreMoral.setStringPainted(true);
		if(this.tama.getEtatInt("moral") > 80)
			this.barreMoral.setForeground(Color.RED);
		else
			this.barreMoral.setForeground(Color.GREEN);
		this.barreMoral.repaint();

		// Couleur de la barre de vie
		this.barreVie.setStringPainted(true);
		if(this.tama.getEtatInt("vie") < 10)
			this.barreVie.setForeground(Color.RED);
		else
			this.barreVie.setForeground(Color.GREEN);
		this.barreVie.repaint();
		super.rafraichir();
	}
	public void majEtat(String etat, int valeur)
	{
		System.out.println("Met à jour l'indicateur " + etat + " avec la valeur " + valeur + " sur l'interface");
	}
}
