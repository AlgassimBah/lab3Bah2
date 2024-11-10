import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCrud {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/School";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Warriors30#";

    private Connection connection = null;

    public MySQLCrud() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to MySQL Database");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public void insertCustomer(Customer customer) {
        String sql = "INSERT INTO customer (id, firstName, lastName, age, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setInt(4, 20);  // Assuming age is 20 as hardcoded in original
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Customer inserted");
        } catch (SQLException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
    }

    public Customer readCustomer(int customerId) {
        String sql = "SELECT id, firstName, lastName, email FROM customer WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        20,  // Assuming age is 20 as hardcoded in original
                        resultSet.getString("email")
                );
            } else {
                System.out.println("Customer with ID " + customerId + " not found");
            }
        } catch (SQLException e) {
            System.out.println("Error reading customer: " + e.getMessage());
        }
        return null;
    }

    public void updateCustomer(int id, String newFirstName) {
        String sql = "UPDATE customer SET firstName = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newFirstName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Customer updated");
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
            System.out.println("Customer deleted");
        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT id, firstName, lastName, age, email FROM customer";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                customers.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("age"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving all customers: " + e.getMessage());
        }
        return customers;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.out.println("Error closing the database connection: " + e.getMessage());
            }
        }
    }
}
