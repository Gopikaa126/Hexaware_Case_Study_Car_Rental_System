package test;

/*import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import entity.Vehicle;
import exception.CarNotFoundException;
import java.util.Scanner;

public class CarCreationTest {
	
    private ICarLeaseRepositoryImpl repo;
    private String make;
    private String model;
    private int year;
    private double dailyRate;
    private int passengerCapacity;
    private double engineCapacity;
    private String status;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();  
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter car make: ");
        make = sc.nextLine();

        System.out.println("Enter car model: ");
        model = sc.nextLine();

        System.out.println("Enter car year: ");
        year = sc.nextInt();

        System.out.println("Enter car daily rate: ");
        dailyRate = sc.nextDouble();

        System.out.println("Enter car passenger capacity: ");
        passengerCapacity = sc.nextInt();

        System.out.println("Enter car engine capacity: ");
        engineCapacity = sc.nextDouble();
        sc.nextLine(); // Consume the newline character

        System.out.println("Enter car status (Available/NotAvailable): ");
        status = sc.nextLine();
    }

    @Test
    public void testCarCreation() throws CarNotFoundException {
        Vehicle car = new Vehicle();
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setDailyRate(dailyRate);
        car.setPassengerCapacity(passengerCapacity);
        car.setEngineCapacity(engineCapacity);
        car.setStatus(status);
        
        repo.addCar(car);

        System.out.println("Car created successfully!");

       
    }
}*/



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import entity.Vehicle;
import exception.CarNotFoundException;

public class CarCreationTest {
	
    private ICarLeaseRepositoryImpl repo;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();  
    }

    @Test
    public void testCarCreation() throws CarNotFoundException {
    	
    	String make="Toyota";
        String model="Camry";
        int year=2020;
        double dailyRate=50.0;
        int passengerCapacity=5;
        double engineCapacity=2.5;
        String status="Available";
        Vehicle car = new Vehicle();
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setDailyRate(dailyRate);
        car.setPassengerCapacity(passengerCapacity);
        car.setEngineCapacity(engineCapacity);
        car.setStatus(status);
        
        repo.addCar(car);

        System.out.println("Car created successfully!");

       
    }
}

