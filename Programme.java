/* Fichier Programme.java 
Classe principal permettant de gérer le jeu 
MAJ : 2 avril 2015 */

import java.io.File;
import javax.swing.JOptionPane;

public class Programme
{
	private Tamagotchi creerTamagotchi(String[] fichier)
	{
		return null;
	}
	public static void main(String[] args)
	{
		boolean nouveau = false;	// tamagotchi vierge ou non
		Interface fenetreNouveau = null;
		System.out.println("Projet Tamagotchi - Modélisation UML");
		System.out.println("Elodie Boloré - Jérémie Décome - Thibaut Miranda");
		System.out.println("Version : 0.1.2");
		System.out.println("Démarrage de l'application ...");
		// Détermine si le tamagotchi est nouveau (pas de fichier de sauvegarde) ou non (on propose à l'utilisateur de sélectionner un tamagotchi)
		String[] dir = new File("saves/").list();
		if(dir.length > 0)
		{
			JOptionPane jop = new JOptionPane();			
			int option = jop.showConfirmDialog(null, "Un ou plusieurs tamagotchis ont été trouvés, voulez vous charger une partie ?", "Lancement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			// On sélectionne (ou créé le fichier de sauvegarde)
			if(option == JOptionPane.OK_OPTION)
			{
				System.out.println("Fenetre de sélection du tamagotchi");
			}
			else
			{
				System.out.println("Création d'un nouveau tamagotchi");
				fenetreNouveau = new Interface(300, 200);
			}
		}
		// La fenetre création tamagotchi est fermé, on peut afficher la fenetre principale
		if((fenetreNouveau != null) && (fenetreNouveau.getEtat()))
		{
			Tamagotchi tama = new Tamagotchi(); // Instanciation de l'objet Tamagotchi
		
			// Hydratation de l'objet Tamagotchi avec un nouveau tamagotchi (si nouveau) ou celui sélectionné
			Interface fenetre = new Interface(tama);
		}
	}
}
