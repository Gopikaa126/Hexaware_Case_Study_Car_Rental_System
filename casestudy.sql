create database carrental;
use carrental;

CREATE TABLE Customer (CustomerID INT PRIMARY KEY AUTO_INCREMENT,FirstName VARCHAR(100) NOT NULL,LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,PhoneNumber VARCHAR(15),Address VARCHAR(255),Username VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,RegistrationDate date);
    alter table customer auto_increment=500;
    
CREATE TABLE Vehicle (VehicleID INT PRIMARY KEY AUTO_INCREMENT,Model VARCHAR(100) NOT NULL,Make VARCHAR(100),Year INT,Color VARCHAR(50),
    RegistrationNumber VARCHAR(100) NOT NULL UNIQUE,Availability BOOLEAN DEFAULT TRUE,DailyRate DECIMAL(10, 2));
alter table customer auto_increment=200;
    
CREATE TABLE Reservation (ReservationID INT PRIMARY KEY AUTO_INCREMENT,CustomerID INT,VehicleID INT,StartDate TIMESTAMP,EndDate TIMESTAMP,
    TotalCost DECIMAL(10, 2) NOT NULL,Status VARCHAR(50) DEFAULT 'pending',FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID),
    FOREIGN KEY (VehicleID) REFERENCES Vehicle(VehicleID));
alter table customer auto_increment=300;

CREATE TABLE Admin (AdminID INT PRIMARY KEY AUTO_INCREMENT,FirstName VARCHAR(100) NOT NULL,LastName VARCHAR(100) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,PhoneNumber VARCHAR(15),Username VARCHAR(100) NOT NULL UNIQUE,Password VARCHAR(255) NOT NULL,
    Role VARCHAR(50) NOT NULL,JoinDate date);
alter table customer auto_increment=100;