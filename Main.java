import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            SimpleBlockchain blockchain = new SimpleBlockchain(); // Initialize blockchain
            Customer customer = gatherCustomerDetails(scanner); // Collect customer details

            int choice;
            do {
                // Display the menu
                System.out.println("\n===== User Menu =====");
                System.out.println("1. Select MySQL");
                System.out.println("2. Select Mongo");
                System.out.println("3. Select Blockchain");
                System.out.println("4. Select Redis");
                System.out.println("5. Exit");
                System.out.print("Please choose an option (1-5): ");

                // Read and validate the user's choice
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    scanner.next(); // Clear invalid input
                }
                choice = scanner.nextInt();

                // Perform action based on the user's choice
                switch (choice) {
                    case 1:
                        handleMysql(customer);
                        break;

                    case 2:
                        handleMongo(customer);
                        break;

                    case 3:
                        handleBlockchain(customer, blockchain);
                        break;

                    case 4:
                        handleRedis(customer);
                        break;

                    case 5:
                        System.out.println("Exiting the program. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please choose a valid option.");
                }

            } while (choice != 5);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static Customer gatherCustomerDetails(Scanner scanner) {
        Customer customer = new Customer();
        System.out.println("Enter Customer ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input for ID. Please enter a valid number.");
            scanner.next(); // Clear invalid input
        }
        customer.setId(scanner.nextInt());
        scanner.nextLine(); // Clear the buffer

        System.out.println("Enter Customer Name:");
        customer.setLastName(scanner.nextLine());

        System.out.println("Enter Customer Email:");
        customer.setEmail(scanner.nextLine());

        return customer;
    }

    private static void handleMysql(Customer customer) {
        System.out.println("Welcome to MySQL!");
        try {
            MySQLCrud mysqlCRUD = new MySQLCrud();
            System.out.println("Inserting a Customer in MySQL");
            mysqlCRUD.insertCustomer(customer);
            System.out.println("Updating a Customer in MySQL");
            mysqlCRUD.updateCustomer(123,"Smith");
            System.out.println("Reading a Customer from MySQL");
            mysqlCRUD.readCustomer(customer.getId());
            System.out.println("Deleting a Customer from MySQL");
            mysqlCRUD.deleteCustomer(customer.getId());
        } catch (Exception e) {
            System.out.println("Error with MySQL operations: " + e.getMessage());
        }
    }

    private static void handleMongo(Customer customer) {
        System.out.println("Welcome to MongoDB!");
        try (MongoCRUD mongoCRUD = new MongoCRUD()) {
            System.out.println("Inserting a Customer into MongoDB");
            mongoCRUD.insertCustomer(customer);
            System.out.println("Updating a Customer in MongoDB");
            mongoCRUD.updateCustomer(customer);
            System.out.println("Reading a Customer from MongoDB");
            mongoCRUD.readCustomer(customer.getId());
            System.out.println("Deleting a Customer from MongoDB");
            mongoCRUD.deleteCustomer(customer.getId());
        } catch (Exception e) {
            System.out.println("Error with MongoDB operations: " + e.getMessage());
        }
    }

    private static void handleBlockchain(Customer customer, SimpleBlockchain blockchain) {
        System.out.println("Welcome to Blockchain!");
        try {
            blockchain.addBlock("Customer ID: " + customer.getId() + ", Name: " + customer.getName());
            blockchain.printBlockchain(); // Display blockchain details
        } catch (Exception e) {
            System.out.println("Error with Blockchain operations: " + e.getMessage());
        }
    }

    private static void handleRedis(Customer customer) {
        System.out.println("Welcome to Redis!");
        try (RedisCRUD redisCRUD = new RedisCRUD()) {
            System.out.println("Inserting a Customer into Redis");
            redisCRUD.insertCustomer(customer);
            System.out.println("Updating a Customer in Redis");
            redisCRUD.updateCustomer(customer);
            System.out.println("Reading a Customer from Redis");
            redisCRUD.readCustomer(customer.getId());
            System.out.println("Deleting a Customer from Redis");
            redisCRUD.deleteCustomer(customer.getId());
        } catch (Exception e) {
            System.out.println("Error with Redis operations: " + e.getMessage());
        }
    }
}
