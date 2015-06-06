/* Fichier Vivant.java
Crée le samedi 25 avril 2015
MAJ : lundi 18 mai 2015
Description : Classe abstraite des types  */

public abstract class Vivant extends Tamagotchi
{
	private Fichier fichier;
	private Besoin nourriture, hygiene, toilettes;
	private EtatTama fatigue, energie, sante, moral;
	protected boolean maisonEC, dormirEC, deplacementEC;	// indique l'état actuel du tamagotchi - en train de dormir ou à la maison, ... - EC pour En Cours
	private boolean deplacement;
	private int delai1;			// influence sur la vie

	// A implémenter dans les autres types (Robot et Inerte)
	private int intervalle;		// intervalle en seconde indiquant un "top horloge"
	private long nbTop; 		// nbre de top horloge a rattraper
	public Vivant(Fichier file)
	{
		super(file, true);
		this.fichier = file;
		// Besoins
		this.nourriture = new Besoin("nourriture", this.fichier.getEtatInt(3), 1, true);
		this.toilettes = new Besoin("toilettes", this.fichier.getEtatInt(8), 1, true);
		this.hygiene = new Besoin("hygiene", this.fichier.getEtatInt(9), 1, false);

		// Etats 
		this.fatigue = new EtatTama("fatigue", this.fichier.getEtatInt(4), 2, true);
		this.energie = new EtatTama("energie", this.fichier.getEtatInt(5), 2, false);		// fatigue et énergie va déclencher la nécessité de dormir ou non
		this.moral = new EtatTama("moral", this.fichier.getEtatInt(6), 1, false);
		this.sante = new EtatTama("sante", this.fichier.getEtatInt(7), 2, false);			// la santé n'influe pas sur l'interface mais a une influence sur l'humeur du tama

		// a virer
		//this.dormir = new Besoin("dormir", this.fatigue.getValeur(), 1, true);
		
		this.maisonEC = this.fichier.getEtatBool(10);
		this.dormirEC = this.fichier.getEtatBool(11);
		this.deplacementEC = this.fichier.getEtatBool(12);
		this.intervalle = 1;
	}
	abstract String[] getActions();
	abstract void effectuerAction(String action);

	public void dormir()
	{
		if(this.maisonEC && !this.dormirEC) // il est a la maison, donc il peux aller dormir et si il ne dort pas déjà
		{
			if((this.fatigue.getValeur() < 40) && (this.energie.getValeur() < 30))
			{
				this.dormirEC = true;
			}
		}
	}
	public void reveiller()
	{
		if(this.dormirEC)
			this.dormirEC = false;
	}
	protected void rentrer()
	{
		if(!this.maisonEC)
		{
			this.maisonEC = true;
		}
		super.miseAJour("maisonEC");
	}
	protected void sortir()
	{
		if(this.maisonEC)
			this.maisonEC = false;
		super.miseAJour("maisonEC");
	}
	public void sauvegarde()
	{
		super.save.majEtat(Etat.FAIM, this.nourriture.getValeur());
		super.save.majEtat(Etat.FATIGUE, this.fatigue.getValeur());
		super.save.majEtat(Etat.ENERGIE, this.energie.getValeur());
		super.save.majEtat(Etat.MORAL, this.moral.getValeur());
		super.save.majEtat(Etat.SANTE, this.sante.getValeur());
		super.save.majEtat(Etat.TOILETTES, this.toilettes.getValeur());
		super.save.majEtat(Etat.HYGIENE, this.hygiene.getValeur());
		super.save.majEtat(Etat.MAISON, this.maisonEC);
		super.save.majEtat(Etat.DORMIR, this.dormirEC);
		super.save.majEtat(Etat.DEPLACEMENT, this.deplacementEC);
		super.sauvegarde();
	}
	@Override
	public String getHumeur()
	{
		String humeur;
		int moral = this.moral.getValeur();
		if((moral >= 80) && (moral <= 100))
			humeur = "Très joyeux !";
		else if((moral >= 60) && (moral <= 79))
			humeur = "Ca va";
		else if((moral >= 40) && (moral <= 59))
			humeur = "Ca va à peu près bien";
		else if((moral >= 20) && (moral <= 39))
			humeur = "Ouais bof ...";
		else if((moral >= 5) && (moral <= 19))
			humeur = "Mal";
		else
			humeur = "Au plus bas ... :( ";
		//System.out.println("Humeur de la valeur moral " + moral + " = " + humeur);		// tmp
		return humeur;
	}
	// Méthode rafraichissement, appelée juste avant que le moteur ne soit démarré
	@Override
	public void rafraichissement()
	{
		long now = System.currentTimeMillis() / 1000;
		int derniere = this.fichier.getLastMaj();
		long delta = now - derniere;
		if(delta > 0)
			this.nbTop = delta / this.intervalle;	// calcul du nombre de top à rattraper
		else
			this.nbTop = 0;
	}
	@Override
	public void run()
	{
		/* La méthode run() est spécifique à chaque type de tamagotchi. En effet, chaque type de tamagotchi
		ne dispose pas des memes attributs qui n'évoluent pas de la meme manière. 
		C'est pour cette raison que le moteur est "réécrit" pour chaque genre de tamagotchi. */
		int cat1 = 0, cat2 = 0, cat3 = 0, cat4 = 0;
		int reveil = 0;
		/*
			1 top horloge toutes les 300 secondes
			Catégories :
				1) se met à jour tous les 300 tops horloge (modification très lente) 
					- vie du tamagotchi (estimé à 25 heures)
				2) se met à jour tous les 150 tops horloge (modification lente) - 12,5 heures
				3) se met à jour tous les 100 tops horloge (modification normale) - 8 heures
				4) se met à jour tous les 50 tops horloge (modification rapide) - 4 heures
					- nourriture
		*/
		// Calcul de l'influence

		while(true)
		{
			try
			{
				if(super.vie.getValeur() > 0)
				{
					Thread.sleep(this.intervalle * 1000);	// ceci est un top horloge (par défaut 0,1 sec pour dev sinon 300 secondes)
					if((this.fatigue.getValeur() > 90) && (this.sante.getValeur() < 10))	// fatigue et santé influe sur la vie
						this.delai1 = 200;
					else
						this.delai1 = 300;
					if(this.nbTop > 0)
					{
						//System.out.println("Nombre de top : " + this.nbTop);
						cat1 += this.nbTop;
						cat2 += this.nbTop;
						cat3 += this.nbTop;
						cat4 += this.nbTop;	
					}
					else
					{
						cat1++;
						cat2++;
						cat3++;
						cat4++;
					}
					if(cat1 >= delai1)
					{
						super.run();
						//System.out.println("MAJ cat1");
						cat1 = 0;
					}
					if(cat2 >= 150)
					{
						this.nourriture.vie();
						this.energie.vie();
						this.hygiene.vie();
						super.miseAJour("nourriture");
						super.miseAJour("energie");
						super.miseAJour("hygiene");
						//System.out.println("MAJ cat2");
						cat2 = 0;
					}
					if(cat3 >= 100)
					{
						//this.dormir.vie();
						this.sante.vie();
						this.fatigue.vie();
						//super.miseAJour("dormir");
						super.miseAJour("sante");
						super.miseAJour("fatigue");
						//System.out.println("MAJ cat3");
						cat3 = 0;
					}
					if(cat4 >= 50)
					{
						this.moral.vie();
						this.toilettes.vie();
						super.miseAJour("moral");
						super.miseAJour("toilettes");
						//System.out.println("MAJ cat4");
						cat4 = 0;
					}
					// Controle des états et besoin
					if(this.dormirEC)
						reveil++;
					else
						reveil = 0;
					//System.out.println("fatigue => dormir : " + this.dormirEC + " - réveil : " + reveil);
					if((this.dormirEC) && (reveil <= 4))
					{
						//System.out.println("Le tamagotchi dort dormir - energie : " + this.energie.getValeur() + " - fatigue : " + this.fatigue.getValeur());
						if((this.energie.getValeur() < 40) || (this.fatigue.getValeur()  > 50))
						{
							this.energie.satisfaire(10);
							this.fatigue.satisfaire(10);
						}
						else
						{
							this.dormirEC = false;
							reveil = 0;
						}
					}
					else
					{
						this.dormirEC = false;
					}
					//System.out.println("WC : " + this.toilettes.getValeur() +  " - Hygiène : " + this.hygiene.getValeur());
					if((this.toilettes.getValeur() >= 95) || (this.hygiene.getValeur() <= 2))	// l'hygiène a une influence sur la santé du tama
					{
						this.sante.vie();
					}
					else
						this.sante.satisfaire(3); // l'hygiene et les toilettes sont satisfait donc n'ont plus d'influence sur la santé
					// si certains états ou besoins arrivent à 100 % ou 0 %, la vie perd 3 points de vie
					if((this.nourriture.getValeur() >= 100) || (this.toilettes.getValeur() >= 100) || (this.fatigue.getValeur() >= 100) || (this.sante.getValeur() <= 0))
					{
						//System.out.println("Nourriture : " + this.nourriture.getValeur() + " - WC : " + this.toilettes.getValeur() + " - Fatigue : " + this.fatigue.getValeur() + " - Santé : " + this.sante.getValeur());
						for(int i = 0; i < 3; i++)
						{
							super.run();
						}
					}
					if(this.nbTop != 0) // RAZ du delta a rajouter
						this.nbTop = 0;
				}
				else
				{
					//System.out.println("Le tamagotchi est mort ! Il n'a plus de vie !");
					super.miseAJour("vie");
				}
				
			}
			catch (InterruptedException ignore)
			{ }
		}
	}
	@Override
	public void majBesoin(String besoin, int valeur)
	{
		//System.out.println("[Sous classe] Satisfait le besoin " + besoin + " avec la valeur " + valeur);
		switch(besoin)
		{
			case "nourriture":
				this.nourriture.satisfaire(valeur);
				break;
			/*case "dormir":
				this.dormir.satisfaire(valeur);
				break;//*/
			case "moral":
				this.moral.satisfaire(valeur);
				break;
			case "toilettes":
				this.toilettes.satisfaire(valeur);
				break;
			case "hygiene":
				/*int ret = */this.hygiene.satisfaire(valeur);
				//System.out.println("Valeur de ret pour hygiene : " + ret);
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
		//System.out.println("[Classe Vivant] Met à jour l'état " + etat + " avec " + valeur);
		switch(etat)
		{
			case "maison":
				this.maisonEC = valeur;
				break;
			case "dormir":
				this.dormirEC = valeur;
				//System.out.println("Valeur pour " + etat + ": " + this.dormirEC);
				break;
			case "deplacement":
				this.deplacementEC = valeur;
				break;
			default:
				//System.out.println("Défaut pour l'état " + etat);
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
				ret = this.energie.getValeur();
				break;
			case "sante":
				ret = this.sante.getValeur();
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
		//System.out.println("Lit l'état " + etat);
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
		//System.out.println("Valeur de " + etat + " => " + ret);
		return ret;
	}
}
