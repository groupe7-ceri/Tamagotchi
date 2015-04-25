/* Fichier Chien.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Chien */

public class Chien extends Animal
{
	private String[] actions = {"Dormir", "Manger", "Ronronner", "Faire son curieux"};
	public Chien(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Chien");
	}
	@Override
	public String[] getActions()
	{
		return this.actions;
	}
}
