package animaljdbc;
import java.sql.*;

/**
 * Author: John D. Snurr
 * Date: January 4, 2017
 */

public class CreateAnimal 
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
    
    public void populateAnimal() // addAnimal() prompts user to add an animal and its attributes to the ArrayList
    {       
        String nameMessage = "Please enter the name of the animal: "; // Create String message to send to validateString()
        tempAnimal.setName(validation.validateString(nameMessage, false)); // Call validateString() to validate name (numbers are not accepted)
        
        try {
            Class.forName(JDBC_DRIVER); // Register the JDBC_DRIVER
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // Establish connection to the database
            stmt = conn.createStatement(); // Assign connection statement to stmt
            
            String searchAnimal = "SELECT * FROM animals_table WHERE animal_name='" + tempAnimal.getName() + "'"; // String to search the database to see if the input animal already exists
            ResultSet rs = stmt.executeQuery(searchAnimal);  // Declare and initialize ResultSet with searchAnimal query
            
            if (rs.isBeforeFirst()) // If the animal exists on the table
                tempBoolean = true; // Set tempBoolean to true
                
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
        
        if(!tempBoolean) // If the above name check passes...
        {
            do { // Do-while loop to gather animal type from user
            
                String typeMessage = "Please enter the type of animal (Mammal, Reptile, Bird, Insect, Arachnid, or Aquatic): "; // Create String message to send to validateString()
                tempInput = validation.validateString(typeMessage, false); // Call validateString() to validate type (numbers are not accepted)
            
            } while (!tempInput.equalsIgnoreCase("Mammal") && !tempInput.equalsIgnoreCase("Reptile") &&
                !tempInput.equalsIgnoreCase("Bird") && !tempInput.equalsIgnoreCase("Insect") &&
                !tempInput.equalsIgnoreCase("Arachnid") && !tempInput.equalsIgnoreCase("Aquatic")); // End the do-while loop when the user inputs an accepted String
            
            tempAnimal.setType(tempInput); // Assign the user input to the type attribute of the tempAnimal
        
            do { // Do-while loop to gather animal diet from user
                
                String dietMessage = "Please enter the diet of the animal (Carnivore, Herbivore, or Omnivore): "; // Create String message to send to validateString()
                tempInput = validation.validateString(dietMessage, false); // Call validateString() to validate diet (numbers are not accepted)
                
            } while (!tempInput.equalsIgnoreCase("Carnivore") && !tempInput.equalsIgnoreCase("Herbivore") &&
                !tempInput.equalsIgnoreCase("Omnivore")); // End the do-while loop when the user inputs an accepted String
            
            tempAnimal.setDiet(tempInput); // Assign the user input to the diet attribute of the tempAnimal
            
            do { // Do-while loop to gather animal habitat from user
                
                String habitatMessage = "Please enter the habitat of the animal (Desert, Grasslands, Forest, Mountains, " +
                        "Freshwater, Ocean, or Tundra): "; // Create String message to send to validateString()
                tempInput = validation.validateString(habitatMessage, false); // Call valiadteString() to validate habitat (numbers are not accepted)
                
            } while (!tempInput.equalsIgnoreCase("Desert") && !tempInput.equalsIgnoreCase("Grasslands") &&
                !tempInput.equalsIgnoreCase("Forest") && !tempInput.equalsIgnoreCase("Mountains") &&
                !tempInput.equalsIgnoreCase("Freshwater") && !tempInput.equalsIgnoreCase("Ocean") &&
                !tempInput.equalsIgnoreCase("Tundra")); // End the do-while loop when the user inputs an accepted String
            
            tempAnimal.setHabitat(tempInput); // Assign the user input to the habitat attribute of the tempAnimal
            
            do { // Do-while loop to gather animal size from user
                
                String sizeMessage = "Please enter the size category of the animal (Small, Medium, or Large): "; // Create String message to send to validateString()
                tempInput = validation.validateString(sizeMessage, false); // Call validateString() to validate size (numbers are not accepted)
                
            } while (!tempInput.equalsIgnoreCase("Small") && !tempInput.equalsIgnoreCase("Medium") &&
                !tempInput.equalsIgnoreCase("Large")); // End the do-while loop when the user inputs an accepted String
            
            tempAnimal.setSize(tempInput); // Assign the user input to the size attribute of the tempAnimal
            
            do { // Do-while loop to gather animal endangerment status from user
                
                String endangeredMessage = "Is the animal an endangered species (Yes or No)? "; // Create String message to send to validateString()
                tempInput = validation.validateString(endangeredMessage, false); // Call validateString() to validate endangered status (numbers are not accepted)
                
                tempBoolean = tempInput.equalsIgnoreCase("Yes"); // Call equalsIgnoreCase() to decipher user input
                
            } while(!tempInput.equalsIgnoreCase("Yes") && !tempInput.equalsIgnoreCase("No")); // End do-while loop when the user inputs an accepted String
            
            tempAnimal.setIsEndangered(tempBoolean); // Assign tempBoolean to isEndangered attribute of the tempAnimal
            
            do { // Do-while loop to gather animal flight capabilites from user
                
                String flightMessage = "Is the animal capable of flight (Yes or No)? "; // Create String message to send to validateString()
                tempInput = validation.validateString(flightMessage, false); // Call validateString() to validate flight capabilites (numbers are not accepted)
                
                tempBoolean = tempInput.equalsIgnoreCase("Yes"); // Call equalsIgnoreCase() to decipher user input
                
            } while (!tempInput.equalsIgnoreCase("Yes") && !tempInput.equalsIgnoreCase("No")); // End do-while loop when the user inputs an accepted String
            
            tempAnimal.setCanFly(tempBoolean); // Assign tempBoolean to canFly attribute of the tempAnimal
            
            do { // Do-while loop to gather animal swimming capabilities from user
                
                String swimMessage = "Is the animal capable of swimming (Yes of No)? "; // Create String message to send to validateString
                tempInput = validation.validateString(swimMessage, false); // Call validateString() to validate swimming capabilities (numbers are not accepted)
                
                tempBoolean = tempInput.equalsIgnoreCase("Yes"); // Call equalsIgnoreCase() to decipher user input
                
            } while (!tempInput.equalsIgnoreCase("Yes") && !tempInput.equalsIgnoreCase("No")); // End do-while loop when the user inputs an accepted String
            
            tempAnimal.setCanSwim(tempBoolean); // Assign tempBoolean to canSwim attribute of the tempAnimal
            
            AnimalMenu menu = new AnimalMenu(); // Declare and instantiate reference to AnimalMenu
            tempAnimal = menu.attributeToUppercase(tempAnimal); // Call attributeToUppercase to convert all input attributes to uppercase to make output more readable
            insertAnimal(); // Call insertAnimal() to add the tempAnimal to the database
        }
        else // If the animal already exists in the currentList...
        {
            System.out.println("That animal already exists in the list."); // Display the message to the user
        }
    }
    
    public void insertAnimal()
    {
        try {
            Class.forName(JDBC_DRIVER); // Register the JDBC_DRIVER
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // Establish connection to the database
            stmt = conn.createStatement(); // Assign connection statement to stmt
            
            String isEndangered = tempAnimal.getIsEndangered() ? "YES" : "NO"; // Translate isEndangered to String by using ternary operand
            String hasFlight = tempAnimal.getCanFly() ? "YES" : "NO"; // Translate canFly to String by using ternary operand
            String canSwim = tempAnimal.getCanSwim() ? "YES" : "NO"; // Translate canSwim to String by using ternary operand
            
            String createAnimal = "INSERT INTO animals_table VALUES ('" + tempAnimal.getName() + "', '" + tempAnimal.getType() + "', '" + tempAnimal.getDiet() +
                    "', '" + tempAnimal.getHabitat() + "', '" + tempAnimal.getSize() + "', '"  + isEndangered + "', '" + hasFlight + "', '" + canSwim + "')"; // INSERT statement to execute
            stmt.executeUpdate(createAnimal); // Execute the INSERT statement to add the tempAnimal to the database
            
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