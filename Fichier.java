/* Fichier Fichier.java
Crée le dimanche 12 avril 2015
MAJ : mercredi 15 avril 2015
Description : Gère l'interaction avec le fichier XML présent sur le disque */

import java.io.File;
import java.io.IOException;
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
import org.xml.sax.SAXException;


public class Fichier
{
	private File fichier;
	private String dossier = "saves/";
	private String[] data;		// est l'image du fichier XML au chargement et sert de tampon
	private String[] etats;
	private int nb = 14;
	private Element[] elements;
	private Element racine;
	private Element tama;
	private String nomFichier;
	public Fichier(String nomTama, String typeTama)
	{
		this.fichier = null;
		this.data = new String[this.nb];
		this.elements = new Element[this.nb];
		this.etats = new String[] {"nom", "type", "vie", "faim", "fatigue", "energie", "moral", "sante", "toilettes", "hygiene", "maison", "dormir", "deplacement", "lastMAJ"};
		// On construit le nom du fichier
		this.nomFichier = this.dossier + nomTama + "-" + typeTama + ".xml";
		if(this.isFileExisted(this.nomFichier))
		{
			System.out.println("Erreur, le fichier existe déjà !");
			System.exit(-1);
		}
		this.ecrireXML(true, nomTama, typeTama);
	}
	public Fichier(String nomFichier)
	{
		if(!this.isFileExisted(this.dossier + nomFichier)) // Mettre une sécurité si le fichier n'existe pas, on stoppe le chargement
		{
			System.out.println("Erreur, le fichier " + nomFichier + " n'existe pas !");
			System.exit(-1);
		}
		this.data = new String[this.nb];
		this.elements = new Element[this.nb];
		this.etats = new String[] {"nom", "type", "vie", "faim", "fatigue", "energie", "moral", "sante", "toilettes", "hygiene", "maison", "dormir", "deplacement", "lastMAJ"};
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
    		DocumentBuilder builder = factory.newDocumentBuilder();
    		this.nomFichier = this.dossier + nomFichier;
    		this.fichier = new File(this.nomFichier);
			Document document= builder.parse(this.nomFichier);
			this.racine = document.getDocumentElement();
			NodeList racineNoeuds = this.racine.getChildNodes();
			int nbRacineNoeuds = racineNoeuds.getLength();
			
			for (int i = 0; i < nbRacineNoeuds; i++)
			{
				if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE)
				{
					this.tama = (Element) racineNoeuds.item(i);
					for(int j = 0; j < this.nb; j++)
					{
						this.elements[j] = (Element) this.tama.getElementsByTagName(this.etats[j]).item(0);
						this.data[j] = this.elements[j].getTextContent();
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void ecrireXML(boolean nouveau, String nomTama, String typeTama)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			this.racine = document.createElement("application");
			document.appendChild(racine);
			this.tama = document.createElement("tamagotchi");
			racine.appendChild(this.tama);
			if(nouveau)		// ajout des infos dans un nouveau fichier
			{
				long timestamp = System.currentTimeMillis() / 1000;
				String[] defaut = new String[] {nomTama, typeTama, "100", "0", "0", "0", "90", "100", "0", "90", "false", "false", "false", Long.toString(timestamp)};
				this.racine = document.createElement("application");
				for(int i = 0; i < this.nb; i++)
				{
					this.elements[i] = document.createElement(this.etats[i]);
					this.elements[i].appendChild(document.createTextNode(defaut[i]));
					this.tama.appendChild(this.elements[i]);
					this.data[i] = defaut[i];
				}	
			}
			else 			// modification des infos dans le fichier (donc sauvegarde)
			{
				for(int i = 0; i < this.nb; i++)
				{
					this.elements[i] = document.createElement(this.etats[i]);
					this.elements[i].appendChild(document.createTextNode(this.data[i]));
					this.tama.appendChild(this.elements[i]);
				}
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			if(nouveau)
				this.fichier = new File(nomFichier);
			StreamResult sortie = new StreamResult(this.fichier);
			//prologue
			transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			//formatage
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(source, sortie);	// sortie vers le fichier
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private boolean isFileExisted(String nom)
	{
		File f = new File(nom);
		if(f.exists() && f.isFile())
			return true;
		else
			return false;
	}
	
	public boolean majEtat(Etat etat, int valeur)
	{
		int pos = etat.ordinal();
		if((pos >= 2) && (pos <= 9))	// cases du tableau correspondant aux états
		{
			this.data[pos] = Integer.toString(valeur);
			return true;
		}
		else
			return false;
	}
	public boolean majEtat(Etat etat, boolean valeur)
	{
		int pos = etat.ordinal();
		if((pos >= 10) && (pos <= 12))	// cases du tableau correspondant aux états
		{
			if(valeur)
				this.data[pos] = "true";
			else
				this.data[pos] = "false";
			return true;
		}
		else
			return false;
	}
	
	// temporaire
	public void afficheElement(int pos)
	{
		System.out.println(pos + ") Etat " + this.etats[pos] + " : " + this.data[pos]);
	}
	public void sauvegarde()
	{
		for(int i = 0; i < this.nb; i++)
		{
			this.tama.setAttribute(this.etats[i], this.data[i]);
		}
		this.ecrireXML(false, null, null);
		
	}
	// Getteurs
	public String getNom()
	{
		return this.nomFichier;
	}
	public int getEtatInt(int pos)
	{
		if((pos >= 2) && (pos <= 9))
			return Integer.parseInt(this.data[pos]);
		else
			return -1;
	}
	public boolean getEtatBool(int pos)
	{
		boolean res = false;
		if((pos >= 10) && (pos <= 12))
		{
			// conversion en bool
			if(this.data[pos].equals("true"))
				res = true;
			else
				res = false;
		}
		return res;
	}
	public int getLastMaj()
	{
		return Integer.parseInt(this.data[13]);
	}
	public String getNomTama()
	{
		return this.data[0];
	}
	public String getTypeTama()
	{
		return this.data[1];
	}
	/*public static void main(String[] args)
	{
		System.out.println("Test Fichier");
		Fichier test = new Fichier("Robert-Robot.xml");
		int etat = test.getLastMaj();
		System.out.println("Etat : " + etat);
	}//*/
	// Setteurs
}
