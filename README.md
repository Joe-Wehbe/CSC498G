# MyCar :car:
Mobile application that keeps track of your vehicle(s) fluids. 

# Contributors :raising_hand_man:
<a href="https://github.com/Joe-Wehbe">Joe Wehbe</a>

# Project Description :page_with_curl: 

First, a signin page will be shown where users can enter their email and password to access the application directly. In case they do not have an account, they will be asked to enter their first name, last name, country, email, and password. All this information will be stored in MySQL database (table users) and you can access it using phpMyAdmin under the 'Database' folder in the repository. Once complete, a list of the user's vehicles will be displayed (if they have any), and they can check each vehicle's current amount of fluids remaining. Users have the option to delete vehicles, or add new vehicles, they will be asked to enter the: brand, model, color, plate. This information will be stored in the table 'cars' of the database. When a new distance is driven, users will have to enter it in miles, and the application will update the remaining amount of fluids accordingly.
      
# App Preview :iphone:
![image](https://user-images.githubusercontent.com/102875229/209527996-954449ea-fe45-4767-b18c-b4d701f41934.png)
![image](https://user-images.githubusercontent.com/102875229/209528710-240a9220-9f75-4ba6-9a92-0a73f9f2b8d8.png)
![image](https://user-images.githubusercontent.com/102875229/209528293-a92cac30-8c58-4eb8-8cea-278d7586f88a.png)
![image](https://user-images.githubusercontent.com/102875229/209528429-61e3de0f-7cd4-450a-a540-e2d04d0d7036.png)
![image](https://user-images.githubusercontent.com/102875229/209529553-41608ad4-64b7-4c9c-a4ce-f78df25aad81.png)
![image](https://user-images.githubusercontent.com/102875229/209529803-0ff8b60f-c61f-4fc8-b19c-ce49ccfb57e3.png)

# Stacks :books:
<img src="https://img.shields.io/badge/-PHP-232531?logo=php&logoColor=white&style=for-the-badge" ></img>
<img src="https://img.shields.io/badge/-java-5382a1?logo=&logoColor=white&style=for-the-badge" ></img>
<img src="https://img.shields.io/badge/-MYSQL-00758f?logo=mysql&logoColor=white&style=for-the-badge" ></img>
<img src="https://img.shields.io/badge/-ANDROID%20STUDIO-3DDC84?logo=android-studio&logoColor=white&style=for-the-badge" ></img>

# Database :bar_chart:
You can download the database from the repository under the 'Database' folder.

# ER Diagram :link:
![image](https://user-images.githubusercontent.com/102875229/207974550-3ca3fe1e-488d-41bf-b088-71dfc135a4a0.png)


# How to Run the Application
## What to Download
Download Android Studio
```
https://developer.android.com/studio
```

Download XAMPP
```
https://www.apachefriends.org/download.html
```

Download the Files in my Repository
> Click on: 'Code', then 'Download ZIP'.
\

## Set up the Project
Import the project to Android Studio
> From android studio home, click on 'Open', locate and choose 'MyCar' folder, then click 'OK'
> If you have a project running click on 'File', 'New', 'Import Project...', locate and choose MyCar, then click 'OK'

Change the IPv4 Address
> Open the command prompt and type
```
ipconfig
```
> Copy the IPv4 Address and replace the old one with it in every activity where a URL is used.

Move the files of the 'Backend' folder to htdocs
> Go to XAMPP/htdocs on your device, create a new folder name it MyCar, and move into it the files under the downloaded 'Backend' folder.

Run XAMPP server
> Start Apache
> Start MySQL
> Click on MySQL Admin, which will open phpMyAdmin.









