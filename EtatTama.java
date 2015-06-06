/* Fichier EtatTama.java
Crée le lundi 20 avril 2015
MAJ : mardi 28 avril 2015
Description : Représente un état du tamagotchi (vie, fatigue, énergie, santé) */

public class EtatTama
{
	private String type;
	private int valeur;
	private int pas;
	private boolean variation;	// permet de définir qu'un état peut fluctuer entre 0 et 100 ou non, tel que la vie
	private boolean sens; // montée : true - descente : false
	public EtatTama(String type, int valeur, int pas, boolean sens)
	{
		this.type = type;
		this.valeur = valeur;
		this.pas = pas;
		this.variation = true;
		this.sens = sens;
		//System.out.println("Etat " + this.type + " instancié : " + this.valeur + " sens : " + this.sens);
		if(this.type.equals("vie"))
			this.variation = false;
	}
	public void vie()
	{
		/*if(sens)
			System.out.println("L'état " + this.type + " augmente " + this.pas + " points");
		else
			System.out.println("L'état " + this.type + " perds " + this.pas + " points");// - tmp */
		if(this.sens) 	// l'état tend vers 100 %
			this.valeur += this.pas;
		else			// l'état tend naturellement vers 0 %
			this.valeur -= this.pas;

		if(this.valeur < 0)
			this.valeur = 0;
		if(this.valeur > 100)
			this.valeur = 100;
	}
	public int satisfaire(int newVal)
	{
		if((this.valeur >= 0) && (this.valeur <= 100))
		{
			//System.out.println("[EtatTama - 2] Satisfaction de l'état " + this.type + " - valeur " + newVal + " " + this.sens);	// tmp
			if(this.sens) // l'état tend naturellement vers 100 %, donc il faut en enlever pour le satisfaire
				this.valeur -= newVal;
			else			// l'état tend naturellement vers 0 %, donc il faut en rajouter pour le satisfaire
				this.valeur += newVal;
			//System.out.println("1 Nouvelle valeur de " + this.type + " = " + this.valeur); // tmp
			if(this.valeur < 0)
				this.valeur = 0;
			if(this.valeur > 100)
				this.valeur = 100;
			//System.out.println("2 Nouvelle valeur de " + this.type + " = " + this.valeur); // tmp
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
