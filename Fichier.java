/* Fichier Fichier.java
Crée le dimanche 12 avril 2015
MAJ : dimanche 12 avril 2015
Description : Gère l'interaction avec le fichier XML présent sur le disque */

import java.io.File;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Arrays;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.lang.Enum;

public class Fichier
{
	private File fichier;
	private String dossier = "saves/";
	private String[] data;
	private String[] etats;
	private int nb = 13;
	private Element[] elements;
	private Element racine;
	private Element tama;
	private Element nom;
	private Element type;
	private Element vie;
	private Element faim;
	private Element fatigue;
	private Element energie;
	private Element moral;
	private Element sante;
	private Element toilettes;
	private Element maison;
	private Element dormir;
	private Element deplacement;
	private Element lastMAJ;
	private String nomFichier;
	public Fichier(String nomTama, String typeTama)
	{
		this.fichier = null;
		this.data = new String[this.nb];
		this.elements = new Element[this.nb];
		this.etats = new String[] {"nom", "type", "vie", "faim", "fatigue", "energie", "moral", "sante", "toilettes", "maison", "dormir", "deplacement", "lastMAJ"};
		// On construit le nom du fichier
		this.nomFichier = this.dossier + nomTama + "-" + typeTama + ".xml";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document= builder.newDocument();
			this.racine = document.createElement("application");
			document.appendChild(racine);

			this.tama = document.createElement("tamagotchi");
			racine.appendChild(tama);

			this.nom = document.createElement("nom");
			this.nom.appendChild(document.createTextNode(nomTama));
			
			this.type = document.createElement("type");
			this.type.appendChild(document.createTextNode(typeTama));

			this.vie = document.createElement("vie");
			this.vie.appendChild(document.createTextNode("100"));

			this.faim = document.createElement("faim");
			this.faim.appendChild(document.createTextNode("0"));

			this.fatigue = document.createElement("fatigue");
			this.fatigue.appendChild(document.createTextNode("0"));

			this.energie = document.createElement("energie");
			this.energie.appendChild(document.createTextNode("0"));

			this.moral = document.createElement("moral");
			this.moral.appendChild(document.createTextNode("90"));

			this.sante = document.createElement("sante");
			this.sante.appendChild(document.createTextNode("100"));

			this.toilettes = document.createElement("toilettes");
			this.toilettes.appendChild(document.createTextNode("0"));

			this.maison = document.createElement("maison");
			this.maison.appendChild(document.createTextNode("false"));

			this.dormir = document.createElement("dormir");
			this.dormir.appendChild(document.createTextNode("false"));

			this.deplacement = document.createElement("deplacement");
			this.deplacement.appendChild(document.createTextNode("false"));

			long timestamp = System.currentTimeMillis() / 1000;
			this.lastMAJ = document.createElement("lastMAJ");
			this.lastMAJ.appendChild(document.createTextNode(Long.toString(timestamp)));
				
			this.tama.appendChild(this.nom);
			this.tama.appendChild(this.type);
			this.tama.appendChild(this.vie);
			this.tama.appendChild(this.faim);
			this.tama.appendChild(this.fatigue);
			this.tama.appendChild(this.energie);
			this.tama.appendChild(this.moral);
			this.tama.appendChild(this.sante);
			this.tama.appendChild(this.toilettes);
			this.tama.appendChild(this.maison);
			this.tama.appendChild(this.dormir);
			this.tama.appendChild(this.deplacement);
			this.tama.appendChild(this.lastMAJ);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			this.fichier = new File(nomFichier);
			StreamResult sortie = new StreamResult(this.fichier);
			
			//prologue
			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			
	    		
			//formatage
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			//sortie
			transformer.transform(source, sortie);
			this.elements[0] = this.nom;
			this.elements[1] = this.type;
			this.elements[2] = this.vie;
			this.elements[3] = this.faim;
			this.elements[4] = this.fatigue;
			this.elements[5] = this.energie;
			this.elements[6] = this.moral;
			this.elements[7] = this.sante;
			this.elements[8] = this.toilettes;
			this.elements[9] = this.maison;
			this.elements[10] = this.dormir;
			this.elements[11] = this.deplacement;
			this.elements[12] = this.lastMAJ;
		}
		catch(Exception e)
		{
    		e.printStackTrace();
		}
	}
	public Fichier(String nomFichier)
	{
		System.out.println("Ouvre le fichier " + dossier + nomFichier);
		// Mettre une sécurité si le fichier n'existe pas
		this.data = new String[this.nb];
		this.elements = new Element[this.nb];
		this.etats = new String[] {"nom", "type", "vie", "faim", "fatigue", "energie", "moral", "sante", "toilettes", "maison", "dormir", "deplacement", "lastMAJ"};
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		this.fichier = new File(this.dossier + nomFichier);
			Document document= builder.parse(this.dossier + nomFichier);
			this.racine = document.getDocumentElement();
			NodeList racineNoeuds = racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();
			
			for (int i = 0; i < nbRacineNoeuds; i++)
			{
				if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					this.tama = (Element) racineNoeuds.item(i);
					for(int j = 0; j < this.nb; j++)
					{
						this.elements[j] = (Element) this.tama.getElementsByTagName(this.etats[j]).item(0);
						//System.out.println("Etat XML n°" + j + " => " + this.elements[j].getTextContent());
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void stockageTableau()
	{
		
	}
	public String getElement(int i)
	{
		return this.elements[i].getTextContent();
	}
	public boolean majEtat(Etat etat, int valeur)
	{
		int pos = etat.ordinal();
		if((pos >= 2) && (pos <= 8))	// cases du tableau correspondant aux états
		{
			if(this.elements[pos].hasAttribute(this.etats[pos]))
			{
				this.elements[pos].setAttribute(this.etats[pos], Integer.toString(valeur));
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	public boolean majEtat(Etat etat, boolean valeur)
	{
		int pos = etat.ordinal();
		String val;
		if((pos >= 9) && (pos <= 11))	// cases du tableau correspondant aux états
		{
			if(valeur)
				val = "true";
			else
				val = "false";
			this.elements[pos].setAttribute(this.etats[pos], val);
			return true;
		}
		else
			return false;
	}
	public String getNom()
	{
		return this.nomFichier;
	}
	public void afficheElement(int pos)
	{
		System.out.println(pos + ") Etat " + this.etats[pos] + " : " + this.getElement(pos));
	}
	public void sauvegarde()
	{
		System.out.println("Ecriture des informations dans le fichier, appelé à la fermeture de l'application");
		/*for(int i = 0; i < this.nb; i++)
		{
			System.out.println("Etat " + this.etats[i] + " : " + this.getElement(i));
		}//*/
	}
	public static void main(String[] args)
	{
		Etat etat;
		System.out.println("Essai de la classe Fichier");
		Fichier fichier = new Fichier("Robert-Animal.xml");
		System.out.println("Juste après l'ouverture");
		for(int i = 0; i < 13; i++)
			fichier.afficheElement(i);
		System.out.println("--------------------");
		if(fichier.majEtat(Etat.VIE, 98))
		{
			System.out.println("Modification du point de vie");
			for(int i = 0; i < 13; i++)
				fichier.afficheElement(i);
		}
		System.out.println("--------------------");
		if(fichier.majEtat(Etat.DORMIR, true))
		{
			System.out.println("Modification de dormir");
			for(int i = 0; i < 13; i++)
				fichier.afficheElement(i);
		}
		fichier.sauvegarde();
	}
}
