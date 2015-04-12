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

public class Fichier
{
	private File fichier;
	private String dossier = "saves/";
	private String[] data;
	private int nb = 13;
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
	public Fichier(String nomTama, String typeTama)
	{
		this.fichier = null;
		this.data = new String[this.nb];
		// On construit le nom du fichier
		String nomFichier = this.dossier + nomTama + "-" + typeTama + ".xml";
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
			this.stockageTableau();
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
					this.nom = (Element) this.tama.getElementsByTagName("nom").item(0);					// tab[0]
					this.type = (Element) this.tama.getElementsByTagName("type").item(0);				// tab[1]
					this.vie = (Element) this.tama.getElementsByTagName("vie").item(0);					// tab[2]
					this.faim = (Element) this.tama.getElementsByTagName("faim").item(0);				// tab[3]
					this.fatigue = (Element) this.tama.getElementsByTagName("fatigue").item(0);			// tab[4]
					this.energie = (Element) this.tama.getElementsByTagName("energie").item(0);			// tab[5]
					this.moral = (Element) this.tama.getElementsByTagName("moral").item(0);				// tab[6]
					this.sante = (Element) this.tama.getElementsByTagName("sante").item(0);				// tab[7]
					this.toilettes = (Element) this.tama.getElementsByTagName("toilettes").item(0);		// tab[8]
					this.maison = (Element) this.tama.getElementsByTagName("maison").item(0);			// tab[9]
					this.dormir = (Element) this.tama.getElementsByTagName("dormir").item(0);			// tab[10]
					this.deplacement = (Element) this.tama.getElementsByTagName("deplacement").item(0);	// tab[11]
					this.lastMAJ = (Element) this.tama.getElementsByTagName("lastMAJ").item(0);			// tab[12]
					// Stockage
					this.stockageTableau();
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
		this.data[0] = this.nom.getTextContent();
		this.data[1] = this.type.getTextContent();
		this.data[2] = this.vie.getTextContent();
		this.data[3] = this.faim.getTextContent();
		this.data[4] = this.fatigue.getTextContent();
		this.data[5] = this.energie.getTextContent();
		this.data[6] = this.moral.getTextContent();
		this.data[7] = this.sante.getTextContent();
		this.data[8] = this.toilettes.getTextContent();
		this.data[9] = this.maison.getTextContent();
		this.data[10] = this.dormir.getTextContent();
		this.data[11] = this.deplacement.getTextContent();
		this.data[12] = this.lastMAJ.getTextContent();
	}
	public String getElement(int i)
	{
		return this.data[i];
	}
	public void majEtat(String etat, int valeur)
	{
		System.out.println("Met à jour l'état " + etat + " de valeur " + valeur + " dans le fichier ");
		if(Arrays.asList(this.data).contains(etat))		// la condition ne se réalise pas
		{
			System.out.println("Le tableau contient bien l'état " + etat);
		}
	}
	public static void main(String[] args)
	{
		System.out.println("Essai de la classe Fichier");
		Fichier fichier = new Fichier("Bob-Robot.xml");
		fichier.majEtat("vie", 98);
	}
}
