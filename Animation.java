/* Fichier Animation.java
Description du fichier
Crée le mercredi 8 avril 2015
MAJ : mercredi 8 avril 2015 

Description : Gère l'ajout d'une image dans un JPanel (l'image est incluse dans le JPanel) */

import javax.swing.*;
import java.awt.*;

public class Animation extends JFrame
{
	private JPanel panel;
	private ImageIcon icone;
	private JLabel image;
	public Animation(String fichier, int largeur, int longueur)
	{
		this.icone = new ImageIcon(fichier);
		this.panel = new JPanel();
		this.image = new JLabel(this.icone);
		this.image.setSize(largeur, longueur);
		this.panel.add(image);
		this.panel.repaint();
	}
	public JPanel changerImage(String image)
	{
		
	}
	public JPanel getPanel()
	{
		return this.panel;
	}
}
