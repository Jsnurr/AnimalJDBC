package animaljdbc;
import java.sql.*;

/**
 * Author: John D. Snurr
 * Date: January 4, 2017
 */

public class EditAnimal 
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
    
    public void edit()
    {
        String editMessage = "Which animal would you like to edit? "; // Create String message to send to validateString
        tempInput = validation.validateString(editMessage, false); // Call validateString() to validate animal name (numbers are not allowed)
        String animalToUpdate = tempInput; // Store the animal to edit in case the user wishes to change the name of the animal
        
        try {       
            Class.forName(JDBC_DRIVER); // Register the JDBC_DRIVER
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // Establish connection to the database
            stmt = conn.createStatement(); // Assign connection statement to stmt
            
            String searchAnimal = "SELECT * FROM animals_table WHERE animal_name='" + tempInput + "'"; // SELECT statement to get requested animal
            ResultSet rs = stmt.executeQuery(searchAnimal); // Assign query to ResultSet
            
            if (rs.isBeforeFirst()) // If the animal exists on the database
            {
                while (rs.next()) // Get the row and...
                {
                    tempAnimal.setName(rs.getString("animal_name")); // Add animal_name to tempAnimal
                    tempAnimal.setType(rs.getString("animals_type")); // Add animal_type to tempAnimal
                    tempAnimal.setDiet(rs.getString("animals_diet")); // Add animal_diet to tempAnimal
                    tempAnimal.setHabitat(rs.getString("animals_habitat")); // Add animal_habitat to tempAnimal
                    tempAnimal.setSize(rs.getString("animals_size")); // Add animal_size to tempAnimal
                    
                    boolean isEndangered = rs.getString("animals_endangerment").equals("YES"); // Convert animal_endangerment to boolean
                    boolean canFly = rs.getString("animals_flight").equals("YES"); // Convert animal_flight to boolean
                    boolean canSwim = rs.getString("animals_swim").equals("YES"); // Convert animal_swim to boolean
                    
                    tempAnimal.setIsEndangered(isEndangered); // Set isEndangered with converted boolean
                    tempAnimal.setCanFly(canFly); // Set canFly with converted boolean
                    tempAnimal.setCanSwim(canSwim); // Set canSwim with converted boolean
                }
                
                String attributeMessage = "Which attribute would you like to edit " +
                        "(Name, Type, Diet, Habitat, Size, Endangerment, Flight, or Swimming): "; // Prompt user to select an attribute to edit
                tempInput = validation.validateString(attributeMessage, false); // Call validateString to validate attribute (numbers are not allowed)
                
                switch (tempInput.toLowerCase()) // Switch statement to determine attribute to edit
                {
                    case "name": // If the user wants to change the animals name
                        String nameMessage = "Please enter the new name of the animal: "; // Create String message to send to validateString
                        tempAnimal.setName(validation.validateString(nameMessage, false)); // Call validateString() to validate name before assigning to tempAnimal name
                        break; // Break out of the editing menu
                    case "type": // If the user wants to change the animals type
                        System.out.println("Current type: " + tempAnimal.getType()); // Display the animals current type to the user
                        
                        do { // Do-while loop to gather new animal type from user
            
                            String typeMessage = "Please enter the new type of animal (Mammal, Reptile, Bird, Insect, Arachnid, or Aquatic): "; // Create String message to send to validateString
                            tempInput = validation.validateString(typeMessage, false); // Call validateString() to validate type before assigning to tempAnimal type
            
                        } while (!tempInput.equalsIgnoreCase("Mammal") && !tempInput.equalsIgnoreCase("Reptile") &&
                            !tempInput.equalsIgnoreCase("Bird") && !tempInput.equalsIgnoreCase("Insect") &&
                            !tempInput.equalsIgnoreCase("Arachnid") && !tempInput.equalsIgnoreCase("Aquatic")); // End do-while loop when user inputs an accepted String
            
                        tempAnimal.setType(tempInput); // Assign new type to tempAnimal type
                        break; // Break out of the editing menu
                    case "diet": // If the user wants to change the animals diet
                        System.out.println("Current diet: " + tempAnimal.getDiet()); // Display the animals current diet to the user
                        
                        do { // Do-while loop to gather new animal diet from user
                
                            String dietMessage = "Please enter the new diet of the animal (Carnivore, Herbivore, or Omnivore): "; // Create String message to send to validateString
                            tempInput = validation.validateString(dietMessage, false); // Call validateString() to validate diet before assigning to tempAnimal diet
                
                        } while (!tempInput.equalsIgnoreCase("Carnivore") && !tempInput.equalsIgnoreCase("Herbivore") &&
                            !tempInput.equalsIgnoreCase("Omnivore")); // End do-while loop when user inputs an accepted String
            
                        tempAnimal.setDiet(tempInput); // Assign new diet to tempAnimal diet
                        break; // Break out of the editing menu
                    case "habitat": // If the user wants to change the animals habitat
                        System.out.println("Current habitat: " + tempAnimal.getHabitat()); // Display the animals current habitat to the user
                        
                        do { // Do-while loop to gather new animal habitat from user
                
                            String habitatMessage = "Please enter the new habitat of the animal (Desert, Grasslands, Forest, Mountains, " +
                                "Freshwater, Ocean, or Tundra): "; // Create String message to send to validateString()
                            tempInput = validation.validateString(habitatMessage, false); // Call validateString() to validate habitat before assigning to tempAnimal habitat
                
                        } while (!tempInput.equalsIgnoreCase("Desert") && !tempInput.equalsIgnoreCase("Grasslands") &&
                            !tempInput.equalsIgnoreCase("Forest") && !tempInput.equalsIgnoreCase("Mountains") &&
                            !tempInput.equalsIgnoreCase("Freshwater") && !tempInput.equalsIgnoreCase("Ocean") &&
                            !tempInput.equalsIgnoreCase("Tundra")); // End do-while loop when user inputs an accepted String
            
                        tempAnimal.setHabitat(tempInput); // Assign new habitat to tempAnimal habitat
                        break; // Break out of editing menu
                    case "size": // If the user wants to change the animals size
                        System.out.println("Current size: " + tempAnimal.getSize()); // Display the animals current size to the user
                        
                        do { // Do-while loop to gather new animal size from user
                
                            String sizeMessage = "Please enter the new size category of the animal (Small, Medium, or Large): "; // Create String message to send to validateString()
                            tempInput = validation.validateString(sizeMessage, false); // Call validateString() to validate size before assigning to tempAnimal size
                
                        } while (!tempInput.equalsIgnoreCase("Small") && !tempInput.equalsIgnoreCase("Medium") &&
                            !tempInput.equalsIgnoreCase("Large")); // End do-while loop when user inputs an accepted String
            
                        tempAnimal.setSize(tempInput); // Assign new size to tempAnimal size
                        break; // Break out of editing menu
                    case "endangerment": // If the user wants to change the animals endangerment status
                        String isEndangered = tempAnimal.getIsEndangered() ? "Yes" : "No"; // Decipher current isEndangered boolean
                        System.out.println("Is animal listed as endangered: " + isEndangered); // Display current endangerment status to user
                        
                        do { // Do-while loop to gather new animal endangerment status from user
                
                            String endangeredMessage = "Is the animal an endangered species (Yes or No)? "; // Create String message to send to validateString()
                            tempInput = validation.validateString(endangeredMessage, false); // Call validateString() to validate endangerment status before assigning to tempAnimal isEndangered
                
                            tempBoolean = tempInput.equalsIgnoreCase("Yes"); // Decipher user input and assign boolean value
                
                        } while(!tempInput.equalsIgnoreCase("Yes") && !tempInput.equalsIgnoreCase("No")); // End do-while loop when user inputs accepted String
            
                        tempAnimal.setIsEndangered(tempBoolean); // Assign new endangerment status to tempAnimal isEndangered
                        break; // Break out of editing menu
                    case "flight": // If the user wants to change the animals flight status
                        String hasFlight = tempAnimal.getCanFly() ? "Yes" : "No"; // Decipher current canFly boolean
                        System.out.println("Does animal have flight capabilities: " + hasFlight); // Display current flight status to user
                        
                        do { // Do-while loop to gather new animal flight status from user
                
                            String flightMessage = "Is the animal capable of flight (Yes or No)? "; // Create String message to send to validateString()
                            tempInput = validation.validateString(flightMessage, false); // Call validateString() to validate flight status before assigning to tempAnimal canFly
                
                            tempBoolean = tempInput.equalsIgnoreCase("Yes"); // Decipher user input and assign boolean value
                
                        } while (!tempInput.equalsIgnoreCase("Yes") && !tempInput.equalsIgnoreCase("No")); // End do-while loop when user inputs accepted String
            
                        tempAnimal.setCanFly(tempBoolean); // Assign new flight status to tempAnimal canFly
                        break; // Break out of editing menu
                    case "swimming": // If the user wants to change the animals swimming status
                        String canSwim = tempAnimal.getCanSwim() ? "Yes" : "No"; // Decipher current canSwim boolean
                        System.out.println("Does animal have swimming capabilities: " + canSwim); // Display current simming status to user
                        
                        do { // Do-while loop to gather new animal swimming status from user
                
                            String swimMessage = "Is the animal capable of swimming (Yes of No)? "; // Create String message to send to validateString()
                            tempInput = validation.validateString(swimMessage, false); // Call validateString() to validate swimming status before assigning to tempAnimal canSwim
                
                            tempBoolean = tempInput.equalsIgnoreCase("Yes"); // Decipher user input and assign boolean value
                
                        } while (!tempInput.equalsIgnoreCase("Yes") && !tempInput.equalsIgnoreCase("No")); // End do-while loop when user inputs accepted String
            
                        tempAnimal.setCanSwim(tempBoolean); // Assign new swimming status to tempAnimal canSwim
                        break; // Break out of editing menu
                }
                
                AnimalMenu menu = new AnimalMenu(); // Declare and instantiate reference to AnimalMenu
                tempAnimal = menu.attributeToUppercase(tempAnimal); // Call attributeToUppercase to convert all input attributes to uppercase to make output more readable
                updateAnimal(animalToUpdate); // Call updateAnimal() to apply changes to the database
            }
            else 
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
    
    public void updateAnimal(String animalToUpdate)
    {
        try {
            Class.forName(JDBC_DRIVER); // Register the JDBC_DRIVER
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD); // Establish connection to the database
            stmt = conn.createStatement(); // Assign connection statement to stmt
            
            String isEndangered = tempAnimal.getIsEndangered() ? "YES" : "NO"; // Translate isEndangered to String by using ternary operand
            String hasFlight = tempAnimal.getCanFly() ? "YES" : "NO"; // Translate canFly to String by using ternary operand
            String canSwim = tempAnimal.getCanSwim() ? "YES" : "NO"; // Translate canSwim to String by using ternary operand
            
            String updateAnimal = "UPDATE animals_table SET animal_name='" + tempAnimal.getName() + "', animals_type='" + tempAnimal.getType() + "', animals_diet='" + tempAnimal.getDiet() +
                    "', animals_habitat='" + tempAnimal.getHabitat() + "', animals_size='" + tempAnimal.getSize() + "', animals_endangerment='" + isEndangered +
                    "', animals_flight='" + hasFlight + "', animals_swim='" + canSwim + "' WHERE animal_name='" + animalToUpdate + "'"; // UPDATE statement to be executed
            stmt.executeUpdate(updateAnimal); // Execute the UPDATE statement
            
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
