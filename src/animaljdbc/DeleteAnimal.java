package animaljdbc;
import java.sql.*;

/**
 * Author: John D. Snurr
 * Date: January 4, 2017
 */

public class DeleteAnimal 
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
    
    public void delete()
    {
        String searchMessage = "Which animal would you like to view? "; // Create String message to send to validateString()
        tempInput = validation.validateString(searchMessage, false); // Call validateString() to validate name before assigning to tempInput
        
        try {
            Class.forName(JDBC_DRIVER); // Register the JDBC_DRIVER
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // Establish connection to the database
            stmt = conn.createStatement(); // Assign connection statement to stmt
            
            String searchAnimal = "SELECT * FROM animals_table WHERE animal_name='" + tempInput + "'"; // SELECT statement to search for animal
            ResultSet rs = stmt.executeQuery(searchAnimal); // Execute SEARCH statement and assign to ResultSet
            
            if (rs.isBeforeFirst()) // If the animal exists on the database
            {
                String deleteAnimal = "DELETE FROM animals_table WHERE animal_name='" + tempInput + "'"; // DELETE statement to remove animal
                stmt.executeUpdate(deleteAnimal); // Execute DELETE statement
                
                tempInput = tempInput.toUpperCase(); // Convert tempInput to uppercase
                System.out.println(tempInput + " REMOVED FROM THE DATABASE."); // Output that the animal has been removed
            }
            else // If the animal doesn't exist on the database
            {
                tempInput = tempInput.toUpperCase(); // Change the input to uppercase
                System.out.println("\n" + tempInput + " IS NOT ON THE LIST!"); // Inform the user that the animal is unable to be located
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
