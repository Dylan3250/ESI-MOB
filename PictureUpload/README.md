# Projet MOBG5 - PictureUpload

![Overview](https://i.imgur.com/jMilgtE.gif)

Ce dépôt contient les sources du projet PictureUpload.

## Description

L'application permet de mettre en ligne sur un serveur WEB des images choisies depuis l'appareil mobile après une connexion avec les services Google.

## Persistance des données

Les images ainsi que des informations comme le nom sont envoyées sur le serveur grâce à une requête POST, les utilisateurs sont connectés grâce à un appel à l'API de Google. Il y a également une liste des dernières connexions stockées dans l'appareil.

## Backend

Le backend est géré avec du PHP, les images sont envoyées en POST avec un chemin unique, hashé sur base du nom, de l'email et de la date en SHA1. Les scripts sont disponibles dans le dossier " **backend** ".

## Service rest

Pour envoyer les données, il faut envoyer l'image sur le service WEB. Pour afficher les miniatures, il faudra appeler un service WEB avec les différentes URL.

## Auteur

**Dylan BRICAR** @54027
Pour envoyer les données, il faut envoyer l'image sur le service WEB. Pour afficher les miniatures, il faudra appeler un service WEB avec les différentes URL.

## Auteur

**Dylan BRICAR** @54027
