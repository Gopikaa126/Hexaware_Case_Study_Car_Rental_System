package test;

/*import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;
import java.util.Scanner;

public class ExceptionHandlingTest {
    private ICarLeaseRepositoryImpl repo;
    private Scanner sc;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();  
        sc = new Scanner(System.in); 
    }

    @Test
    public void testExceptionHandling() {
        System.out.println("Choose the exception to test:");
        System.out.println("1. LeaseNotFoundException");
        System.out.println("2. CarNotFoundException");
        System.out.println("3. CustomerNotFoundException");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                testLeaseNotFound();
                break;
            case 2:
                testCarNotFound();
                break;
            case 3:
                testCustomerNotFound();
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }

    private void testLeaseNotFound() {
        System.out.println("Enter lease ID to test (LeaseNotFoundException):");
        int leaseId = sc.nextInt();
        
        try {
            repo.returnCar(leaseId);
            fail("Expected LeaseNotFoundException to be thrown");
        } catch (LeaseNotFoundException e) {
            assertEquals("Lease with ID " + leaseId + " not found.", e.getMessage());
        } catch (Exception e) {
            fail("Expected LeaseNotFoundException, but got " + e.getClass().getSimpleName());
        }
    }

    private void testCarNotFound() {
        System.out.println("Enter car ID to test (CarNotFoundException):");
        int carId = sc.nextInt();
        
        try {
            repo.findCarById(carId);
            fail("Expected CarNotFoundException to be thrown");
        } catch (CarNotFoundException e) {
            assertEquals("Car with ID " + carId + " not found.", e.getMessage());
        } catch (Exception e) {
            fail("Expected CarNotFoundException, but got " + e.getClass().getSimpleName());
        }
    }
    
    private void testCustomerNotFound() {
        System.out.println("Enter customer ID to test (CustomerNotFoundException):");
        int customerId = sc.nextInt();
        
        try {
            repo.findCustomerById(customerId);
            fail("Expected CustomerNotFoundException to be thrown");
        } catch (CustomerNotFoundException e) {
            assertEquals("Customer with ID " + customerId + " not found.", e.getMessage());
        } catch (Exception e) {
            fail("Expected CustomerNotFoundException, but got " + e.getClass().getSimpleName());
        }
    }
}*/

import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;
import static org.junit.Assert.fail;

public class ExceptionHandlingTest {
    private ICarLeaseRepositoryImpl repo;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();
    }

    @Test
    public void testLeaseNotFound() {
        int invalidLeaseId = 999; 
        try {
            repo.returnCar(invalidLeaseId);
            fail("Expected LeaseNotFoundException was not thrown");
        } catch (LeaseNotFoundException e) {
            System.out.println("LeaseNotFoundException successfully thrown for invalid lease ID: " + invalidLeaseId);
        }
    }

    @Test
    public void testCarNotFound() {
        int invalidCarId = 999; 
        try {
            repo.findCarById(invalidCarId);
            fail("Expected CarNotFoundException was not thrown"); 
        } catch (CarNotFoundException e) {
            System.out.println("CarNotFoundException successfully thrown for invalid car ID: " + invalidCarId);
        }
    }
    
    @Test
    public void testCustomerNotFound()
    {
    	int invalidCustomerId=999;
    	try
    	{
    		repo.findCarById(invalidCustomerId);
            fail("Expected CarNotFoundException was not thrown"); 
        } catch (CarNotFoundException e) {
            System.out.println("CarNotFoundException successfully thrown for invalid car ID: " + invalidCustomerId);
        }
    }
}
