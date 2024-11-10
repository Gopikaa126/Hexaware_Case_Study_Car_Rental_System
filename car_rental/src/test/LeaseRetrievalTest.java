package test;

/*import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import entity.Lease;
import exception.LeaseNotFoundException;
import java.util.List;
import java.util.Scanner;

public class LeaseRetrievalTest {
    private ICarLeaseRepositoryImpl repo;
    private Scanner sc;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();  
        sc = new Scanner(System.in);  
    }

    @Test
    public void testLeaseRetrieval() throws LeaseNotFoundException {
        System.out.println("Enter 1 for active leases or 2 for lease history:");
        int choice = sc.nextInt();
        
        List<Lease> leases;
        if (choice == 1) {
            leases = repo.listActiveLeases();  
        } else {
            leases = repo.listLeaseHistory();  
        }

        assertNotNull("Leases should be retrieved", leases);
        assertFalse("Lease list should not be empty", leases.isEmpty());
    }
}*/



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import dao.ICarLeaseRepositoryImpl;
import entity.Lease;
import exception.LeaseNotFoundException;
import java.util.List;

public class LeaseRetrievalTest {
    private ICarLeaseRepositoryImpl repo;

    @Before
    public void setUp() throws Exception {
        repo = new ICarLeaseRepositoryImpl();  
    }

    @Test
    public void testActiveLeaseRetrieval() throws LeaseNotFoundException {
        List<Lease> activeLeases = repo.listActiveLeases();  

        assertNotNull("Active leases should be retrieved", activeLeases);
        assertFalse("Active lease list should not be empty", activeLeases.isEmpty());

        System.out.println("Active Leases retrieved successfully:");
        for (Lease lease : activeLeases) {
            System.out.println("Lease ID: " + lease.getLeaseID() + ", Customer ID: " + lease.getCustomerID() + ", Vehicle ID: " + lease.getVehicleID());
        }
        System.out.println();
    }

    @Test
    public void testLeaseRetrieval() throws LeaseNotFoundException {
    	List<Lease> leaseHistory = repo.listLeaseHistory();  

        assertNotNull("Lease history should be retrieved", leaseHistory);
        assertFalse("Lease history should not be empty", leaseHistory.isEmpty());

        System.out.println("Lease History retrieved successfully:");
        for (Lease lease : leaseHistory) {
            System.out.println("Lease ID: " + lease.getLeaseID() + ", Customer ID: " + lease.getCustomerID() + ", Vehicle ID: " + lease.getVehicleID());
        }
        System.out.println();
    }
}


