/* Fichier Creation.java
Crée le dimanche 12 avril 2015
MAJ : dimanche 12 avril 2015
Description : Permet de créer une fenetre de création d'un Tamagotchi */

import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

public class Creation extends Interface implements Runnable
{
	private JTextField saisieNom;
	private Choice selectType;
	private String[] typesTama = {"Chat", "Chien", "Droide", "Bactérie", "Pokémon", "Brique"};	// uniquement les classes non abstraites (finales)
	private Fichier fichier;
	public Creation()
	{
		super(300, 200);	// on dessine la fenetre en elle meme (via la classe mère Interface)
		super.configFenetre("Création d'un tamagotchi");
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
		super.principal = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		super.principal.add(panNom, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		super.principal.add(panType, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		super.principal.add(panBoutons, c);

		// Attachement des évenements sur les boutons
		btValider.addActionListener(this);
		btAnnuler.addActionListener(this);

		super.fenetre.setContentPane(this.principal);
		super.fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
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
					String valeur = this.selectType.getSelectedItem();
					if((valeur != "Sélectionnez") && (Arrays.asList(this.typesTama).contains(valeur)))	// vérifie que le type est bien définie dans l'appli
					{
						// tout est ok, on peut créer le fichier texte
						fichier = new Fichier(this.saisieNom.getText(), valeur);
						this.etat = true;
						setChanged();
						notifyObservers(this.etat);
						this.fenetre.dispose();		// ferme de la fenetre
					}
					else
					{
						super.afficherMessage("Ce type de tamagotchi est inconnu, veuillez en choisir un dans la liste", "Type inconnu", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					super.afficherMessage("Vous devez donner un nom à votre tamagotchi !", "Pas de nom", JOptionPane.ERROR_MESSAGE);
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
	@Override
	public void run()
	{

	}
}
