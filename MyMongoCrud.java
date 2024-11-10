import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Updates;

public class MongoCRUD {

    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    public MongoCRUD() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            MongoDatabase database = mongoClient.getDatabase("your_database_name"); // Replace with actual database name
            collection = database.getCollection("customers");
            System.out.println("Connected to MongoDB");
        } catch (Exception e) {
            System.out.println("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    // Insert customer into MongoDB
    public void insertCustomer(Customer customer) {
        try {
            Document newCustomer = new Document("id", customer.getId())
                    .append("first_name", customer.getFirstName())
                    .append("last_name", customer.getLastName())
                    .append("email", customer.getEmail());
            collection.insertOne(newCustomer);
            System.out.println("Customer inserted into MongoDB");
        } catch (Exception e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
    }

    // Read customer from MongoDB
    public void readCustomer(int customerId) {
        try {
            Document query = new Document("id", customerId);
            FindIterable<Document> customers = collection.find(query);

            if (!customers.iterator().hasNext()) {
                System.out.println("Customer not found with ID: " + customerId);
            } else {
                for (Document customer : customers) {
                    System.out.println(customer.toJson());
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading customer: " + e.getMessage());
        }
    }

    // Update customer in MongoDB
    public void updateCustomer(Customer customer) {
        try {
            Document query = new Document("id", customer.getId());
            Bson updates = Updates.combine(
                    Updates.set("first_name", customer.getFirstName()),
                    Updates.set("last_name", customer.getLastName()),
                    Updates.set("email", customer.getEmail())
            );
            collection.updateOne(query, updates);
            System.out.println("Customer updated in MongoDB");
        } catch (Exception e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    // Delete customer from MongoDB
    public void deleteCustomer(int customerId) {
        try {
            Document query = new Document("id", customerId);
            collection.deleteOne(query);
            System.out.println("Customer deleted from MongoDB");
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
        }
    }

    // Close the MongoDB connection
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed");
        }
    }
}
