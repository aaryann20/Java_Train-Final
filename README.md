# ༄ Indian Railways Reservation System ༄  
#### *A Java-based train booking platform with secure auth, real-time seat selection & JSON persistence*  

<p align="center">
  <img src="media/image1.png" alt="Demo" width="600"/>
</p>

---

### ✧ **Features**  
- **Secure Auth** │ BCrypt hashing + session management  
- **Live Seat Map** │ Interactive GUI with real-time availability  
- **JSON Database** │ Jackson for lightweight persistence  
- **MVC Design** │ Clean separation of logic, data & UI  
- **OOP Mastery** │ Polymorphism, encapsulation, inheritance  

---

### ༺ Tech Stack ༻  
| **Category**       | **Tools**                                   |  
|-------------------|--------------------------------------------|  
| `Language`        | Java 17                                    |  
| `UI`              | Java Swing (Custom Styling)                |  
| `Build Tool`      | Gradle                                     |  
| `Database`        | JSON (Jackson)                             |  
| `Security`        | BCrypt                                     |  

---

### ༒ **Code Highlights**  
```java
// Singleton DatabaseManager Example
public class DatabaseManager {
    private static DatabaseManager instance;
    private List<Train> trains;

    private DatabaseManager() {
        loadTrainsFromJson(); // Jackson deserialization
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
}
