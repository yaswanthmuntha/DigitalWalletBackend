# 💼 Digital Wallet Backend

A Spring Boot-based backend system that simulates a digital wallet platform. Users can register, manage their wallet accounts, view balances, and eventually transfer money securely.

---

## 🚀 Features Implemented

* ✅ **User Registration** with embedded address details
* ✅ **Unique Account Number** generation
* ✅ **View Wallet Balance** by account number
* ✅ **Custom Exception Handling** with `@ControllerAdvice`
* ✅ **DTO Mapping** to secure responses
* ✅ **Field Validation** using `javax.validation`

---

## 🔐 Upcoming Features

* 🔄 JWT Authentication with Spring Security
* 🔢 OTP-based login verification
* 🔐 Role-based Access (User/Admin)
* 💸 Money transfer between users
* 📜 Transaction history & statement generation

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA (MySQL)
* Lombok
* REST API
* Maven
* Bean Validation
* Exception Handling

---

## 📁 Project Structure

```
DigitalWalletBackend
├── controller          # REST endpoints
├── dto                 # Response DTOs
├── entity              # JPA entities (WalletUser, Address)
├── exception           # Custom exceptions & handler
├── repository          # Spring Data JPA interfaces
├── service             # Service layer & business logic
├── config              # Security configs (upcoming)
└── DigitalWalletBackendApplication.java
```

---

## 📦 API Endpoints (Implemented)

### Register a Wallet User

```
POST /api/users
```

**Request Body:**

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "gender": "Male",
  "dob": "1990-01-01",
  "aadharNo": "123456789012",
  "panCardNo": "ABCDE1234F",
  "email": "john.doe@example.com",
  "phoneNumber": "9876543210",
  "address": {
    "doorNo": "12",
    "street": "Park Street",
    "town": "MG Road",
    "district": "Hyderabad",
    "state": "Telangana",
    "pinCode": "500001"
  }
}
```

### View Wallet Balance

```
GET /api/users/account/{accountNumber}/balance
```

---

## 📝 Getting Started

### Prerequisites

* Java 17
* MySQL
* Maven

### Steps

1. Clone the repo
2. Update DB credentials in `application.properties`
3. Run using:

```bash
mvn spring-boot:run
```

---

## 🙋‍♂️ Author

**Yaswanth Muntha**

Feel free to contribute or raise issues for improvement. Security integration and admin flows coming soon!

---

> ⚠️ This backend is under active development. Authentication and admin controls are WIP.
