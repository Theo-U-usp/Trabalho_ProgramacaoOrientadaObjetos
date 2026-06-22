# Java Library Management System

## Overview

This project is a desktop Library Management System developed in Java using Swing. The application allows librarians and administrators to manage books, patrons, and loans while maintaining data persistence through file serialization.

The system was developed following object-oriented programming principles and includes automated tests using JUnit.

---

## Features

### Book Management

* Add books
* Edit books
* Delete books
* Search books by:

  * Title
  * Author
  * ISBN
  * Genre

Each book contains:

* Title
* Author
* ISBN
* Genre
* Publication Year
* Available Copies

### Patron Management

* Add patrons
* Edit patrons
* Delete patrons
* Search patrons by:

  * Name
  * ID

Each patron contains:

* Name
* ID
* Contact Information
* Borrowing History
* Current Fine Balance

### Loan Management

* Check out books
* Check in books
* Automatic due date generation (14 days)
* Overdue fine calculation
* Borrowing history tracking

### Reports

* Currently checked-out books
* Overdue loans
* Patron borrowing history

### User Authentication

Two user roles are available:

#### Administrator

* Full access to the system
* Manage books
* Manage patrons
* Manage loans
* Reset fines

#### Librarian

* Search books and patrons
* Check out books
* Check in books
* View reports
* Cannot add, edit, or delete books/patrons

### Data Persistence

The system automatically saves and loads data using Java serialization.

Stored data:

* Books
* Patrons
* Loans

Data file:

```text
library.dat
```

### Input Validation

* ISBN stored as String and validated as numeric
* Patron ID stored as String and validated as numeric
* Duplicate ISBN prevention
* Duplicate Patron ID prevention
* Invalid input handling through dialog messages

---

## Project Structure

```text
PROJETO/
│
├── src/
│   ├── AdminScreen.java
│   ├── LibrarianScreen.java
│   ├── LoginScreen.java
│   ├── LibrarySystem.java
│   ├── Book.java
│   ├── BookManagement.java
│   ├── Patron.java
│   ├── PatronManagement.java
│   ├── Loan.java
│   ├── LoanManagement.java
│   ├── LibraryData.java
│   └── LibraryTests.java
│
├── lib/
│   ├── junit-4.13.2.jar
│   └── hamcrest-core-1.3.jar
│
├── library.dat
└── credential.txt
```

---

## System Architecture

```text
LibrarySystem
      |
      v
 LoginScreen
   /       \
  v         v
Admin   Librarian
  |         |
  +---------+
        |
  -------------------
  |    |     |      |
Book Patron Loan LibraryData
```

---

## Testing

JUnit 4 was used to test the application.

### Tested Components

* Book
* BookManagement
* Patron
* PatronManagement
* Loan
* LoanManagement
* LibraryData
* LoginScreen
* AdminScreen
* LibrarianScreen

### Current Coverage

| Class            | Coverage |
| ---------------- | -------- |
| Book             | 100%     |
| Loan             | 100%     |
| LibraryData      | 100%     |
| LibraryTests     | 99%      |
| PatronManagement | 91%      |
| BookManagement   | 89%      |
| Patron           | 73%      |
| LoanManagement   | 67%      |
| LoginScreen      | 53%      |
| LibrarianScreen  | 28%      |
| AdminScreen      | 18%      |

---

## How to Run

### Prerequisites

* Java JDK 17+
* Visual Studio Code
* Extension Pack for Java

### Running the Application

Compile:

```bash
javac src/*.java
```

Run:

```bash
java -cp src LibrarySystem
```

### Running Tests

```bash
java -cp ".;src;lib/*" org.junit.runner.JUnitCore LibraryTests
```

---

## Default Credentials

### Administrator

```text
Username: admin
Password: 1234
```

### Librarian

```text
Username: librarian
Password: 5678
```

---

## Design Decisions

* Java Swing was chosen for the graphical user interface.
* Java Serialization was used instead of a database to simplify persistence.
* ISBN and Patron ID are stored as Strings to preserve leading zeros.
* Business rules were separated into management classes to improve maintainability and testability.

---



---

## Authors

| Name                          | USP Number |
| ----------------------------- | ---------- |
| Theo Urbano Gaudencio de Sene | 12558717   |
|                               |            |

University of São Paulo (USP)
