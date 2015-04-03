/* Fichier Interface.java
Description du fichier
Crée le 02/04/2015
MAJ : 03/04/2015 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.*;

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
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrete le programme quand la fenetre est fermée
		// Définition des conteneurs
		JPanel ligneNom = new JPanel();			// Ligne 1 - Haut affichage des informations 1 (nom et type)
		ligneNom.setBackground(Color.red);
		ligneNom.setPreferredSize(new Dimension(60, 40));
		JPanel ligneHumeur = new JPanel();		// Ligne 2 - Haut affichage des informations 2 (Humeur et lieu)
		ligneHumeur.setBackground(Color.green);
		ligneHumeur.setPreferredSize(new Dimension(60, 40));
		JPanel ligneMAJ = new JPanel();			// Ligne 3 - Haut affichage des informations 3 (Dernière et prochaine MAJ)
		ligneHumeur.setBackground(Color.BLUE);
		ligneHumeur.setPreferredSize(new Dimension(60, 40));
		JPanel ligneCentre = new JPanel();		// Ligne 4 - Image tamagotchi et barre de progression état
		ligneHumeur.setBackground(Color.black);
		ligneHumeur.setPreferredSize(new Dimension(60, 40));
		JPanel ligneBas = new JPanel();			// Ligne 5 - Bas Boutons et sélecteur actions
		ligneBas.setBackground(Color.YELLOW);
		ligneBas.setPreferredSize(new Dimension(60, 40));
		JPanel content = new JPanel();			// Conteneur principal
		content.setPreferredSize(new Dimension(this.x, this.y));
		content.setBackground(Color.WHITE);
		content.setLayout(new GridBagLayout());	// layout manager
		GridBagConstraints c = new GridBagConstraints();
		// La ligne nom est sur la première ligne (x = 0, y = )
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		content.add(ligneNom, c);
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		content.add(ligneHumeur, c);
		c.gridy = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		content.add(ligneMAJ, c);
		c.gridy = 3;
		c.gridwidth = GridBagConstraints.REMAINDER;
		content.add(ligneCentre, c);
		c.gridy = 4;
		content.add(ligneBas, c);
		// La ligne humeur est sur la deuxième ligne

		// La ligne MAJ est sur la troisième ligne

		// La ligne Centre est sur la quatrième ligne

		// La ligne Bas est sur la cinquième ligne

		// Textes résumé haut
		// Boutons
		JButton bQuitter = new JButton("Quitter");
		JButton bRefresh = new JButton("Rafraichir");
		JButton bConfig = new JButton("Config");
		JButton bAbout = new JButton("A propos");
		// Barre de progression

		// Liste déroulante action

		// Rafraichissement de l'interface
		this.fenetre.setContentPane(content);
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
