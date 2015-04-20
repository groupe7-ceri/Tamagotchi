/* Fichier App.java
Crée le dimanche 19 avril 2015
MAJ : dimanche 19 avril 2015
Description : Permet de démarrer le jeu */

import java.io.File;
import javax.swing.JOptionPane;
import java.util.Observer;
import java.util.Observable;

public class App implements Observer
{
	private Fichier fichierActuel;
	private boolean etatFen;
	public App()
	{
		//this.fichierActuel = null;
	}
	@Override
	public void update(Observable obs, Object o)
	{
		if((obs instanceof Creation) || (obs instanceof Selection))
		{
			this.etatFen = ((Boolean) o).booleanValue();
			if(obs instanceof Creation)
			{
				Creation tmpCrea = (Creation) obs;
				this.fichierActuel = tmpCrea.getFichier();
			}
			if(obs instanceof Selection)
			{
				Selection tmpSelect = (Selection) obs;
				this.fichierActuel = tmpSelect.getFichier();
			}
		}
	}
	public void demarrer()
	{
		Selection fenSelection = null;
		Creation fenNouveau = null;
		System.out.println("Projet Tamagotchi - Modélisation UML");
		System.out.println("Elodie Boloré - Jérémie Décome - Thibaut Miranda");
		System.out.println("Version : 0.4");
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
				fenSelection = new Selection(dir);
				fenSelection.addObserver(this);
			}
			else
			{
				fenNouveau = new Creation();
				fenNouveau.addObserver(this);
			}
		}
		else	// On crée un nouveau tamagotchi
		{
			fenNouveau = new Creation();
			fenNouveau.addObserver(this);
		}
		// a revoir
		while(!this.etatFen)
		{
			// rien, on attends que la fenetre soit fermé pour lancer la fenetre principale
		}
		if(fenNouveau != null)
			fenNouveau.deleteObserver(this);
		if(fenSelection != null)
			fenSelection.deleteObserver(this);
		Tamagotchi tama = new Tamagotchi(fichierActuel); // Instanciation de l'objet Tamagotchi avec le fichier
		Principale fenetre = new Principale(tama);
		tama.addObserver(fenetre);
	}
}
