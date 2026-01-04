# File Encryption & Decryption System using AES

###  Project Information
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

## Overview
This project is a **Java-based File Security System** that allows users to encrypt and decrypt multiple files simultaneously using the **AES (Advanced Encryption Standard)** algorithm. It features a graphical user interface (GUI) built with Java Swing and supports batch processing of files within a secure vault directory.

The system uses **AES/CBC/PKCS5Padding** to ensure high security, utilizing a unique 128-bit secret key and random Initialization Vectors (IV) for every encryption operation.

---

##  Features
* **Secure Encryption:** Uses AES 128-bit encryption with Cipher Block Chaining (CBC) mode.
* **Randomized IV:** Generates a unique Initialization Vector for every file to prevent pattern recognition.
* **Batch Processing:** Can process all files in a directory at once using `DirectoryStream` for memory efficiency.
* **GUI Interface:** User-friendly Swing interface with real-time Progress Bar and Status updates.
* **Multithreading:** Runs encryption/decryption tasks on background threads to keep the UI responsive.
* **Logging:** Automatically generates `operation.log` and report files for auditing activities.

---

##  Tech Stack
* **Language:** Java (JDK 8+)
* **GUI:** Java Swing (JFrame, JProgressBar)
* **I/O:** Java NIO (New I/O) for file handling
* **Security:** `javax.crypto` (Cipher, SecretKey, IvParameterSpec)

---

##  Installation & Setup

### Prerequisites
* Java Development Kit (JDK) installed.
* An IDE (IntelliJ IDEA, Eclipse, NetBeans) OR a Terminal/Command Prompt.

### Step 1: Clone the Repository
Download this project or clone it using git:

Step 2: Create the Vault Directory
The project relies on a specific folder structure. Create a folder named Vault in the root directory. (Note: The program will attempt to create directories if they don't exist, but it is good practice to set it up manually.)

Step 3: Generate the Secret Key (CRITICAL STEP)
Before running the main application, you must generate the AES key. This only needs to be done once.

Compile and run KeyGeneratorUtil.java.

This will create a binary file at Vault/secret.key.

If using Command Line:

Bash

javac KeyGeneratorUtil.java
java KeyGeneratorUtil
Output: "Key generated successfully."

### How to Use
1. Prepare Files
Place the files you want to encrypt (e.g., .txt, .jpg, .pdf) inside the Vault/data folder.

2. Run the Application
Run the main GUI file: CryptoGUI.java.

If using Command Line:
``Bash``

``javac CryptoGUI.java
java CryptoGUI``


3. Encryption
Click the "Encrypt Files" button.

The system will:

Scan Vault/data.

Encrypt files (adding .enc extension).

Delete the original files for security.

Update the progress bar.

4. Decryption
Click the "Decrypt Files" button.

The system will:

Read .enc files from Vault/data.

Unlock them using the secret.key.

Restore them to their original format.

Delete the encrypted versions.

### Project Structure
├── CryptoGUI.java         # Main entry point (The Interface)
├── CryptoEngine.java      # Logic for AES Encryption/Decryption
├── KeyGeneratorUtil.java  # Script to generate the secret.key
├── Encryptor.java         # Handles file looping for encryption
├── Decryptor.java         # Handles file looping for decryption
├── Vault/                 # The secure folder
│   ├── secret.key         # Generated Key (Do not delete!)
│   └── data/              # Put your files here
├── operation.log          # Logs of all actions
└── encryption_report.txt  # Detailed report of last run


!!! Important Notes
Do not delete Vault/secret.key: If you lose this file after encrypting data, your files cannot be recovered.

File Overwriting: The program deletes the original file after processing is complete. Please make sure you have backups if testing for the first time.


`If you have any questions, feel free to contact me at this email: rehmanmcpe12@gmail.com; also give credits if you copy this :)`
