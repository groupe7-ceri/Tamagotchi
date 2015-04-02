/* Fichier Programme.java 
Classe principal permettant de gérer le jeu 
MAJ : 2 avril 2015 */

import javax.swing.JFrame;

public class Programme
{
	public static void main(String[] args)
	{
		System.out.println("Projet Tamagotchi - Modélisation UML");
		System.out.println("Elodie Boloré - Jérémie Décome - Thibaut Miranda");
		System.out.println("Version : 0.1");
		System.out.println("Démarrage de l'application ...");
		JFrame fenetre = new JFrame();
		fenetre.setVisible(true);	// obligatoire pour afficher la fenetre
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // arrete le programme quand la fenetre est fermée
	}
}