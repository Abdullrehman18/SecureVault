# File Encryption & Decryption System using AES

### 🎓 Project Information
**Developed by:**
* **Student 1 Name:** Abdull Rehman
* **Student ID:** 25sp-029-cs
* **Student 2 Name:** Abdullah
* **Student ID:** 25sp-045-cs
* **Student 1 Name:** Abdul basir
* **Student ID:** 25sp-050-cs
* **Student 2 Name:** Muzammil
* **Student ID:** 25sp-021-cs

---

## 📖 Overview
This project is a **Java-based File Security System** that allows users to encrypt and decrypt multiple files simultaneously using the **AES (Advanced Encryption Standard)** algorithm. It features a graphical user interface (GUI) built with Java Swing and supports batch processing of files within a secured vault directory.

The system uses **AES/CBC/PKCS5Padding** to ensure high security, utilizing a unique 128-bit secret key and random Initialization Vectors (IV) for every encryption operation.

---

## 🚀 Features
* **Secure Encryption:** Uses AES 128-bit encryption with Cipher Block Chaining (CBC) mode.
* **Randomized IV:** Generates a unique Initialization Vector for every file to prevent pattern recognition.
* **Batch Processing:** Can process all files in a directory at once using `DirectoryStream` for memory efficiency.
* **GUI Interface:** User-friendly Swing interface with real-time Progress Bar and Status updates.
* **Multithreading:** Runs encryption/decryption tasks on background threads to keep the UI responsive.
* **Logging:** Automatically generates `operation.log` and report files for auditing activities.

---

## 🛠️ Tech Stack
* **Language:** Java (JDK 8+)
* **GUI:** Java Swing (JFrame, JProgressBar)
* **I/O:** Java NIO (New I/O) for file handling
* **Security:** `javax.crypto` (Cipher, SecretKey, IvParameterSpec)

---

## ⚙️ Installation & Setup

### Prerequisites
* Java Development Kit (JDK) installed.
* An IDE (IntelliJ IDEA, Eclipse, NetBeans) OR a Terminal/Command Prompt.

### Step 1: Clone the Repository
Download this project or clone it using git:
```bash
git clone [https://github.com/your-username/your-repo-name.git](https://github.com/your-username/your-repo-name.git)
cd your-repo-name
