# Firebase - Firestore Database Demo Project
This is to Learn the basic usage of Firebase and Cloud Firestore Database, To Achieve simple SignIn, SignUp work flow using Firestore.

### Basic Steps for Quick Start :
- Basic overview : https://firebase.google.com/docs/firestore/
- Quick Setup : https://firebase.google.com/docs/firestore/quickstart
- To Structuring and Managing Data : https://firebase.google.com/docs/firestore/manage-data/structure-data

### To Starts with this Project :
1. Create a Project on Firebase Console.
2. Register your application inside the project with SHA-1 certification key.
3. Add the Generated `google-services.json` File to your project, As mentioned in Google docs.
4. Build your project once.
5. Enjoy working app on Firestore.

### Dependencies

```gradle

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath 'com.google.gms:google-services:4.2.0'
    }
}

dependencies {
    implementation 'com.google.firebase:firebase-firestore:18.0.1'
    implementation 'com.google.firebase:firebase-auth:16.1.0'
}

apply plugin: 'com.google.gms.google-services'

```

### Functionality :
- Sign In
- Sign Up (Registration)
- Registered users listing on Main Screen on Successfully Logged In.
- Email Verification : User will received the Email Verification on Sign-Up if provided the existing Email.

### Want to Add more Functions?
All pull requests are welcome.

### Questions?
 
**Ping-Me on :**  [![Twitter](https://img.shields.io/badge/Twitter-%40UTM__Panchasara-blue.svg)](https://twitter.com/UTM_Panchasara)
[![Facebook](https://img.shields.io/badge/Facebook-Uttam%20Panchasara-blue.svg)](https://www.facebook.com/UttamPanchasara94)

<a href="https://stackoverflow.com/users/5719935/uttam-panchasara">
<img src="https://stackoverflow.com/users/flair/5719935.png" width="208" height="58" alt="profile for Uttam Panchasara at Stack Overflow, Q&amp;A for professional and enthusiast programmers" title="profile for Uttam Panchasara at Stack Overflow, Q&amp;A for professional and enthusiast programmers">
</a>
