# 🔥 G-Track API
📍 **Gas Tracking & Preferences API** - Développé par **H Technologies**

## 🛠️ Description
G-Track API est un système avancé de **gestion, localisation et analyse des préférences des gaz**.  
Il permet de **localiser les distributeurs** via **PostGIS**, de **gérer les stocks** et de **suivre les préférences** des utilisateurs en fonction des achats.

La sécurité repose sur **Spring Security**, avec un système de **rôles et privilèges** sécurisé par **JWT Token**.

---

## 🚀 **Fonctionnalités principales**

### 🔎 **Localisation des distributeurs**
✔ Stockage des **coordonnées GPS** des distributeurs avec **PostGIS**  
✔ Recherche des **distributeurs les plus proches** d’un utilisateur  
✔ Gestion des **stocks** par distributeur

### 📦 **Gestion des stocks et préférences**
✔ Suivi des **stocks** en temps réel  
✔ Gestion des **types de gaz** ( Butane, Propane, etc.)  
✔ Association des gaz à un **fabricant** et une **marque**  
✔ Analyse des **modèles et marques les plus achetés**

### 🔐 **Sécurité avancée**
✔ **Authentification avec JWT Token**  
✔ **Spring Security** avec gestion des **rôles et privilèges**  
✔ Attribution dynamique des rôles aux utilisateurs

---

## 🛠️ **Technologies utilisées**

| Technologie | Utilisation |
|-------------|------------|
| **Java 17** | Langage principal |
| **Spring Boot** | Framework backend |
| **PostgreSQL + PostGIS** | Base de données avec gestion des données spatiales |
| **Spring Security + JWT** | Sécurité et authentification |
| **JPA / Hibernate** | ORM pour l'accès aux données |
| **Maven** | Gestion des dépendances |

---

## 📡 **Endpoints principaux**

| Méthode | URL | Description |
|---------|-----|------------|
| `POST` | `/authentication/authenticate` | Authentification et récupération du JWT Token |
| `POST` | `/utilisateur/grantrole/` | Attribution d’un rôle à un utilisateur |
| `POST` | `/commande/nouveau` | Création d’une nouvelle commande |
| `GET`  | `/distributeurs/proches?lat=...&lng=...` | Recherche des distributeurs les plus proches |

---

## 🔐 **Sécurité : Gestion des Rôles & JWT**

- **Rôles disponibles** :
    - `ROLE_ADMIN` : Accès total à l’API
    - `ROLE_DISTRIBUTEUR` : Gestion des stocks et des commandes
    - `ROLE_UTILISATEUR` : Consultation et commande de gaz

- **Procédure d’authentification** :  
  1️⃣ **L’utilisateur s’authentifie** via `/authentication/authenticate`  
  2️⃣ **L’API retourne un JWT Token**  
  3️⃣ **L’utilisateur utilise ce Token** dans les requêtes avec `Authorization: Bearer <token>`

---

## 🔧 **Installation & Exécution**

### 1️⃣ **Pré-requis**
- **JDK 17**
- **Maven**
- **PostgreSQL avec extension PostGIS**

### 2️⃣ **Installation du projet**
```sh
git clone https://github.com/h-technologies/g-track-api.git
cd g-track-api
mvn clean install
```

### 3️⃣ **Configuration de la base de données**
Modifier `application.yml` :
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/gtrack
    username: postgres
    password: password
  jpa:
    hibernate:
      ddl-auto: update
```

### 4️⃣ **Lancer le projet**
```sh
mvn spring-boot:run
```

---

## 📄 **Exemple d'authentification avec `curl`**
```sh
curl -X POST http://localhost:8080/authentication/authenticate      -H "Content-Type: application/json"      -d '{
          "email": "admin@example.com",
          "password": "password"
        }'
```
➡ **Réponse attendue :**
```json
{
  "accessToken": "string",
  "nom": "string",
  "prenom": "string",
  "email": "string",
  "photo": "string"
}
```

---

## ✨ **Développé par H Technologies**
🚀 G-Track API est conçu par **H Technologies** pour optimiser la gestion des stocks et la localisation des distributeurs de gaz.

---
