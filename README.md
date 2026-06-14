# InvestShop

Application de gestion de portefeuilles d'investissement developper avec Spring Boot et Angular.

## Technologies utilisées
# Backend
Java 17
Spring Boot 3.2.5
Spring Data JPA + Hibernate
MapStruct + Lombok
Base de données H2
Maven 3.9
# Frontend
Angular 19
TypeScript
HTML / CSS

## Lancer le projet
j'ai mis la partie backend dans le dossier "backend" et la partie frontend dans le dosssier "frontend"
# Backend
cd backend
mvn spring-boot:run
L'API est accessible sur : `http://localhost:8080`

# Frontend
cd frontend
ng serve
L'application est accessible sur : `http://localhost:4200`

# application0properties
Conservant le fichier properties il est dans le dossier ressource et n'est pas externalisé, je peux l'externaliser si besoin

# Base de données
Pour ce projet j'ai utilisé H2 pour simplifier le développement et les tests.
Le passage à Oracle en production nécessite juste de modifier 3 lignes dans application.properties 
Le code JPA/Hibernate est entièrement compatible Oracle sans aucune modification.

