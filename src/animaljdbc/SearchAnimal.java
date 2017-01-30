package animaljdbc;
import static java.lang.System.*;
import java.sql.*;

/**
 * Author: John D. Snurr
 * Date: January 4, 2017
 */

public class SearchAnimal 
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Declare and initialize static final String for JDBC Driver
    static final String DB_URL = "jdbc:mysql://localhost:3306/Animals"; // Delcare and initialzie static final String for database URL
    static final String USERNAME = "root"; // Declare and initialize static final String for database username
    static final String PASSWORD = "password"; // Declare and initialize static final String for database password
    
    Connection conn = null; // Declare and initialize Connection for database
    Statement stmt = null; // Declare and initialize Statement for executing database statements
    
    ValidateInput validation = new ValidateInput(); // Declare and instantiate reference to ValidateInput to validate user input
    Animal tempAnimal = new Animal(); // Declare and instantiate reference to Animal to store user input
    
    String tempInput; // Declare String to temporarily store user input (name, type, diet, habitat, size)
    boolean tempBoolean; // Declare boolean to temporarily store user input (endangered, flight, swimming)
    
    public void search()
    {
        String searchMessage = "Which animal would you like to view? "; // Create String message to send to validateString()
        tempInput = validation.validateString(searchMessage, false); // Call validateString() to validate name before assigning to tempInput
        
        try {
            Class.forName(JDBC_DRIVER); // Register the JDBC_DRIVER
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // Establish connection to the database
            stmt = conn.createStatement(); // Assign connection statement to stmt
            
            String searchAnimal = "SELECT * FROM animals_table WHERE animal_name='" + tempInput + "'"; // SELECT statement to search for requested animal
            ResultSet rs = stmt.executeQuery(searchAnimal); // Declare and initialize ResultSet with SELECT statement
            
            if (rs.isBeforeFirst()) // If the ResultSet finds the animal
            {
                while (rs.next()) // Print out the details of the the animal row
                {
                    out.println("Name: " + rs.getString("animal_name")); // Print name
                    out.println("Type: " + rs.getString("animals_type")); // Print type
                    out.println("Diet: " + rs.getString("animals_diet")); // Print diet
                    out.println("Habitat: " + rs.getString("animals_habitat")); // Print habitat
                    out.println("Size: " + rs.getString("animals_size")); // Print size
                    out.println("Endangered: " + rs.getString("animals_endangerment")); // Print endangered
                    out.println("Can Fly: " + rs.getString("animals_flight")); // Print fly
                    out.println("Can Swim: " + rs.getString("animals_swim")); // Print swim
                }
                tempBoolean = true; // Animal is found set tempBoolean to true
            }
            else // If the animal doesn't exist on the database
            {
                tempInput = tempInput.toUpperCase(); // Change the input to uppercase
                System.out.println("\n" + tempInput + " IS NOT ON THE LIST!"); // Inform the user that the animal is unable to be located
                                               
                do { // Request new information from the user
                    String addAnimalMessage = "WOULD YOU LIKE TO ADD " + tempInput + " TO THE DATABASE? "; // Ask user if they would like to add the animal
                    tempInput = validation.validateString(addAnimalMessage, false); // Validate user input
                    
                    if (tempInput.equalsIgnoreCase("YES")) // If the user input yes
                    {
                        CreateAnimal createOperation = new CreateAnimal(); // Declare and instantiate CreateAnimal reference
                        createOperation.populateAnimal(); // Call populateAnimal() to add new animal to the database
                        break; // Break out of the do loop
                    }
                    
                } while (!tempInput.equalsIgnoreCase("NO") || tempInput.equalsIgnoreCase("YES")); // Exit loop if tempInput == NO
            }
                
        } catch (SQLException | ClassNotFoundException se) { // Multicatch for expected exceptions
            se.printStackTrace(); // Print the stack trace if an exception is found
        } finally { // Finally block to close connections
            try {
                if (stmt != null) // If the stmt is null
                    conn.close(); // Close the connection
            } catch (SQLException se) {} // Catach expected exceptions
            try {
                if (conn != null) // If the connection is null
                    conn.close(); // Make sure the connection is closed
            } catch (SQLException se) {} // Catach expected exceptions
        }
    }
}

/**
 * Author: John D. Snurr
 * Date: January 4, 2017
 */
