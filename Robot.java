/* Fichier Robot.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description :  */

public abstract class Robot extends Tamagotchi
{
	public Robot(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Robot");
	}
	abstract String[] getActions();
}
