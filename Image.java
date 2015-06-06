/* Fichier Image.java
Crée le lundi 25 mai 2015
MAJ : mardi 2 juin 2015
Description :  */

import javax.swing.*;
import java.awt.*;

public class Image extends JFrame
{
	private JPanel panel;
	private ImageIcon icone;
	private JLabel image;
	private String fichier;
	public Image(String fichier)
	{
		this.fichier = fichier;
		this.icone = new ImageIcon(this.fichier);
		this.panel = new JPanel();
		this.image = new JLabel(this.icone);
		this.panel.add(image);
		this.panel.repaint();
	}
	public JPanel getPanel()
	{
		//System.out.println("[getPanel - Image] panel pour l'image " + this.fichier + " => " + this.panel);
		return this.panel;
	}
}
