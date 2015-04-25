/* Fichier Animal.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe abstraite des types animal */

public abstract class Animal extends Tamagotchi
{
	public Animal(Fichier file)
	{
		super(file);
		System.out.println("Le tamagotchi est de type Animal");
	}
	abstract String[] getActions();
}
