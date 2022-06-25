# MicroService1_CompteBancaire
## 1. Objectif

The project consists in creating two micro-services

CompteBancaire se caractérise par {"IBAN" : "FR7630004000031234567890143", "type de compte" : "Compte courant", " interet" : 0.0, "frais de tenue de compte" : "gratuit" "}


OperationBancaire se caractérise par {"id operation" : 12345, "type operation" : "VIREMENT", "IBAN source" : "FR7630004000031234567890143","IBAN destination" : "USD", "montant" : 1000.0, "date" : "2021-12-30"} 
check  : https://github.com/samarrhilane/MicroService2_OperationBancaire

## 2. Installation 

```
git clone --single-branch --branch ruben https://github.com/samarrhilane/MicroService1_CompteBancaire
mvn clean install package
```

## 3. Set up the Spring Boot Application

```
cd MicroService1_CompteBancaire/target
java -jar CompteBancaire-0.0.1-SNAPSHOT.jar
```
## 4. Test on Postman

```
in Spring-Boot-Rest create new HTTP Request
tap:
http://localhost:8080/COMPTEBANCAIRE/

to get all bank account exist
```

## 5. Set up Docker

```
docker build -t CompteBancaire 
```
