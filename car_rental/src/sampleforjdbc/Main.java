package sampleforjdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import dao.ICarLeaseRepository;
import dao.ICarLeaseRepositoryImpl;
import entity.Vehicle;
import entity.Customer;
import entity.Lease;
import entity.Payment;
import util.DatabaseConnectionUtil;
import java.util.Date;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            CarLeaseMenu menu = new CarLeaseMenu();
            menu.run(connection);
            connection.close();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }   
}

class CarLeaseMenu {
    public ICarLeaseRepository repoImp1;
    public Scanner sc;
    
    //constructor
    public CarLeaseMenu() {
        this.repoImp1 = new ICarLeaseRepositoryImpl();
        this.sc = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\nCar Rental System Menu:");
        System.out.println("1. Add a New Car");
        System.out.println("2. Remove a Car");
        System.out.println("3. List Available Cars");
        System.out.println("4. List Rented Cars");
        System.out.println("5. Find Car by ID");
        System.out.println("6. Add a New Customer");
        System.out.println("7. Remove a Customer");
        System.out.println("8. List Customers");
        System.out.println("9. Find Customer by ID");
        System.out.println("10. Create a Lease");
        System.out.println("11. Return a Leased Car");
        System.out.println("12. List Active Leases");
        System.out.println("13. List Lease History");
        System.out.println("14. Record Payment");
        System.out.println("15. Exit");
    }

    public void run(Connection connection) {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addCar();
                    break;
                case "2":
                    removeCar();
                    break;
                case "3":
                    listAvailableCars();
                    break;
                case "4":
                    listRentedCars();
                    break;
                case "5":
                    findCarById();
                    break;
                case "6":
                    addCustomer();
                    break;
                case "7":
                    removeCustomer();
                    break;
                case "8":
                    listCustomers();
                    break;
                case "9":
                    findCustomerById();
                    break;
                case "10":
                    createLease();
                    break;
                case "11":
                    returnCar();
                    break;
                case "12":
                    listActiveLeases();
                    break;
                case "13":
                    listLeaseHistory();
                    break;
                case "14":
                    recordPayment();
                    break;
                case "15":
                    System.out.println("\nExiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    private void addCar() {
        System.out.print("Enter Company Name: ");
        String make = sc.nextLine();
        System.out.print("Enter Model: ");
        String model = sc.nextLine();
        System.out.print("Enter Year: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Daily Rate: ");
        double dailyRate = Double.parseDouble(sc.nextLine());
        System.out.print("Enter Status (available, notAvailable): ");
        String available = sc.nextLine();
        System.out.print("Enter Passenger Capacity: ");
        int passengerCapacity = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Engine Capacity: ");
        double engineCapacity = Double.parseDouble(sc.nextLine());
        System.out.println("Car is Added !");

        Vehicle vehicle = new Vehicle(make, model, year, dailyRate, available, passengerCapacity, engineCapacity);
        repoImp1.addCar(vehicle);
    }
  
    private void removeCar() {
        System.out.print("Enter Vehicle ID: ");
        int vehicleId = Integer.parseInt(sc.nextLine());
        try {
            repoImp1.removeCar(vehicleId);
        } catch (CarNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listAvailableCars() {
        var availableCars = repoImp1.listAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("Available Cars Not Found!");
        } else {
            for (Vehicle car : availableCars) {
                System.out.println("Vehicle ID: " + car.getVehicleID());
                System.out.println("Make: " + car.getMake());
                System.out.println("Model: " + car.getModel());
                System.out.println("Year: " + car.getYear());
                System.out.println("Daily Rate: " + car.getDailyRate());
                System.out.println("Status: " + car.getStatus());
                System.out.println("Passenger Capacity: " + car.getPassengerCapacity());
                System.out.println("Engine Capacity: " + car.getEngineCapacity());
                System.out.println();
            }
        }
    }

    private void listRentedCars() {
        var rentedCars = repoImp1.listRentedCars();
        if (rentedCars.isEmpty()) {
            System.out.println("No Rented Cars Found!");
        } else {
            for (Vehicle car : rentedCars) {
                System.out.println("Vehicle ID: " + car.getVehicleID());
                System.out.println("Make: " + car.getMake());
                System.out.println("Model: " + car.getModel());
                System.out.println("Year: " + car.getYear());
                System.out.println("Daily Rate: " + car.getDailyRate());
                System.out.println("Status: " + car.getStatus());
                System.out.println("Passenger Capacity: " + car.getPassengerCapacity());
                System.out.println("Engine Capacity: " + car.getEngineCapacity());
                System.out.println();
            }
        }
    }
  
    private void findCarById() {
        System.out.print("Enter Vehicle ID to find: ");
        int vehicleId = Integer.parseInt(sc.nextLine());
        try {
            Vehicle car = repoImp1.findCarById(vehicleId);
            if (car != null) {
                System.out.println("Vehicle ID: " + car.getVehicleID());
                System.out.println("Make: " + car.getMake());
                System.out.println("Model: " + car.getModel());
                System.out.println("Year: " + car.getYear());
                System.out.println("Daily Rate: " + car.getDailyRate());
                System.out.println("Available: " + car.getStatus());
                System.out.println("Passenger Capacity: " + car.getPassengerCapacity());
                System.out.println("Engine Capacity: " + car.getEngineCapacity());
                System.out.println();
            } else {
                System.out.println("Car Not Found!!");
            }
        } catch (CarNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void addCustomer() {
        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();
        System.out.println("Customer is added !");
        
        Customer customer = new Customer(fname, lname, email, phone);
        repoImp1.addCustomer(customer);
    }

  private void removeCustomer(){
        System.out.print("Enter Customer ID: ");
        int customerId = Integer.parseInt(sc.nextLine());
        try {
            repoImp1.removeCustomer(customerId);
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    private void listCustomers() {
        var customers = repoImp1.listCustomers();
        if (!customers.isEmpty()) {
            System.out.println("\nList of Customers:");
            for (Customer customer : customers) {
                System.out.println("Customer ID: " + customer.getCustomerID());
                System.out.println("First Name: " + customer.getFirstName());
                System.out.println("Last Name: " + customer.getLastName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Phone Number: " + customer.getPhoneNumber());
                System.out.println();
            }
        } else {
            System.out.println("No Customers Found!!");
        }
    }
  
    private void findCustomerById() {
        System.out.print("Enter Customer ID to find: ");
        int customerId = Integer.parseInt(sc.nextLine());
        try {
            Customer customer = repoImp1.findCustomerById(customerId);
            if (customer != null) {
            	System.out.println("Customer Found:");
                System.out.println("Customer ID: " + customer.getCustomerID());
                System.out.println("First Name: " + customer.getFirstName());
                System.out.println("Last Name: " + customer.getLastName());
                System.out.println("Email: " + customer.getEmail());
                System.out.println("Phone Number: " + customer.getPhoneNumber());
            } else {
                System.out.println("Customer Not Found!!");
            }
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
   
    private void createLease() {
        try {
            System.out.print("Enter Customer ID: ");
            int customerId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Vehicle ID: ");
            int vehicleId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Start Date (YYYY-MM-DD): ");
            String startDateString = sc.nextLine();
            System.out.print("Enter End Date (YYYY-MM-DD): ");
            String endDateString = sc.nextLine();
            System.out.print("Enter Lease Type (Daily Lease/Monthly Lease): ");
            String type = sc.nextLine().trim();

            if (type.equalsIgnoreCase("Daily Lease")) {
                type = "DailyLease";
            } else if (type.equalsIgnoreCase("Monthly Lease")) {
                type = "MonthlyLease";
            } else {
                System.out.println("Error: Invalid lease type. Please enter 'Daily Lease' or 'Monthly Lease'.");
                return;
            }
            
            Date startDate = java.sql.Date.valueOf(startDateString);
            Date endDate = java.sql.Date.valueOf(endDateString);
            
            repoImp1.createLease(customerId, vehicleId, startDate, endDate,type);
            
        } catch (CustomerNotFoundException e) {
            System.out.println("Error: Customer not found. " + e.getMessage());
        } catch (CarNotFoundException e) {
            System.out.println("Error: Car not found. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        }
    }
    
   private void returnCar() {
        System.out.print("Enter Lease ID: ");
        int leaseId = Integer.parseInt(sc.nextLine());
        try {
            repoImp1.returnCar(leaseId);
        } catch (LeaseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }	
      
    private void listActiveLeases() {
        try {
            var activeLeases = repoImp1.listActiveLeases(); 
            for (Lease lease : activeLeases) {
                System.out.println("Lease ID: " + lease.getLeaseID());
                System.out.println("Customer ID: " + lease.getCustomerID());
                System.out.println("Vehicle ID: " + lease.getVehicleID());
                System.out.println("Start Date: " + lease.getStartDate());
                System.out.println("End Date: " + lease.getEndDate());
                System.out.println();
            }
        } catch (LeaseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

   private void listLeaseHistory() {
        try {
            var leaseHistory = repoImp1.listLeaseHistory(); 
            for (Lease lease : leaseHistory) {
                System.out.println("Lease ID: " + lease.getLeaseID());
                System.out.println("Customer ID: " + lease.getCustomerID());
                System.out.println("Vehicle ID: " + lease.getVehicleID());
                System.out.println("Start Date: " + lease.getStartDate());
                System.out.println("End Date: " + lease.getEndDate());
                System.out.println();
            }
        } catch (LeaseNotFoundException e) {
            System.out.println(e.getMessage()); 
        }
    }

    
    private void recordPayment() {
        System.out.print("Enter Lease ID: ");
        int leaseId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Payment Amount: ");
        double amount = Double.parseDouble(sc.nextLine());
        System.out.print("Enter Payment Date (YYYY-MM-DD): ");
        String paymentDateString = sc.nextLine();
        Date paymentDate = java.sql.Date.valueOf(paymentDateString);
        
        repoImp1.recordPayment(leaseId, amount, paymentDate);
        System.out.println("Payment is recorded !");
    }
}
