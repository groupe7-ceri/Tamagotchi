/* Fichier Chat.java
Crée le samedi 25 avril 2015
MAJ : samedi 25 avril 2015
Description : Classe finale type de Tamagotchi - Type Chat */

public class Chat extends Animal
{
	private String[] actions = {"Ronronner", "Faire chier le monde", "Faire son curieux"};
	public Chat(Fichier file)
	{
		super(file);
		System.out.println("Tamagochti de type Chat");
	}
	public String[] getActions()
	{
		return this.actions;
	}
}
