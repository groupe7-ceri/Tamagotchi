/* Fichier Droide.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Droide */

public class Droide extends Robot
{
	private String[] actions = {"Dormir", "Manger", "Ronronner", "Faire son curieux"};
	public Droide(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Droide");
	}
	@Override
	public String[] getActions()
	{
		return this.actions;
	}
}
