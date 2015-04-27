/* Fichier Chat.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Chat */

import java.util.Arrays;

public final class Chat extends Vivant
{
	private String[] actions = {"Ronronner", "Faire chier le monde", "Faire son curieux"};
	private String cri = "Miaou";
	public Chat(Fichier file)
	{
		super(file);
	}
	/*public void sauvegarde()
	{
		super.sauvegarde();
	}//*/
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
		super.run();
	}
}
