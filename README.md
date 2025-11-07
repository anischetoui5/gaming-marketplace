# Gaming Marketplace API

A complete Spring Boot application for a gaming marketplace where users can purchase coins and use them to buy games, skins, and accessories.

## 🎯 Project Overview

**Application**: Gaming Marketplace "GameHub"

**Entités**: 
- User, Game, Product, CoinPackage, Transaction, Inventory

**Relations**:
- Un User peut avoir plusieurs Transactions et plusieurs Inventory items
- Un Game peut avoir plusieurs Products  
- Un Product appartient à un Game
- Une Transaction est liée à un User
- Un Inventory item est lié à un User et un Game ou Product

## 🚀 Features

- Complete CRUD operations for Users and Games
- Purchase system with virtual coins
- Inventory management
- Transaction history
- RESTful API with proper error handling

## 🛠 Technologies

- Spring Boot 3.2.0
- Spring Data JPA
- MySQL Database
- Maven
- Thymeleaf (for templates)

## 📋 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| POST | `/api/users` | Create new user |
| GET | `/api/games` | Get all games |
| POST | `/api/games` | Create new game |
| POST | `/api/marketplace/purchase-coins` | Buy coins |
| POST | `/api/marketplace/purchase-game` | Buy game |
| POST | `/api/marketplace/purchase-product` | Buy product |
| GET | `/api/marketplace/inventory/{userId}` | Get user inventory |
| GET | `/api/marketplace/transactions/{userId}` | Get user transactions |

## 🏃‍♂️ Running the Application

1. Start MySQL database
2. Update `application.properties` with your database credentials
3. Run: `mvn spring-boot:run`
4. Visit: `http://localhost:8080/` for API documentation

## 👥 Contributors

- Anis Chetoui
- Teacher: Hatem Awadi