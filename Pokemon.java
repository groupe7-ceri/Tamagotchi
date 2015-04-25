/* Fichier Pokemon.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Pokémon */

public class Pokemon extends Animal
{
	private String[] actions = {"Dormir", "Manger", "Ronronner", "Faire son curieux"};
	public Pokemon(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Pokémon");
	}
	@Override
	public String[] getActions()
	{
		return this.actions;
	}
}
