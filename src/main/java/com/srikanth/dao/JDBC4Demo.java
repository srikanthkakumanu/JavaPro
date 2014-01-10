package com.srikanth.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.xa.PGXADataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBC4Demo {

	Logger logger = LoggerFactory.getLogger(JDBC4Demo.class);
	private Connection connection = null;
	PGXADataSource ds = null;
	Context ctx = null;
	private AtomicInteger idCounter = new AtomicInteger();
	
	
	public static void main(String[] args) {
		
		JDBC4Demo demo = new JDBC4Demo();
		
		Customer customer = new Customer();
		customer.setFirstName("Srikanth");
		customer.setLastName("Kakumanu");
		customer.setState("TamilNadu");
		customer.setStreet("Kumaran Kudil M");
		customer.setCity("Chennai");
		customer.setZip("600097");
		customer.setCountry("India");
		
		demo.createCustomer(customer);

		Customer customer1 = new Customer();
		customer1.setFirstName("Srikanth");
		customer1.setLastName("Kakumanu");
		customer1.setState("Andhra Pradesh");
		customer1.setStreet("Ratnam Lodge Road");
		customer1.setCity("Tenali");
		customer1.setZip("522201");
		customer1.setCountry("India");
		
		demo.createCustomer(customer1);
		
		Customer customer11 = new Customer();
		customer11.setId(1);
		customer11.setFirstName("Srikanth");
		customer11.setLastName("Kakumanu");
		customer11.setState("Andhra Pradesh");
		customer11.setStreet("Bharath Nagar Colony, Moosa Pet");
		customer11.setCity("Hyderabad");
		customer11.setZip("500017");
		customer11.setCountry("India");
		
		demo.updateCustomer(customer11);
		
//		demo.getAllCustomers();
//		demo.getCustomerById(1);

	}
	/**
	 * Returns connection pool, XA transaction aware data source
	 * @return org.postgresql.xa.PGXADataSource
	 */
	private PGXADataSource getPGXADataSource() {
		
		if(ds == null) {
			ds = new PGXADataSource();
			ds.setServerName("localhost");
			ds.setDatabaseName("SRIKANTH_DB");
			ds.setUser("srikanth");
			ds.setPassword("srikanth");
		}
		return ds;
	}
	/**
	 * Binds PGXADataSource object to a name in JNDI Context
	 * @return javax.naming.Context
	 */
	private Context getNamingContext() {

		if(ctx == null) {
			try {
				ds = getPGXADataSource();
				ctx = new InitialContext();
				ctx.bind("jdbc/srikanthdb", ds);
			} catch (NamingException e) {
				logger.error("Error occurred while initializing Context: ", e);
				e.printStackTrace();
			}
		}
		return ctx;
	}
	/**
	 * Returns standard JDBC DB Connection object
	 * @return java.sql.Connection
	 */
	private Connection getDBConnection() {
		
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SRIKANTH_DB?user=srikanth&password=srikanth");
				
			} catch (SQLException e) {
				logger.error("Error occurred while getting Connection: ", e);
				e.printStackTrace();
			}
		}
		return connection;
	}
	/**
	 * Returns JDBC Connection obtained through PGXADataSource
	 * @return java.sql.Connection
	 */
	private Connection getDBConnectionWithDataSource() {
		
		if(connection == null) {
			try {
				ds = getPGXADataSource();
				connection = ds.getConnection();
			} catch (SQLException e) {
				logger.error("Error occurred while getting Connection via DataSource: ", e);
				e.printStackTrace();
			}
		}
		return connection;
	}
	/**
	 * Returns JDBC Connection obtained hrough JNDI Context and PGXADataSource
	 * @return java.sql.Connection
	 */
	private Connection getDBConnectionWithContext() {
		
		if(connection == null) {
			try {
				ctx = getNamingContext();
				ds = (PGXADataSource) ctx.lookup("jdbc/srikanthdb");
				connection = ds.getConnection();
				
			} catch (SQLException | NamingException e) {
				logger.error("Error occurred while getting Connection via Context & PGXADataSource: ", e);
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	/**
	 * Creates new Customer
	 * @param customer
	 */
	public void createCustomer(Customer customer) {
		String sql = "INSERT INTO customer(id, firstname, lastname, street, city, zip, state, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		connection = getConnection();

		try {
			customer.setId(idCounter.incrementAndGet());
			PreparedStatement prepareStmt = connection.prepareCall(sql);
			prepareStmt.setInt(1, customer.getId());
			prepareStmt.setString(2,customer.getFirstName());
			prepareStmt.setString(3, customer.getLastName());
			prepareStmt.setString(4, customer.getStreet());
			prepareStmt.setString(5, customer.getCity());
			prepareStmt.setString(6, customer.getZip());
			prepareStmt.setString(7, customer.getState());
			prepareStmt.setString(8, customer.getCountry());
			
			int result = prepareStmt.executeUpdate();
			logger.info("Customer " + customer.getId() + " created Successfully. Result is " + result);
		} catch (SQLException e) {
			logger.error("Error occurred while creating customer: ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Updates Customer
	 * @param customer
	 */
	public void updateCustomer(Customer customer) {
		String sql = "UPDATE customer SET firstname=?, lastname=?, street=?, city=?, state=?,zip=?, country=? WHERE id=?";
		connection = getConnection();

		try {
			PreparedStatement prepareStmt = connection.prepareCall(sql);
			prepareStmt.setString(1, customer.getFirstName());
			prepareStmt.setString(2, customer.getLastName());
			prepareStmt.setString(3, customer.getStreet());
			prepareStmt.setString(4, customer.getCity());
			prepareStmt.setString(5, customer.getState());
			prepareStmt.setString(6, customer.getZip());
			prepareStmt.setString(7, customer.getCountry());
			prepareStmt.setInt(8, customer.getId());
			int result = prepareStmt.executeUpdate();
			logger.info("Customer " + customer.getId() + " updated Successfully. Result is " + result);
		} catch (SQLException e) {
			logger.error("Error occurred while updating customer: ", e);
			e.printStackTrace();
		}
	}
	/**
	 * Deletes Customer
	 * @param id
	 */
	public void deleteCustomer(int id) {
		String sql = "DELETE FROM customer WHERE id=?";;
		connection = getConnection();

		try {
			PreparedStatement prepareStmt = connection.prepareCall(sql);
			prepareStmt.setInt(1, id);
			int result = prepareStmt.executeUpdate();
			logger.info("Customer " + id + " deleted Successfully. Result is " + result);
		} catch (SQLException e) {
			logger.error("Error occurred while deleting customer: ", e);
			e.printStackTrace();
		}
}
	/**
	 * Returns all Customers
	 * @return customers
	 */
	public List<Customer> getAllCustomers() {
		String sql = " select * from customer";
		ArrayList<Customer> customers = new ArrayList<>(); 
		Customer customer = null;
		connection = getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setFirstName(result.getString("firstname"));
				customer.setLastName(result.getString("lastname"));
				customer.setStreet(result.getString("street"));
				customer.setCity(result.getString("city"));
				customer.setState(result.getString("state"));
				customer.setZip(result.getString("zip"));
				customer.setCountry(result.getString("country"));
				customers.add(customer);
			}
			logger.info("Printing All Customers");
			for (Customer cust : customers) {
				logger.info(cust.toString());
			}
		} catch (SQLException e) {
			logger.error("Error occurred while retrieving customers: ", e);
			e.printStackTrace();
		}
		return customers;
	}
	/**
	 * Retrieves Customer based on Id
	 * @param id
	 * @return Customer
	 */
	public Customer getCustomerById(int id) {
		String sql = " select * from customer where id=" + id;
		Customer customer = null;
		connection = getConnection();

		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			
			while(result.next()) {
				customer = new Customer();
				customer.setId(result.getInt("id"));
				customer.setFirstName(result.getString("firstname"));
				customer.setLastName(result.getString("lastname"));
				customer.setStreet(result.getString("street"));
				customer.setCity(result.getString("city"));
				customer.setState(result.getString("state"));
				customer.setZip(result.getString("zip"));
				customer.setCountry(result.getString("country"));
			}
			logger.info("Printing Customer Information..");
			logger.info(customer.toString());
		} catch (SQLException e) {
			logger.error("Error occurred while retrieving customer: " + id, e);
			e.printStackTrace();
		}
		return customer;
	}
	public Connection getConnection() {
//		connection = getDBConnection();
//		connection = getDBConnectionWithDataSource();
//		connection = getDBConnectionWithContext();
		return connection;
	}
}
