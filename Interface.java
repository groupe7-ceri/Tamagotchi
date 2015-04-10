/* Fichier Interface.java
Description du fichier
Crée le 02/04/2015
MAJ : 06/04/2015 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.awt.event.WindowListener;


public class Interface extends JFrame implements ActionListener, WindowListener
{
	private int x, y;			// taille de la fenetre
	private JFrame fenetre;
	private JPanel principal;	// conteneur principal
	private JTextField saisieNom;
	private Choice selectType;
	private String[] typesTama = {"Humain", "Animal", "Robot", "Brique", "Bactérie"};
	private boolean etat;		// détermine si la fenetre est fermé
	// Constructeur principal, permet de générer la fenetre principale
	public Interface(Tamagotchi tama)
	{
		System.out.println("Initialisation de l'interface graphique ...");
		this.x = 800;
		this.y = 300;
		this.fenetre = new JFrame();
		this.configFenetre("Tamagotchi - Nom");

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

		// Attachement des évenements sur les boutons
		//btValider.addActionListener(this);

		// Génération de la fenetre
		this.fenetre.setContentPane(this.principal);
		this.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
	}
	// Constructeur surchargé permettant d'afficher l'interface de création d'un tamagotchi
	public Interface(boolean nouveau, int x, int y)
	{
		this.x = x;
		this.y = y;
		this.fenetre = new JFrame();
		if(nouveau)		// Création de la fenetre de création d'un tamagotchi
		{
			this.configFenetre("Création d'un tamagotchi");
			// Définition des conteneurs
			JPanel panNom = new JPanel();
			JPanel panType = new JPanel();
			JPanel panBoutons = new JPanel();

			// Textes
			JLabel nom = new JLabel("Nom :");
			JLabel type = new JLabel("Type :");
			
			// Boutons
			JButton btValider = new JButton("Valider");
			JButton btAnnuler = new JButton("Annuler");

			// Liste déroulante type
			this.selectType = new Choice();
			selectType.addItem("Sélectionnez");
			for(int i = 0; i < this.typesTama.length; i++)
			{
				selectType.addItem(this.typesTama[i]);
			}

			// Champ de saise
			this.saisieNom = new JTextField();
			this.saisieNom.setPreferredSize(new Dimension(70, 20));

			// On rempli la fenetre (et configuration des layouts)
			panNom.add(nom);
			panNom.add(saisieNom);
			panType.add(type);
			panType.add(selectType);

			panBoutons.add(btValider);
			panBoutons.add(btAnnuler);

			// Placements des JPanel
			this.principal = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1;
			c.gridx = 0;
			c.gridy = 0;
			this.principal.add(panNom, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridx = 0;
			c.gridy = 1;
			this.principal.add(panType, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridx = 0;
			c.gridy = 2;
			this.principal.add(panBoutons, c);

			// Attachement des évenements sur les boutons
			btValider.addActionListener(this);
			btAnnuler.addActionListener(this);
		}
		else 		// création de la fenetre de sélection d'un tamagotchi
		{

		}
		// Génération de la fenetre
		this.fenetre.setContentPane(this.principal);
		this.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
	}
	private void configFenetre(String nomFenetre)
	{
		// Configuration de la fenetre
		this.fenetre.setTitle(nomFenetre);
		this.fenetre.setSize(this.x, this.y);
		this.fenetre.setLocationRelativeTo(null);
		this.fenetre.setResizable(false);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrete le programme quand la fenetre est fermée
		this.fenetre.addWindowListener(this);
	}
	private void afficherMessage(String message, String titre, int type)
	{
		JOptionPane boite = new JOptionPane();
		boite.showMessageDialog(null, message, titre, type);
	}
	public void actionPerformed(ActionEvent arg0)
	{
		JButton bt = (JButton) arg0.getSource();
		switch(bt.getText())
		{
			case "Valider":
			{
				if(!this.saisieNom.getText().equals("")) // vérification du champ saisieNom
				{
					System.out.println("[Validation] saisieNom vaut : " + this.saisieNom.getText());
					System.out.println("[Validation] selectType vaut : " + this.selectType.getSelectedItem());
					String valeur = this.selectType.getSelectedItem();
					if((valeur != "Sélectionnez") && (Arrays.asList(this.typesTama).contains(valeur)))	// vérifie que le type est bien définie dans l'appli
					{
						System.out.println("Ok");
						// tout est ok, on peut créer le fichier texte
						this.etat = true;
						this.fenetre.dispose();		// ferme de la fenetre
					}
					else
					{
						// Type de tamagotchi inconnu
						this.afficherMessage("Ce type de tamagotchi est inconnu, veuillez en choisir un dans la liste", "Type inconnu", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					this.afficherMessage("Vous devez donner un nom à votre tamagotchi !", "Pas de nom", JOptionPane.ERROR_MESSAGE);
				}
			}
			break;
			case "Annuler":
			{
				System.out.println("Bouton Annuler cliqué, on ferme l'application");
			}
			break;
			default:
				break;

		}
	}
	public String getValeur(String type)
	{
		switch(type)
		{
			case "nom":
				return this.saisieNom.getText();
			case "type":
				return this.selectType.getSelectedItem();
			default:
				return null;
		}
	}
	public String[] getFichier()
	{
		System.out.println("Retourne le fichier associé");
		return null;
	}
	public boolean getEtat()
	{
		return this.etat;
	}
	public void rafraichir()
	{

	}
	public void majEtat(String etat, int valeur)
	{
		System.out.println("Met à jour l'indicateur " + etat + " avec la valeur " + valeur);
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
