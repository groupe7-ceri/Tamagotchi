#!/bin/sh

if [ $# -ne 1 ]
then
	echo "Usage : [nom classe]"
	exit
fi
nom="$1.java"
jour=`date | cut -d"," -f1`


touch $nom
echo "/* Fichier $nom" > $nom
echo "Crée le $jour" >> $nom
echo "MAJ : $jour" >> $nom
echo "Description : Entrez la description ici */" >> $nom
echo "" >> $nom
echo "public class $1" >> $nom
echo "{" >> $nom
echo "\tpublic $1()" >> $nom
echo "\t{" >> $nom
echo "\t}" >> $nom
echo "}" >> $nom

echo "Le fichier généré est $nom dans le répertoire courant"
