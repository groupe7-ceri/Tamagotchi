#!/bin/sh

echo "Liste toutes les classes Java"
> sortie.txt
liste=`ls -l *.java | sed "s/ \+/ /g" | cut -d" " -f9`
i=0
for ligne in $liste
do
	fichier=`echo $ligne | cut -d"." -f1`
	echo $fichier >> sortie.txt
	i=$(($i + 1))
done
echo "Il y a $i classes" >> sortie.txt
