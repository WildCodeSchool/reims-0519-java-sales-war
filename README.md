# reims-0519-java-sales-war

# SALES WAR

## Contexte :

Deux amies font les soldes dans un magasin, elles voient au loin une magnifique robe en promotion à 99 % mais le temps d’arriver dans le rayon pour prendre l’article, une grand-mère leur prend la robe sous leur nez. En colère, elles estiment que la grand-mère est trop vieille pour pouvoir bien porter cette robe. Elles décident donc lui mettre une raclée pour la récupérer.

Le jeu commence ici et deux joueurs doivent incarnés les deux amies qui font les soldes.

La grand mère a été sous estimé car c’est une grand-mère coriace elle a le double des statistiques des joueurs.

### Le déroulement du combat se fera au tour par tour, avec une attaque chacun disponible.

Une fois battu, elles prennent la robe mais réalisent a ce moment qu’une seule d’entre elle pourra l’acheter.Après l’échauffement avec la grand-mère elles décident donc de continuer la bagarre entre elles et que la gagnante remportera la robe.Déroulement du combat :Le combat se réalisera au tour pas tour.

Chaque joueur possèdent le choix entre :
- une attaque à dégâts variable.
- un blocage donnant une chance de bloquer le coup.

L’adversaire possèdent :
Une attaque basique qui infligent 2 fois plus de dégâts que celle des joueurs.Si l’adversaire arrive à 0 points de vie, le combat est gagné.
Si l’un des joueur arrive à 0 points de vie, le combat est perdu

## Créer la base de données du jeu

- Se connecter à la base de données en **"root"**.
- Créer la base de donnée **"sales_war"**.
- Sortir de la base de données et importer le fichier 
**"sales_war.sql"**
- Se connecter à la base de données nouvellement créer **"sales_war"**.
- Créer un utilisateur nommé **"cafy"**.
- Lui accorder les droits nécessaires pour la base de données **"sales_war"**.

```
mysql -u root -p 

CREATE DATABASE sales_war;

EXIT;

mysql -u root -D sales_war -p < sales_war.sql

mysql -u root -p -D sales_war

CREATE USER 'cafy'@'localhost' IDENTIFIED BY 'Saleswar51!';

GRANT ALL ON sales_war.* TO 'cafy'@'localhost';

EXIT;
```