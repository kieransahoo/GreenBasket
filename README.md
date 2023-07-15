# Project Title : GreenBasket - Online vegetable sales application

## Project Description 
An online vegetable sales application allows customers to easily purchase fresh, locally sourced vegetable from their phone or computer. With a user-friendly interface, customers can browse and select from a wide variety of fruits and vegetables, customize their order, and choose a delivery or pickup option that works for them.

**Frontend Link** : [Netlify-Link](https://green-basket-deployed.netlify.app/)

<br>
<img src="https://user-images.githubusercontent.com/107461052/212683987-3cf29572-7af3-4b84-bc8d-57c594f8a75e.png"  width="400" height="200" position="center">
<br>

- An online vegetable sales application allows customers to easily purchase fresh, locally sourced produce from their phone or computer.
- The application features a user-friendly interface, allowing customers to browse and select from a wide variety of fruits and vegetables.
- Customers can customize their order and choose a delivery or pickup option that works for them.
- The backend of the application is built using Java and Spring Framework.
- The backend handles all the business logic and communicates with the database (MySQL).
- The backend exposes RESTful endpoints for the frontend to consume.
- The code follows best practices for maintainability and scalability.
- The application is continuously integrated and deployed.
- This repository is open-source and contributions are welcome.
- The application is built using modern technologies such as Spring Boot.
- It includes a swagger documentation for the endpoints.
- The application follows best practices for security in terms of input validation and access control.

- This project is developed by team of 5 Aspiring Developers .

---
## ER Diagram
![ER Diagram](https://github.com/kieransahoo/GreenBasket/assets/101393428/9383e35e-9638-45e3-9454-cc54e1a6e5f5)

---
## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* HTML
* CSS
* JavaScript

------------------------------------------------------------------------------
## Modules
------------------------------------------------------------------------------
* Login, Logout Module
* Customer Module
* Admin Module
* AddToCart Module
* Order Module
* Vegetable Stock Module

---------------------------------------------------------------------------------
## Features

---------------------------------------------------------------------------------
* Customer and Admin authentication & validation with usersession's sessionid.
* Admin Features:
    * Administrator Role of the entire application
    * Only registered admins with valid sessionid can add/update/delete Vegetable in the database.
    * Admin can access the details of orders, bill details, customer list.
* Customer Features:
    * Registering themselves with application, and logging in to get the valid sessionid.
    * Customer can add vegetables into the cart
    * Only logged in user can access their all order history.

--------------------------------------------------------------------------------
## Contributors
--------------------------------------------------------------------------------
- <a href="https://github.com/mynkgupta22">Mayank Gupta</a>

- <a href="https://github.com/kieransahoo">Kieran Sahoo</a>

- <a href="https://github.com/mynkgupta22">Nitesh Pal</a>

- <a href="https://github.com/mynkgupta22">Sadanand Mare</a>

- <a href="https://github.com/mynkgupta22">Santosh Kumar</a>


## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](GrennBasket\src\main\resources\application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8083
    spring.datasource.url=jdbc:mysql://localhost:3306/greenBasket;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

## API Root Endpoint

`https://localhost:8083/`

`http://localhost:8083/swagger-ui.html`







