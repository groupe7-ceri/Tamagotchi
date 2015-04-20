/* Fichier Besoin.java
Crée le lundi 20 avril 2015
MAJ : lundi 20 avril 2015
Description : Représente un besoin du tamagotchi (0 - 100 %) */

public class Besoin
{
	private String type;
	private int valeur;
	private int pas;
	private boolean sens; // montée : true - descente : false
	public Besoin(String type, int valeur, int pas, boolean sens)
	{
		this.type = type;
		this.valeur = valeur;
		this.pas = pas;
		this.sens = sens;
	}
	public int satisfaire(int newVal)
	{
		if((this.valeur > 0) && (this.valeur < 100))
		{
			if(sens) // le besoin tend naturellement vers 100 %, donc il faut en enlever pour le satisfaire
				this.valeur -= newVal;
			else	// le besoin tend naturellement vers 0 %, donc il faut en rajouter pour le satisfaire
				this.valeur += newVal;
			return this.valeur;
		}
		else
			return -1;
	}
	public void vie()
	{
		if((this.valeur > 0) && (this.valeur < 100))
		{
			if(sens) // le besoin tend naturellement vers 100 %
				this.valeur += this.pas;
			else	// le besoin tend naturellement vers 0 %
				this.valeur -= this.pas;
		}
	}
	public int getValeur()
	{
		return this.valeur;
	}
	public String getType()
	{
		return this.type;
	}
	/*public static void main(String[] arg)
	{
		System.out.println("Essai de la classe Besoin");
		Besoin manger = new Besoin("Nourriture", 30, 5, true);
		Besoin moral = new Besoin("Moral", 90, 5, false);
		for(int i = 0; i < 5; i++)
		{
			try
			{
				Thread.sleep(1000);
				manger.vie();
				moral.vie();
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(manger.getType() + " est à " + manger.getValeur() + " point(s)");
		System.out.println(moral.getType() + " est à " + moral.getValeur() + " point(s)");
		manger.satisfaire(10);
		moral.satisfaire(10);
		System.out.println(manger.getType() + " est à " + manger.getValeur() + " point(s)");
		System.out.println(moral.getType() + " est à " + moral.getValeur() + " point(s)");
	}//*/
}
