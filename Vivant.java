/* Fichier Vivant.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe abstraite des types  */

public abstract class Vivant extends Tamagotchi
{
	private Besoin nourriture, dormir, hygiene, toilettes, moral;
	private EtatTama fatigue, energie, sante;
	protected boolean maisonEC, dormirEC, deplacementEC;	// indique l'état actuel du tamagotchi - en train de dormir ou à la maison, ... - EC pour En Cours
	private boolean deplacement;
	public Vivant(Fichier file)
	{
		super(file, true);
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
	}
	abstract String[] getActions();
	abstract void effectuerAction(String action);

	public void sauvegarde()
	{
		super.save.majEtat(Etat.FAIM, this.nourriture.getValeur());
		super.save.majEtat(Etat.FATIGUE, this.fatigue.getValeur());
		super.save.majEtat(Etat.ENERGIE, this.energie.getValeur());
		super.save.majEtat(Etat.MORAL, this.moral.getValeur());
		super.save.majEtat(Etat.SANTE, this.sante.getValeur());
		super.save.majEtat(Etat.TOILETTES, this.toilettes.getValeur());
		super.save.majEtat(Etat.MAISON, this.maisonEC);
		super.save.majEtat(Etat.DORMIR, this.dormirEC);
		super.save.majEtat(Etat.DEPLACEMENT, this.deplacementEC);
		super.sauvegarde();
	}
	@Override
	public void run()
	{
		super.run();
	}
	@Override
	public void majBesoin(String besoin, int valeur)
	{
		System.out.println("[Sous classe] Satisfait le besoin " + besoin + " avec la valeur " + valeur);
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
	@Override
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
	@Override
	public int getEtatInt(String etat)
	{
		int ret = -1;
		switch(etat)
		{
			case "vie":
				ret = super.vie.getValeur();
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
	@Override
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
}
