/* Fichier SceneGraphique.java
Crée le lundi 25 mai 2015
MAJ : mardi 2 juin 2015
Description : Permet de gérer l'affichage de l'animation et du son */

import javax.swing.*;
import java.awt.*;

public class SceneGraphique
{
	private JPanel panel;
	private Image[] tabImage = {};
	private Son[] tabSon = {};
	private Son cri;
	private int nbreSprite;
	public SceneGraphique(String type)
	{
		this.nbreSprite = 4;
		String[] sprite = new String[this.nbreSprite];
		this.tabImage = new Image[this.nbreSprite];
		this.tabSon = new Son[this.nbreSprite];
		switch(type)
		{
			case "Chat":
				sprite[0] = "images/chat-normal.png";	// 0 : normal
				sprite[1] = "images/chat-dormir.png";	// 1 : dors
				sprite[2] = "images/chat-manger.png";	// 2 : mange
				sprite[3] = "images/chat-malade.png";	// 3 : malade
				this.cri = new Son("sons/miaou.mp3");
				break;
			case "Droide":
				break;
			default:
				break;
		}
		//System.out.println("Scene ok type : " + type);
		// Création des sprites
		for(int i = 0; i < this.nbreSprite; i++)
		{
			this.tabImage[i] = new Image(sprite[i]);
		}
	}
	public void selectEtat(String etat)
	{
		int select = 0;
		switch(etat)
		{
			case "normal":
				select = 0;
				break;
			case "dormir":
				select = 1;
				break;
			case "manger":
				select = 2;
				break;
			case "malade":
				select = 3;
				break;
			default:
				break;
		}
		//System.out.println("Select etat : " + select + " etat : " + etat);
		this.panel = this.tabImage[select].getPanel();
		this.panel.revalidate();
		this.panel.repaint();
		//System.out.println("Panel sélectionné pour l'état " + etat + " : " + this.panel);
	}
	// Retourne le contenant (utilisé pour mettre à jour l'image)
	public JPanel getPanel()
	{
		return this.panel;
	}
	public void jouerCri()
	{
		// non implémenté car pas de temps
	}
	public static void main(String[] arg)
	{
		System.out.println("Test de la classe SceneGraphique");
		SceneGraphique image = new SceneGraphique("Chat");
		image.selectEtat("normal");
		//System.out.println("Ret de image " + image.getPanel());
		image.selectEtat("dormir");
	} //*/
}
