/* Fichier EtatTama.java
Crée le lundi 20 avril 2015
MAJ : lundi 20 avril 2015
Description : Représente un état du tamagotchi (vie, fatigue, énergie, santé) */

public class EtatTama
{
	private String type;
	private int valeur;
	private int pas;
	private boolean variation;	// permet de définir qu'un état peut fluctuer entre 0 et 100 ou non, tel que la vie
	public EtatTama(String type, int valeur, int pas)
	{
		this.type = type;
		this.valeur = valeur;
		this.pas = pas;
		this.variation = true;
		if(this.type.equals("vie"))
			this.variation = false;
	}
	public void vie()
	{
		if((this.valeur > 0) && (this.valeur < 101))
		{
			if(this.variation)	// l'état peut fluctuer dans le temps, donc naturellement, il tends vers 100 %
				this.valeur += this.pas;
			else				// l'état ne peut pas fluctuer dans le temps, donc naturellement il tends vers 0 %
				this.valeur -= this.pas;
		}
	}
	public int satisfaire(int newVal)
	{
		if((this.valeur > 0) && (this.valeur < 100))
		{
			if(this.variation) // le besoin tend naturellement vers 100 %, donc il faut en enlever pour le satisfaire
				this.valeur -= newVal;
			return this.valeur;
		}
		else
			return -1;
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
		System.out.println("Essai de la classe EtatTama");
		EtatTama life = new EtatTama("vie", 100, 1);
		EtatTama fatigue = new EtatTama("fatigue", 50, 2);
		for(int i = 0; i < 5; i++)
		{
			try
			{
				Thread.sleep(1000);
				life.vie();
				fatigue.vie();
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(life.getType() + " est à " + life.getValeur() + " point(s)");
		System.out.println(fatigue.getType() + " est à " + fatigue.getValeur() + " point(s)");
		System.out.println("Res : " + life.satisfaire(10));
		fatigue.satisfaire(10);
		System.out.println(life.getType() + " est à " + life.getValeur() + " point(s)");
		System.out.println(fatigue.getType() + " est à " + fatigue.getValeur() + " point(s)");
	}//*/
}
