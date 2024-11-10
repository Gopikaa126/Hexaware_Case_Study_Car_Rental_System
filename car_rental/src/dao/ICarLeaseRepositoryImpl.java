package dao;

import entity.Vehicle;
import entity.Customer;
import entity.Lease;
import util.DatabaseConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import exception.CarNotFoundException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;

public class ICarLeaseRepositoryImpl implements ICarLeaseRepository {
	    
	public ICarLeaseRepositoryImpl() {
		
	}
	
    @Override
    //method
    public void addCar(Vehicle car) {
        String sql = "INSERT INTO Vehicle (make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, car.getMake());
            pstmt.setString(2, car.getModel());
            pstmt.setInt(3, car.getYear());
            pstmt.setDouble(4, car.getDailyRate());
            pstmt.setString(5, car.getStatus());
            pstmt.setInt(6, car.getPassengerCapacity());
            pstmt.setDouble(7, car.getEngineCapacity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    @Override
    public void removeCar(int carID) throws CarNotFoundException {
        String checkLeaseSql = "SELECT COUNT(*) FROM Lease WHERE vehicleID = ?";
        String deleteCarSql = "DELETE FROM Vehicle WHERE vehicleID = ?";
        
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement checkPstmt = conn.prepareStatement(checkLeaseSql)) {
     
            checkPstmt.setInt(1, carID);
            ResultSet rs = checkPstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Error: Car with ID " + carID + " has associated leases and cannot be removed.");
                return;
            }
            
            try (PreparedStatement deletePstmt = conn.prepareStatement(deleteCarSql)) {
                deletePstmt.setInt(1, carID);
                int affectedRows = deletePstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new CarNotFoundException("Car with ID " + carID + " not found.");
                } else {
                    System.out.println("Car removed successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<Vehicle> listAvailableCars() {
        List<Vehicle> availableCars = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE status = 'available'";
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vehicle car = new Vehicle();
                car.setVehicleID(rs.getInt("vehicleID"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setDailyRate(rs.getDouble("dailyRate"));
                car.setStatus(rs.getString("status"));
                car.setPassengerCapacity(rs.getInt("passengerCapacity"));
                car.setEngineCapacity(rs.getDouble("engineCapacity"));
                availableCars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return availableCars;
    }

    @Override
    public List<Vehicle> listRentedCars() {
        List<Vehicle> rentedCars = new ArrayList<>();
        String sql = "SELECT * FROM Vehicle WHERE status = 'notAvailable'";
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vehicle car = new Vehicle();
                car.setVehicleID(rs.getInt("vehicleID"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setDailyRate(rs.getDouble("dailyRate"));
                car.setStatus(rs.getString("status"));
                car.setPassengerCapacity(rs.getInt("passengerCapacity"));
                car.setEngineCapacity(rs.getDouble("engineCapacity"));
                rentedCars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return rentedCars;
    }
    
    @Override
    public Vehicle findCarById(int carID) throws CarNotFoundException {
        String sql = "SELECT * FROM Vehicle WHERE vehicleID = ?";
        Vehicle car = null;
        try (Connection conn = DatabaseConnectionUtil.getConnection();
        	 PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, carID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                car = new Vehicle();
                car.setVehicleID(rs.getInt("vehicleID"));
                car.setMake(rs.getString("make"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setDailyRate(rs.getDouble("dailyRate"));
                car.setStatus(rs.getString("status"));
                car.setPassengerCapacity(rs.getInt("passengerCapacity"));
                car.setEngineCapacity(rs.getDouble("engineCapacity"));
            } else {
                throw new CarNotFoundException("Car with ID " + carID + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return car;
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getLastName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void removeCustomer(int customerID) throws CustomerNotFoundException {
        String checkLeaseSql = "SELECT COUNT(*) FROM Lease WHERE customerID = ?";
        String deleteCustomerSql = "DELETE FROM Customer WHERE customerID = ?";

        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement checkPstmt = conn.prepareStatement(checkLeaseSql)) {

            checkPstmt.setInt(1, customerID);
            ResultSet rs = checkPstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Error: Customer with ID " + customerID + " has associated leases and cannot be removed.");
                return;
            }

            try (PreparedStatement deletePstmt = conn.prepareStatement(deleteCustomerSql)) {
                deletePstmt.setInt(1, customerID);
                int affectedRows = deletePstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new CustomerNotFoundException("Customer with ID " + customerID + " not found.");
                } else {
                    System.out.println("Customer removed successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    } 

    @Override
    public List<Customer> listCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return customers;
    }
    
    @Override
    public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        Customer customer = null;
        try (Connection conn = DatabaseConnectionUtil.getConnection();
        	 PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
            } else {
                throw new CustomerNotFoundException("Customer with ID " + customerID + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return customer;
    }
    
    @Override
    public Lease createLease(int customerID,int carID,Date startDate,Date endDate,String type) throws CarNotFoundException,CustomerNotFoundException
    {
        findCarById(carID); 
        findCustomerById(customerID);  
        String sql = "INSERT INTO Lease (vehicleID, customerID, startDate, endDate, type) VALUES (?, ?, ?, ?, ?)";
        Lease lease = null;
        try (Connection conn = DatabaseConnectionUtil.getConnection();
           	 PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
        	pstmt.setInt(1, carID);
            pstmt.setInt(2, customerID);
            pstmt.setDate(3, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(4, new java.sql.Date(endDate.getTime()));
            pstmt.setString(5, type);  
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                lease = new Lease();
                lease.setLeaseID(rs.getInt(1));
                lease.setCustomerID(customerID);
                lease.setVehicleID(carID);
                lease.setStartDate(startDate);
                lease.setEndDate(endDate);
                lease.setType(type);  
                System.out.println("Lease created successfully!");
            }
        }
        catch(SQLException e)
        {
        	System.out.println("Error: "+e.getMessage());
        }
        return lease;
    }
    
    
    @Override
    public void returnCar(int leaseID) throws LeaseNotFoundException{
    	String sql="SELECT Lease.leaseID, Vehicle.make, Vehicle.model, Vehicle.year FROM Lease JOIN Vehicle ON Lease.vehicleID = Vehicle.vehicleID WHERE Lease.leaseID = ?";
    	try(Connection conn=DatabaseConnectionUtil.getConnection();
    			PreparedStatement pstmt=conn.prepareStatement(sql))
    	{
    		pstmt.setInt(1,leaseID);
    		ResultSet rs=pstmt.executeQuery();
    		if(!rs.next())
    		{
                throw new LeaseNotFoundException("Lease with ID " + leaseID + " not found.");
    		}
    		System.out.println("Car returned successfully for Lease ID: " + leaseID);
            System.out.println("Car Details:");
            System.out.println("Make: " + rs.getString("make"));
            System.out.println("Model: " + rs.getString("model"));
            System.out.println("Year: " + rs.getInt("year"));
    	}
    	catch(SQLException e)
    	{
    		System.out.println("Error: "+e.getMessage());
    	}
    }

    
    @Override
    public List<Lease> listActiveLeases() throws LeaseNotFoundException {
        List<Lease> activeLeases = new ArrayList<>();
        String sql = "SELECT * FROM Lease where startdate<=curdate() and enddate >=curdate()";
        		
        
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Lease lease = new Lease();
                lease.setLeaseID(rs.getInt("leaseID"));
                lease.setVehicleID(rs.getInt("vehicleID"));
                lease.setCustomerID(rs.getInt("customerID"));
                lease.setStartDate(rs.getDate("startDate"));
                lease.setEndDate(rs.getDate("endDate"));
                activeLeases.add(lease);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        if (activeLeases.isEmpty()) {
            throw new LeaseNotFoundException("No active leases found.");
        }
        
        return activeLeases;
    }

    
    @Override
    public List<Lease> listLeaseHistory() throws LeaseNotFoundException {
        List<Lease> leaseHistory = new ArrayList<>();
        String sql = "SELECT * FROM Lease";
        
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Lease lease = new Lease();
                lease.setLeaseID(rs.getInt("leaseID"));
                lease.setVehicleID(rs.getInt("vehicleID"));
                lease.setCustomerID(rs.getInt("customerID"));
                lease.setStartDate(rs.getDate("startDate"));
                lease.setEndDate(rs.getDate("endDate"));
                leaseHistory.add(lease);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        if (leaseHistory.isEmpty()) {
            throw new LeaseNotFoundException("No lease history found.");
        }
        
        return leaseHistory;
    }


    @Override
    public void recordPayment(int leaseID, double amount, Date paymentDate) {
        String sql = "INSERT INTO Payment (leaseID, amount, paymentDate) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnectionUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, leaseID);
            pstmt.setDouble(2, amount);
            pstmt.setDate(3, new java.sql.Date(paymentDate.getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
