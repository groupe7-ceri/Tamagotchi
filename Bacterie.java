/* Fichier Bacterie.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Bactérie */

public class Bacterie extends Animal
{
	private String[] actions = {"Dormir", "Manger", "Ronronner", "Faire son curieux"};
	public Bacterie(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Bactérie");
	}
	@Override
	public String[] getActions()
	{
		return this.actions;
	}
}
