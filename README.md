# Projet MOBG6 juin 2022

Ce dépôt contient les sources du projet <WC Map>.

## Description

L'application permet de trouver les toilettes disponibles autour de sa position.

## Persistance des données

Les données relatives aux comptes utilisateurs sont stocké dans la base de données locale de l'application.

## Service rest

Pour collecter les données relatives aux toilettes, des appels au service rest suivant sont effectués : https://opendata.brussels.be/explore/dataset/bruxelles_toilettes_publiques/api/?flg=fr
Pour obtenir les données de géographie l'api Google Map est utilisée

## Build

Pour build le project il est nécessaire d'ajouter une clé pour l'utilisation de l'api google map dans le fichier local.properties
Par exemple :
MAPS_API_KEY=XXXXXXXXXXXXXXX-XXXXXXXXXXXXXXX-XXXXXXXXXXXXX

Pour obtenir une API_KEY, je vous renvoie vers la documentation Google
https://developers.google.com/maps/gmp-get-started#api-key

## Auteur

**Rémy De Poorter** g52063
