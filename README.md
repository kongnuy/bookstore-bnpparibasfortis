# BookStore BNP Paribas Fortis

## KATA - DISTRIBUTED DEVELOPER - Online Bookstore

This is a simple code kata that involves creating a basic front-end in React and a back-end using a RESTful API. We will create a Simple Online Bookstore. We will display a list of books and users will have the possibility to add books to their cart, display the cart and modify the quantity of items and remove items from the cart. The system will consist of a React front-end for the user interface and a Java/SpringBoot back-end for handling API requests.

## Backend Development

### Prerequisite

In other to work on this project locally you need

- Java 17 or higher
- Maven 3.5 or higher
- MySQL databse

### Fork this repo

You can fork this repo if needed by clicking the fork button in the top right corner of this page.

### Clone on your local machine

```bash
git clone https://github.com/kongnuy/bookstore-bnpparibasfortis.git
```

### Navigate to api project directory

```bash
cd api
```

It is a spring boot 4 project. To run it, make sure you copy and rename the **application.sample.yaml** to **application-local.yaml**

Then fill in the required credentials of your database server. Also fill the jwt token secret. Any Base64 string will be good for that. Then run,

```bash
mvn clean package  -Dspring.profiles.active=local
```

Then to launch the api run

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

## Frontend Development

### Prerequisite

In other to work on this project locally you need

- Node 20 or higher
- NPM 11 or higher

### Navigate to ui project directory

```bash
cd ui
```

Run

```bash
npm install
```

Then to launch the ui run.

```bash
npm run dev
```

Make sure the api url link in **ui/src/\_constants/index.ts** matches your own locally

## Improvements

This work is not perfect!

### API

- Integrate unit tests for all layers
- Improve swagger ui documentation of all controllers
- Define a controllerAdvice for handle exceptions
- Implement user inputs validation for all DTOs
- Create both user and technical documentation
- Create more exception classes fr specific errors
- Implement rate limiter
- Integrate caching

### UI

- Delete all any type annotations and define real interfaces or types
- Integrate test with jest
- Better management of error and display more precise error messages to the users
