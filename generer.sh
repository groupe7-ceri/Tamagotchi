#!/bin/sh

if [ $# -ne 1 ]
then
	echo "Usage : [nom classe]"
	exit
fi
nom="$1.java"
jour=`date | cut -d"," -f1`

echo "Génération du fichier de la classe $1"

touch $nom
echo "/* Fichier $nom" > $nom
echo "Description du fichier" >> $nom
echo "Crée le $jour" >> $nom
echo "MAJ : $jour */" >> $nom
echo "" >> $nom
echo "public class $1" >> $nom
echo "{" >> $nom
echo "\tpublic $1()" >> $nom
echo "\t{" >> $nom
echo "\t}" >> $nom
echo "}" >> $nom

echo "Le fichier généré est $nom dans le répertoire courant"