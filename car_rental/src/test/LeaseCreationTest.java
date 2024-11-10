package test;

/*import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import entity.Lease;
import exception.*;
import java.sql.Date;
import java.util.Scanner;
import java.util.List;

public class LeaseCreationTest {
    private ICarLeaseRepositoryImpl repo;
    private int customerId;
    private int vehicleId;
    private Date startDate;
    private Date endDate;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();  

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer ID: ");
        customerId = sc.nextInt(); 

        System.out.print("Enter vehicle ID: ");
        vehicleId = sc.nextInt(); 

        System.out.print("Enter lease start date (YYYY-MM-DD): ");
        String start = sc.next();  
        startDate = Date.valueOf(start);  

        System.out.print("Enter lease end date (YYYY-MM-DD): ");
        String end = sc.next();  
        endDate = Date.valueOf(end);  
    }

    @Test
    public void testLeaseCreation() throws LeaseNotFoundException, CarNotFoundException, CustomerNotFoundException {
        Lease lease = new Lease();
        lease.setCustomerID(customerId);
        lease.setVehicleID(vehicleId);
        lease.setStartDate(startDate);
        lease.setEndDate(endDate);

        Lease isCreated = repo.createLease(lease.getCustomerID(), lease.getVehicleID(), lease.getStartDate(), lease.getEndDate());

        assertNotNull("Lease should be created successfully", isCreated);
    }
}
*/



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import entity.Lease;
import exception.*;
import java.sql.Date;

public class LeaseCreationTest {
    private ICarLeaseRepositoryImpl repo;
    private int customerId;
    private int vehicleId;
    private Date startDate;
    private Date endDate;
    private String type;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();  
        
        vehicleId = 2; 
        customerId = 103; 
        startDate = Date.valueOf("2024-10-21");
        endDate = Date.valueOf("2024-10-28");
        type="MonthlyLease";
    }
    
    @Test
    public void testLeaseCreation() throws LeaseNotFoundException, CarNotFoundException, CustomerNotFoundException {
        Lease lease = new Lease();
        lease.setCustomerID(customerId);
        lease.setVehicleID(vehicleId);
        lease.setStartDate(startDate);
        lease.setEndDate(endDate);
        lease.setType(type);

        Lease isCreated = repo.createLease(lease.getCustomerID(), lease.getVehicleID(), lease.getStartDate(), lease.getEndDate(),lease.getType());

        assertNotNull("Lease created successfully", isCreated);

    }
}