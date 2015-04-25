/* Fichier Tamagotchi.java
Crée le jeudi 9 avril 2015
MAJ : lundi 20 avril 2015
Description : Classe principale du Tamagotchi */

import java.lang.Enum;
import java.util.Observable;
import java.util.Observer;

public abstract class Tamagotchi extends Observable implements Observer
{
	private Fichier save;
	private String nom, type;
	private Besoin nourriture, dormir, hygiene, toilettes, moral;
	private EtatTama vie, fatigue, energie, sante;
	private int majEtats;			// mise à jour états
	private int majFichier;		// timestamp de mise à jour inscrit dans le fichier
	private boolean maisonEC, dormirEC, deplacementEC;	// indique l'état actuel du tamagotchi - en train de dormir ou à la maison, ... - EC pour En Cours
	private boolean notifMaj;		// flag permettant de savoir qu'un état a changer
	public Tamagotchi(Fichier file)
	{
		//System.out.println("Instanciation du tamagotchi général avec le fichier " + file.getNomTama());
		this.save = file;
		this.nom = file.getNomTama();
		this.type = file.getTypeTama();
		this.vie = new EtatTama("vie", file.getEtatInt(2), 1);
		this.nourriture = new Besoin("nourriture", file.getEtatInt(3), 1, true);
		// Calcul du besoin de dormir, qui dépend de la fatigue et de l'énergie
		this.fatigue = new EtatTama("fatigue",file.getEtatInt(4), 2);
		this.dormir = new Besoin("dormir", this.fatigue.getValeur(), 1, true);
		this.energie = new EtatTama("energie", file.getEtatInt(5), 2);
		this.moral = new Besoin("moral", file.getEtatInt(6), 1, false);
		this.sante = new EtatTama("sante", file.getEtatInt(7), 2);
		this.toilettes = new Besoin("toilettes", file.getEtatInt(8), 1, true);
		this.hygiene = new Besoin("hygiene", file.getEtatInt(9), 1, true);
		this.maisonEC = file.getEtatBool(10);
		this.dormirEC = file.getEtatBool(11);
		this.deplacementEC = file.getEtatBool(12);
		this.majFichier = file.getLastMaj();
		this.majEtats = this.majFichier;
	}
	// déclarations des méthodes des classes filles
	abstract String[] getActions();
	public void miseAJour(String etat)
	{
		setChanged();
		notifyObservers(etat);
	}
	public void sauvegarde()
	{
		this.save.majEtat(Etat.VIE, this.vie.getValeur());
		this.save.majEtat(Etat.FAIM, this.nourriture.getValeur());
		this.save.majEtat(Etat.FATIGUE, this.fatigue.getValeur());
		this.save.majEtat(Etat.ENERGIE, this.energie.getValeur());
		this.save.majEtat(Etat.MORAL, this.moral.getValeur());
		this.save.majEtat(Etat.SANTE, this.sante.getValeur());
		this.save.majEtat(Etat.TOILETTES, this.toilettes.getValeur());
		this.save.majEtat(Etat.MAISON, this.maisonEC);
		this.save.majEtat(Etat.DORMIR, this.dormirEC);
		this.save.majEtat(Etat.DEPLACEMENT, this.deplacementEC);
		this.save.sauvegarde();
	}
	public void majBesoin(String besoin, int valeur)
	{
		System.out.println("[Tama] Satisfait le besoin " + besoin + " avec la valeur " + valeur);
		switch(besoin)
		{
			case "nourriture":
				this.nourriture.satisfaire(valeur);
				break;
			case "dormir":
				this.dormir.satisfaire(valeur);
				break;
			case "moral":
				this.moral.satisfaire(valeur);
				break;
			case "toilettes":
				this.toilettes.satisfaire(valeur);
				break;
			case "hygiene":
				this.hygiene.satisfaire(valeur);
				break;
			case "fatigue":
				this.fatigue.satisfaire(valeur);
				break;
			case "energie":
				this.energie.satisfaire(valeur);
				break;
			case "sante":
				this.sante.satisfaire(valeur);
				break;
			default:
				break;
		}
		this.miseAJour(besoin);
		//this.maj = Long.toInteger(System.currentTimeMillis()) / 1000;
	}
	public void majEtat(String etat, boolean valeur)
	{
		switch(etat)
		{
			case "maison":
				this.maisonEC = valeur;
				break;
			case "dormir":
				this.dormirEC = valeur;
				break;
			case "deplacement":
				this.deplacementEC = valeur;
				break;
			default:
				break;
		}
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
	public int getEtatInt(String etat)
	{
		int ret = -1;
		switch(etat)
		{
			case "vie":
				ret = this.vie.getValeur();
				break;
			case "nourriture":
				ret = this.nourriture.getValeur();
				break;
			case "moral":
				ret = this.moral.getValeur();
				break;
			case "toilettes":
				ret = this.toilettes.getValeur();
				break;
			case "hygiene":
				ret = this.hygiene.getValeur();
				break;
			case "fatigue":
				this.fatigue.getValeur();
				break;
			case "energie":
				this.energie.getValeur();
				break;
			case "sante":
				this.sante.getValeur();
				break;
			default:
				ret = -42;
				break;
		}
		return ret;
	}
	public boolean getEtatBool(String etat)
	{
		boolean ret;
		switch(etat)
		{
			case "maison":
				ret = this.maisonEC;
				break;
			case "dormir":
				ret = this.dormirEC;
				break;
			case "deplacement":
				ret = this.deplacementEC;
				break;
			default:
				ret = false;
				break;
		}
		return ret;
	}
	public int getMaj(String type)
	{
		if(type.equals("fichier"))
			return this.majFichier;
		else
			return this.majEtats;
	}
	public String getHumeur()
	{
		return "Imhotep";
	}
	// Setteurs
	@Override
	public void update(Observable mere, Object o)
	{
		
	}
}
