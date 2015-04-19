/* Fichier Tamagotchi.java
Crée le jeudi 9 avril 2015
MAJ : jeudi 9 avril 2015
Description : Classe principale du Tamagotchi */

import java.util.Observable;
import java.util.Observer;

public class Tamagotchi extends Observable implements Runnable, Observer
{
	private int vie, nourriture, fatigue, energie, moral, sante, toilettes;
	private int majFichier;		// timestamp de mise à jour inscrit dans le fichier
	private boolean maison, dormir, deplacement;
	public Tamagotchi(Fichier file)
	{
		this.vie = file.getEtatInt(2);
		this.nourriture = file.getEtatInt(3);
		this.fatigue = file.getEtatInt(4);
		this.energie = file.getEtatInt(5);
		this.moral = file.getEtatInt(6);
		this.sante = file.getEtatInt(7);
		this.toilettes = file.getEtatInt(8);
		this.maison = file.getEtatBool(10);
		this.dormir = file.getEtatBool(11);
		this.deplacement = file.getEtatBool(12);
		this.majFichier = file.getLastMaj();
	}
	@Override
	public void run()
	{
		
	}
	@Override
	public void update(Observable mere, Object o)
	{
		
	}
}
