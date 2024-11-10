package dao;

import entity.Vehicle;
import entity.Customer;
import entity.Lease;
import java.util.Date;
import java.util.List;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;


public interface ICarLeaseRepository {
	//methods
    void addCar(Vehicle car);
    void removeCar(int carID) throws CarNotFoundException;
    List<Vehicle> listAvailableCars();
    List<Vehicle> listRentedCars();
    Vehicle findCarById(int carID) throws CarNotFoundException; 
    
    void addCustomer(Customer customer);
    void removeCustomer(int customerID) throws CustomerNotFoundException; 
    List<Customer> listCustomers();
    Customer findCustomerById(int customerID) throws CustomerNotFoundException;
    
    Lease createLease(int customerID, int carID, Date startDate, Date endDate,String type) throws CarNotFoundException, CustomerNotFoundException;
    void returnCar(int leaseID) throws LeaseNotFoundException;
    List<Lease> listActiveLeases() throws LeaseNotFoundException;
    List<Lease> listLeaseHistory() throws LeaseNotFoundException;
    
    void recordPayment(int leaseID, double amount, Date paymentDate);
    
}


