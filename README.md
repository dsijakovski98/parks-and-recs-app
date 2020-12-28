#  Parks & Recs
Free parking services app made for Android mobile phones.


# Table of Contents

#### 1. Introduction
#### 2. Install guide
#### 3. Architecture & Technologies
#### 4. Use
* ###### 4.1 Create Account
* ###### 4.2 Log in
* ###### 4.3 Create Reservation
* ###### 4.4 My Reservations



---
## 1. Introduction

***Parks & Recs*** is a free parking service app made in [Android Studio](https://developer.android.com/studio). Users can create an account and reserve a parking lot space in any of the available cities across the country! Once they do, they are a simple button click away from a navigation system to their desired parking spot.

---
## 2. Install guide
To install ***Parks & Recs***, all you need to do is download the APK file and install it on your phone. You can do this just by clicking on the file.

#### **Download link -> [Parks & Recs APK](https://drive.google.com/file/d/12D27ls7Un5dR1zuxvk5ByMlZImtxt_QZ/view?usp=sharing)**

> As this app is currently not available in Google Play Store, you might need to enable **installation from unknown sources** in your settings - [More info](https://www.maketecheasier.com/install-apps-from-unknown-sources-android/)

---
## 3. Architecture & Technologies
For all you nerds out there, this section covers the main system architecture of the application, as well as the technology and libraries that went into creating it.

#### Architecture
* ##### 5 Activities
    - **User authentication activity** - Login and register sections
    - **Cities activity** - List of the available cities with open parking lots
    - **Make reservation activity** - Reservation creation section
    - **Confirm reservation activity** - Information about the made reservation
    - **My reservations activity** - List of the user's made reservations
    
* ##### Local SQLite database back end implementation
* ##### Landscape mode for wide screens

#### Technologies
The application was developed in the **Android Studio IDE**, using the **Java** programming language and OOP principles.

Several third-party libraries have been used in order to implement the UI design of the application as well as some other features.

* ###### [Material Design](https://material.io/develop/android)
* ###### [QRGenerator](https://github.com/androidmads/QRGenerator)


---
## 4. Use
This section is a quick guide into using the application and all of its features. 

### 4.1 Create Account
New users need to create a free account in order to make parking reservations.

This is done by simply creating a **unique username** and password and you are a part of the Parks & Recs team! 🎊🎉 

### 4.2 Log in
Existing users log in by entering their respective username and password.

Their account remains logged in even after the application is closed.
If a user wants to log out of their account, they simply press the \
 **@icon-sign-out Log out** button.

### 4.3 Create Reservation
A reservation is created by following these few simple steps:

1. **Pick a city** - Select the city in which you want to reserve a parking spot and press the **[+]** button.
2. **Pick a date and time** - Every parking spot is reserved in 1 hour slots.
3. **Pick a parking lot** - After picking your desired date and time, choose from a list of available parking lots and press **Reserve**.
4. **That's it!** - You have successfully made a reservation in your desired parking spot. \
If you want navigation to it, just press **Navigate**. \
A QR-code is also generated if you want to share your parking spot location with your friends.

### 4.4 My Reservations
Each user can create up to **3 reservations** at a time.

In order to see all your reservations, just press on the @icon-calendar button in the toolbar. \
Here you can see information about your reservations, as well as navigate to them, remove them (if you have already parked) and see the QR code.


## Summary
If you like this app, go ahead and install it and tell me what you think.

This was a fun project to make. I learned a lot in doing so and hope to make even cooler apps in the future.
