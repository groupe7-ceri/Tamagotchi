/* Fichier Tamagotchi.java
Crée le jeudi 9 avril 2015
MAJ : samedi 9 mai 2015
Description : Classe principale du Tamagotchi */

import java.lang.Enum;
import java.util.Observable;
import java.util.Observer;
import java.util.Arrays;
import java.util.Timer;

public abstract class Tamagotchi extends Observable implements Observer, Runnable
{
	protected Fichier save;
	private int majEtats;			// mise à jour états
	private int majFichier;			// timestamp de mise à jour inscrit dans le fichier	
	private boolean notifMaj;		// flag permettant de savoir qu'un état a changer
	private String nom, type;
	private Timer horloge;

	// Besoins et états commun
	protected EtatTama vie;
	protected boolean seDeplace; // caractéristiques commune
	public Tamagotchi(Fichier file, boolean seDeplace)
	{
		this.save = file;
		this.nom = file.getNomTama();
		this.type = file.getTypeTama();
		this.vie = new EtatTama("vie", file.getEtatInt(2), 1, false);
		this.majFichier = file.getLastMaj();
		this.majEtats = this.majFichier;
		this.seDeplace = seDeplace;
		//this.timer = new Timer(10 * 1000);
	}
	// déclarations des méthodes des classes filles
	abstract String[] getActions();
	abstract void effectuerAction(String action);
	abstract void majBesoin(String besoin, int valeur);
	abstract void majEtat(String etat, boolean valeur);
	abstract int getEtatInt(String etat);
	abstract boolean getEtatBool(String etat);
	abstract String getHumeur();
	abstract void rafraichissement();
	//abstract int getIntervalle();
	// Méthodes privées
	

	// implémentation des méthodes communes à tout le monde
	public void run()
	{
		this.vie.vie();
		this.miseAJour("vie"); // envoi un message à la fenetre principale
	}
	public void miseAJour(String etat)
	{
		setChanged();
		notifyObservers(etat);
	}
	public void sauvegarde()
	{
		this.save.majEtat(Etat.VIE, this.vie.getValeur());
		this.save.sauvegarde();
	}
	// Getteurs
	public String getNom()
	{
		return this.nom;
	}
	public String getType()
	{
		return this.type;
	}
	public boolean getDeplacement()
	{
		return this.seDeplace;
	}
	public int getMaj(String type)
	{
		if(type.equals("fichier"))
			return this.majFichier;
		else
			return this.majEtats;
	}
	// Setteurs
	@Override
	public void update(Observable mere, Object o)
	{
		
	}
}
