/* Fichier Inerte.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description :  */

public abstract class Inerte extends Tamagotchi
{
	public Inerte(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Inerte");
	}
	abstract String[] getActions();
}
