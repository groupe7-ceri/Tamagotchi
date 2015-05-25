/* Fichier Image.java
Crée le lundi 25 mai 2015
MAJ : lundi 25 mai 2015
Description :  */

import javax.swing.*;
import java.awt.*;

public class Image extends JFrame
{
	private JPanel panel;
	private ImageIcon icone;
	private JLabel image;
	public Image(String fichier)
	{
		System.out.println("Fichier : " + fichier);
		this.icone = new ImageIcon(fichier);
		this.panel = new JPanel();
		this.image = new JLabel(this.icone);
		this.panel.add(image);
		this.panel.repaint();
	}
	public JPanel changerImage(String image)
	{
		return null;
	}
	public JPanel getPanel()
	{
		return this.panel;
	}
}
