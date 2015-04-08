/* Fichier Programme.java 
Classe principal permettant de gérer le jeu 
MAJ : 2 avril 2015 */


public class Programme
{
	public static void main(String[] args)
	{
		System.out.println("Projet Tamagotchi - Modélisation UML");
		System.out.println("Elodie Boloré - Jérémie Décome - Thibaut Miranda");
		System.out.println("Version : 0.1");
		System.out.println("Démarrage de l'application ...");
		Interface fenetre = new Interface(900, 400);
		fenetre.majEtat("faim", 80);
	}
}
