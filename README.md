# Projet BattleShip ISEN JEE

Vivien CHARLET
Chahnez AZZAZ

## Lancement du projet

  mvn clean install
  mvn jetty:run

## Informations

Le projet permet de jouer à la bataille navale contre une IA simple. Il est disponnible en deux versions :

* La version servlet API qui communique avec une page en javascript grâce à du Json. 
* La version JSP pur

Pour le Json nous utilisons les librairies de base inclues dans javax.
Les deux versions utilisent la même base pour fonctionner (les deux classes Grid et Ship). 
L'ensemble des classes et servlet sont intégralement testés. (100% coverage selon IntelliJ).

Le projet a été testé sur Netbeans et IntelliJ.

