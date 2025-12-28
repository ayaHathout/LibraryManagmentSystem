# Library Management System 📚 (Spring Boot Backend)

A **secure and role-based backend application** to efficiently manage library operations, including books, members, and staff, built with **Spring Boot & MySQL**. 

The system is developed using **Spring MVC**, **Spring Data JPA**, and **Spring Security**, providing a clean layered architecture, secure authentication, and robust data persistence.

This project demonstrates key backend development skills such as **RESTful API design, JPA/Hibernate integration, role-based access control, and secure user authentication**.

Designed to showcase practical skills in **Java backend development and database management**, following clean architecture and best practices.

---

## 🧠 Features

- ✅ **Role-based access control:** Administrator, Librarian, Staff  
- 📘 **Book management:** CRUD operations with extended metadata (title, authors, publisher, categories, language, ISBN, edition, summary, cover image)  
- 👩‍💼 **Member management:** Register, login, update profiles  
- 🏢 **System user management:** Role-based permissions, secure password storage, authentication, activity logging  
- 📊 **Borrowing & return functionality:** Track book loans, due dates, return status, and fines  
- 🔁 **Database integration:** JPA/Hibernate ORM for all entities  
- 🔐 **Validation & error handling:** Input validation, structured API responses, exception handling  
- 📂 **SQL scripts included:** Sample data and database initialization  
- 📦 **Clean architecture:** Layered structure (Controller → Service → Repository)

---

## 🗄 Database Design (ERD)

![Library Management System ERD](images/libraryERD.PNG)

The database schema is designed to support a scalable and flexible library management system, handling books, users, and borrowing transactions efficiently while ensuring data consistency and security.

### 🗄️ Core Entities

- **Books**
  - Stores extended metadata: title, ISBN, edition, publication year, summary, language, cover image
  - Tracks total and available copies
  - Relationships:
    - Many-to-Many with **Authors**
    - Many-to-Many with **Categories**
    - Many-to-One with **Publisher**

- **Authors**:  
  - Stores author details such as name and biography

- **Categories**:  
  - Supports hierarchical structure (parent-child) for genre classification  
  - Example: Fiction → Mystery

- **Borrowers**:  
  - Represents library members
  - Stores personal information (name, email, phone, address)

- **BorrowRecords**:  
  - Tracks borrowing transactions
  - Includes borrow date, due date, return date, status, and fines
  - Links borrowers with books  

- **Users**:  
  - Represents system users (Administrator, Librarian, Staff)
  - Implements role-based access control using ENUM roles 

- **UserActivityLogs**:  
  - Logs system activities such as create, update, delete, login, borrow, and return
  - Ensures auditing and accountability

### 📌 Why This Design?

- Normalized schema ensures data consistency and avoids duplication  
- Flexible Many-to-Many relationships support multiple authors and categories per book  
- Role-based access control enhances system security  
- `BorrowRecords` decouples borrowing logic from books and users  
- Activity logging provides transparency and auditability

---

## 🧱 Tech Stack

- **Language:** Java  
- **Framework:** Spring Boot  
- **Web Layer:** Spring MVC  
- **Security:** Spring Security (Authentication & Role-Based Authorization)  
- **Data Access:** Spring Data JPA  
- **ORM:** Hibernate  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Architecture:** Layered Architecture (Controller, Service, Repository)  
- **API Style:** RESTful APIs

---

## 🚀 Installation & Running Instructions

### 📋 Requirements
- Java 21
- MySQL
- Maven

### ▶️ Running the Application

1. **Clone the repository**:
```bash
git clone https://github.com/ayaHathout/LibraryManagmentSystem.git
```

2. **Create a MySQL database** (e.g., `library_db`) and update the database configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

3. **Initialize the database (optional)**:
  You can use the provided SQL scripts to create the schema and insert sample data:
  - [Download SQL Schema](sql/schema.sql)
  - [Download SQL Scripts](sql/sample_data.sql)

4. **Build and run the application** using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

5. **Access the application** at: [http://localhost:8086](http://localhost:8086)

6. **Test the REST APIs** using **Postman** or any REST client.

---

## 🛠 API Endpoints

All API endpoints are included in the **Postman collection**.  
You can import it into Postman to test the application easily:  

[Download Postman Collection](postman/JSON/libraryCollection.json)

---

## 👩‍💻 About the Author

**Aya Hathout** – Java Software Engineer | ITI 9-Month Graduate | Enterprise & Web Apps Development (Java)  
- 🌐 LinkedIn: [https://www.linkedin.com/in/aya-hathout](https://www.linkedin.com/in/aya-hathout)  
- 🐙 GitHub: [https://github.com/ayaHathout](https://github.com/ayaHathout)

💬 Feel free to **reach out** for questions, collaboration, or feedback!
