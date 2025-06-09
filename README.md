# COLLEGEFINALPROJECT
# 🏨 Hotel Management System  
*Java Swing | File-based Persistence*  
**License:** MIT  

---

## Overview  
A Java desktop application to manage hotel operations such as rooms and bookings. Built with core Object-Oriented Programming principles, featuring a clean, responsive Swing-based GUI and persistent storage using simple file I/O.

---

## 🚀 Features  

- 🛏️ **Room Management:** View, book, and cancel room reservations  
- 🧾 **Billing:** Automatically generate bills on booking ($2000 per room)  
- 💾 **Persistence:** Save and load bookings from a text file (`rooms.txt`)  
- 🎨 **User Interface:** Intuitive and user-friendly GUI designed with Java Swing  
- 🧠 **OOP Design:** Modular classes with clear separation of model and UI  

---

## 🧰 Tech Stack  

| Category    | Technology       |  
|-------------|------------------|  
| Language    | Java (JDK 8+)    |  
| GUI         | Java Swing       |  
| Storage     | File I/O (`rooms.txt`) |  
| IDE         | Any (e.g., VS Code, IntelliJ IDEA) |  

---

## 📋 Project Structure  
HotelManagementProject/
│
├── COLLEGE/
│   ├── model/
│   │   ├── Room.java              # Class representing a hotel room
│   │   └── Hotel.java             # Hotel class managing rooms and file I/O
│   │
│   ├── ui/
│   │   └── HotelManagementUI.java  # Swing-based GUI class
│
├── Main.java                     # Main class to launch the application
├── rooms.txt                    # Text file storing room booking data (created/updated automatically)
└── README.md                    # Project documentation file
