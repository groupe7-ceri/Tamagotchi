/* Fichier Inerte.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description :  */

public abstract class Inerte extends Tamagotchi
{
	public Inerte(Fichier file)
	{
		super(file, false);
		System.out.println("Tamagochti de type Inerte");
	}
	abstract String[] getActions();
	abstract void effectuerAction(String action);
	@Override
	public String getHumeur()
	{
		return "Je suis inerte !";
	}
	@Override
	public void rafraichissement()
	{
		System.out.println("rafraichissement des données avant initialisation de l'interface");
	}
	@Override
	public void majBesoin(String besoin, int valeur)
	{
		System.out.println("[Sous classe] Satisfait le besoin " + besoin + " avec la valeur " + valeur);
		/*switch(besoin)
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
		}//*/
		this.miseAJour(besoin);
		//this.maj = Long.toInteger(System.currentTimeMillis()) / 1000;
	}
	@Override
	public void majEtat(String etat, boolean valeur)
	{
		/*switch(etat)
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
		}//*/
	}
	@Override
	public int getEtatInt(String etat)
	{
		int ret = -1;
		/*switch(etat)
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
		}//*/
		return ret;
	}
	@Override
	public boolean getEtatBool(String etat)
	{
		boolean ret = false;
		/*switch(etat)
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
		}//*/
		return ret;
	}
}
