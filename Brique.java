/* Fichier Brique.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Brique */

public class Brique extends Inerte
{
	private String[] actions = {"Dormir", "Manger", "Ronronner", "Faire son curieux"};
	public Brique(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Brique");
	}
	@Override
	public String[] getActions()
	{
		return this.actions;
	}
}
