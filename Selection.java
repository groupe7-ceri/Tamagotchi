/* Fichier Selection.java
Crée le dimanche 12 avril 2015
MAJ : dimanche 12 avril 2015
Description : Permet de créer une fenetre de sélection de Tamagotchi */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowListener;

public class Selection extends Interface
{
	private Fichier fichier;
	private Choice selectTama;
	// liste contient la liste des fichiers de sauvegarde
	public Selection(String[] liste)
	{
		super(300, 200);	// on dessine la fenetre en elle meme (via la classe mère Interface)
		super.configFenetre("Sélection d'un tamagotchi");
		// Définition des conteneurs
		JPanel panSelecteur = new JPanel();
		JPanel panBoutons = new JPanel();

		// Textes
		JLabel txt = new JLabel("Choix d'une sauvegarde :");
		
		// Boutons
		JButton btValider = new JButton("Valider");
		JButton btAnnuler = new JButton("Annuler");

		// Liste déroulante type
		this.selectTama = new Choice();
		this.selectTama.addItem("Sélectionnez");
		for(int i = 0; i < liste.length; i++)
		{
			//nom = this.lireNomSauvegarde(liste[i]);
			//type = this.lireTypeSauvegarde(liste[i]);
			//String chaine = nom + " - " + type;
			//System.out.println("!" + chaine + "!");
			// Traitement du nom du fichier (pour faire quelque chose de plus propre)
			this.selectTama.addItem(liste[i]);
		}

		// On rempli la fenetre (et configuration des layouts)
		panSelecteur.add(txt);
		panSelecteur.add(this.selectTama);

		panBoutons.add(btValider);
		panBoutons.add(btAnnuler);

		// Placements des JPanel
		super.principal = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		super.principal.add(panSelecteur, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		super.principal.add(panBoutons, c);


		// Attachement des évenements sur les boutons
		btValider.addActionListener(this);
		btAnnuler.addActionListener(this);
		super.fenetre.setContentPane(super.principal);
		super.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
	}
	public void actionPerformed(ActionEvent arg0)
	{
		JButton bt = (JButton) arg0.getSource();
		switch(bt.getText())
		{
			case "Valider":
			{
				String valeur = this.selectTama.getSelectedItem();
				if(valeur != "Sélectionnez")	// vérifie que le type est bien définie dans l'appli
				{
					// tout est ok, on peut créer le fichier texte
					this.fichier = new Fichier(valeur);
					this.etat = true;
					setChanged();
					notifyObservers(this.etat);
					this.fenetre.dispose();		// ferme de la fenetre
				}
				else
				{
					// Type de tamagotchi inconnu
					super.afficherMessage("Ce type de tamagotchi est inconnu, veuillez en choisir un dans la liste", "Type inconnu", JOptionPane.ERROR_MESSAGE);
				}	
			}
			break;
			case "Annuler":
			{
				System.exit(0);
			}
			break;
			default:
				break;

		}
	}
	public Fichier getFichier()
	{
		return this.fichier;
	}
}
