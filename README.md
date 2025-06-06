# 🚆 Indian Railways Reservation System

A comprehensive desktop application for train ticket booking and management, built with Java and Swing. This system provides a complete railway reservation experience with secure user authentication, interactive seat selection, and efficient booking management.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Swing](https://img.shields.io/badge/UI-Java%20Swing-blue?style=flat-square)
![Gradle](https://img.shields.io/badge/Build-Gradle-green?style=flat-square&logo=gradle)
![JSON](https://img.shields.io/badge/Database-JSON-yellow?style=flat-square)
![BCrypt](https://img.shields.io/badge/Security-BCrypt-red?style=flat-square)

## 📋 Table of Contents
- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Installation](#-installation)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [Security Features](#-security-features)
- [Screenshots](#-screenshots)
- [Future Enhancements](#-future-enhancements)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### 🔐 User Authentication
- **Secure Registration**: New user registration with input validation
- **Encrypted Login**: Password hashing using BCrypt for enhanced security
- **Session Management**: Maintains user state throughout the application

### 🔍 Train Search & Booking
- **Smart Search**: Find trains by source, destination, and travel date
- **Interactive Seat Selection**: Visual seat map with real-time availability
- **Instant Booking**: Quick and efficient ticket booking process
- **Seat Availability**: Real-time checking to prevent double bookings

### 📊 Booking Management
- **View Bookings**: Comprehensive list of all user bookings
- **Booking Details**: Detailed information for each reservation
- **Cancellation**: Easy booking cancellation with confirmation dialogs
- **Booking History**: Complete transaction history tracking

### 🎨 User Interface
- **Modern Design**: Clean, intuitive interface with custom styling
- **Responsive Layout**: Optimized for different screen sizes
- **Form Validation**: Real-time input validation and error handling
- **Interactive Components**: Dynamic tables, dialogs, and buttons

## 🛠 Technology Stack

| Component | Technology |
|-----------|------------|
| **Language** | Java 17 |
| **UI Framework** | Java Swing (Custom Styling) |
| **Build Tool** | Gradle |
| **Database** | JSON with Jackson |
| **Security** | BCrypt Password Hashing |
| **Libraries** | Jackson, BCrypt, JavaTimeModule |

## 🏗 Architecture

The application follows **MVC (Model-View-Controller)** architecture with additional design patterns:

- **Model**: Entity classes (`User`, `Train`, `Booking`)
- **View**: GUI panels and components (Swing-based)
- **Controller**: Service classes handling business logic
- **Repository Pattern**: `DatabaseManager` for data access abstraction
- **Singleton Pattern**: Database and theme management
- **Service Layer Pattern**: Encapsulated business logic

## 🚀 Installation

### Prerequisites
- Java 17 or higher
- Gradle 7.0+

### Steps
1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/train-booking-system.git
   cd train-booking-system
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew run
   ```

## 📖 Usage

1. **Launch the Application**
   - Run the main class to start the GUI application

2. **Register/Login**
   - New users: Click "Register" and fill in your details
   - Existing users: Enter your credentials to login

3. **Search Trains**
   - Select source and destination cities
   - Choose your travel date
   - Browse available trains

4. **Book Tickets**
   - Select your preferred train
   - Choose seats from the interactive seat map
   - Confirm your booking

5. **Manage Bookings**
   - View all your bookings in the "My Bookings" section
   - Cancel bookings if needed

## 📁 Project Structure

```
src/
├── main/
│   └── java/
│       ├── models/          # Entity classes
│       │   ├── User.java
│       │   ├── Train.java
│       │   └── Booking.java
│       ├── services/        # Business logic
│       │   ├── UserService.java
│       │   ├── TrainService.java
│       │   └── BookingService.java
│       ├── views/           # GUI panels
│       │   ├── LoginPanel.java
│       │   ├── SearchPanel.java
│       │   └── BookingPanel.java
│       ├── database/        # Data persistence
│       │   └── DatabaseManager.java
│       └── utils/           # Utility classes
│           └── SecurityUtils.java
└── resources/
    └── data/               # JSON data files
        ├── users.json
        ├── trains.json
        └── bookings.json
```

## 🔒 Security Features

- **Password Encryption**: BCrypt hashing with salt for secure password storage
- **Input Validation**: Comprehensive validation to prevent injection attacks
- **Session Security**: Secure session management throughout the application
- **Error Handling**: Robust error handling to prevent information leakage

## 📸 Screenshots

### Login & Registration
| Login Screen | Registration Screen |
|--------------|---------------------|
| ![Login Screen](screenshots/login.png) | ![Registration Screen](screenshots/register.png) |

### Dashboard & Analytics
| Main Dashboard | Booking Statistics |
|----------------|-------------------|
| ![Dashboard](screenshots/dashboard.png) | ![Statistics](screenshots/statistics.png) |

### Train Search & Booking
| Search Interface | Train Results | Seat Selection |
|------------------|---------------|----------------|
| ![Search Trains](screenshots/search.png) | ![Train Results](screenshots/train-results.png) | ![Seat Selection](screenshots/seat-selection.png) |

### Booking Confirmation
| Confirmation Dialog |
|-------------------|
| ![Booking Confirmed](screenshots/booking-confirmed.png) |

### Key UI Features Demonstrated:
- **Clean, Modern Interface**: Professional blue gradient theme with intuitive navigation
- **Interactive Dashboard**: Real-time statistics showing active bookings, available trains, and popular destinations
- **Advanced Search**: Easy-to-use search form with popular routes for quick selection
- **Visual Seat Map**: Color-coded seat selection with real-time availability (Green: Available, Red: Booked, Blue: Selected)
- **Data Visualization**: Route popularity charts and booking analytics
- **Responsive Design**: Well-organized layout that works across different screen sizes
- **User-Friendly Dialogs**: Clear confirmation messages and smooth user experience

## 🚀 Future Enhancements

- [ ] **Database Migration**: Migrate to MySQL/PostgreSQL for better scalability
- [ ] **Payment Integration**: Add payment gateway for online transactions
- [ ] **Notification System**: Email/SMS notifications for booking confirmations
- [ ] **Reporting Module**: Generate booking statistics and reports
- [ ] **Mobile App**: Develop companion mobile application
- [ ] **Multi-language Support**: Support for regional languages

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Aaryan Soni**
- GitHub: [@aaryann20](https://github.com/aaryann20)
- LinkedIn: [LinkedIn](https://www.linkedin.com/in/aaryan-soni2004/)
- Email: soniaaryan2020@gmail.com

## 🙏 Acknowledgments

- Thanks to the Java Swing community for UI inspiration
- BCrypt library for secure password hashing
- Jackson library for JSON processing

---

⭐ **Star this repository if you found it helpful!**
