import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Define a Block class


// Define a Blockchain class


public class SimpleBlockchain {
    public static void main(String[] args) {
        // Create a new blockchain
        Blockchain blockchain = new Blockchain();
        Customer customer1 = new Customer(1234,"Ben", "Smith", 32, "bsmith@aol.com");
        Customer customer2 = new Customer(12345,"sim", "rick", 22, "rick@aol.com");
        Customer customer3 = new Customer(12346,"jon", "brown", 42, "jbrowm@aol.com");

        // Add some blocks to the blockchain
        blockchain.addBlock(customer1.toString());
        blockchain.addBlock(customer2.toString());
        blockchain.addBlock(customer3.toString());

        // Print the blockchain
        blockchain.printBlockchain();

    }
}