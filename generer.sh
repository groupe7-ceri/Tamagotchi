#!/bin/sh

if [ $# -eq 0 ]
then
	echo "Usage : [classe] [[classe 2] [classe 3] ... ]"
	exit
fi
jour=`date | cut -d"," -f1`

for i in "$@"
do
	nom="$i.java"
	touch $nom
	echo "/* Fichier $nom" > $nom
	echo "Crée le $jour" >> $nom
	echo "MAJ : $jour" >> $nom
	echo "Description : $descr */" >> $nom
	echo "" >> $nom
	echo "public class $i" >> $nom
	echo "{" >> $nom
	echo "\tpublic $i()" >> $nom
	echo "\t{" >> $nom
	echo "\t}" >> $nom
	echo "}" >> $nom

	echo "Le fichier généré est $nom dans le répertoire courant"
done
