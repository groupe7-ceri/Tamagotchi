/* Fichier Droide.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Droide */

import java.util.Arrays;

public final class Droide extends Robot
{
	private String[] actions = {""};
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
	@Override
	public void effectuerAction(String action)
	{
		if(Arrays.asList(actions).contains(action))
		{
			System.out.println("Effectue l'action " + action);
		}
	}
	@Override
	public void run()
	{
		
	}
}
