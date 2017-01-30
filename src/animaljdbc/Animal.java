package animaljdbc;

/**
 * Author: John D. Snurr
 * Date: January 4, 2017
 */

public class Animal 
{
    private String name, type, diet, habitat, size; // Declare encapsulated class variables 
    private boolean isEndangered, canFly, canSwim;  // to hold animal attributes
    
    /* ---------------------------------
     * ---------- ACCESSORS ------------
     * --------------------------------- */
    public String getName() // Accessor for animal name
    {
        return name; // Return the name of the animal
    }
    
    public String getType() // Accessor for animal type
    {
        return type; // Return the type of animal (Mammal, Reptile, Bird, Insect, Arachnids, Aquatic Animal)
    }
    
    public String getDiet() // Accessor for animal diest
    {
        return diet; // Return the diet of the animal (carnivore, herbivore, omnivore)
    }
    
    public String getHabitat() // Accessor for animal habitat
    { 
        return habitat; // Return habitat of the animal (Desert, Grasslands, Forests, Mountains, Freshwater, Oceans, Tundra)
    }
    
    public String getSize() // Accessor for animal size 
    {
        return size; // Return animal size (small, medium, large)
    }
    
    public boolean getIsEndangered() // Accessor for endangerment
    {
        return isEndangered; // Return animal endangerment status
    }
    
    public boolean getCanFly() // Accessor for animal ability to fly
    {
        return canFly; // Return animal flight capabilities
    }
    
    public boolean getCanSwim() // Accessor for animal ability to swim
    {
        return canSwim; // Return animal swimming capabilities
    }
    
    /* ---------------------------------
     * ----------- MUTATORS ------------
     * --------------------------------- */
    public void setName(String animalName) // Mutator for animal name variable
    {
        name = animalName; // Assign method parameter as the name
    }
    
    public void setType(String animalType) // Mutator for animal type variable
    {
        // If the method parameter is one of the accepted animal types...
        if (animalType.equalsIgnoreCase("Mammal") || animalType.equalsIgnoreCase("Reptile") ||
                animalType.equalsIgnoreCase("Bird") || animalType.equalsIgnoreCase("Insect") ||
                animalType.equalsIgnoreCase("Arachnid") || animalType.equalsIgnoreCase("Aquatic"))
        {
            type = animalType; // assign the method parameter as the type
        }
    }
    
    public void setDiet(String animalDiet) // Mutator for animal diet variable
    {
        // If the method parameter is one of the accepted animal diets...
        if (animalDiet.equalsIgnoreCase("Carnivore") || animalDiet.equalsIgnoreCase("Herbivore") ||
                animalDiet.equalsIgnoreCase("Omnivore"))
        {
            diet = animalDiet; // assign the method parameter as the diet
        }
    }
    
    public void setHabitat(String animalHabitat) // Mutator for animal habitat variable
    {
        // If the method parameter is one of the accepted animal habitats...
        if (animalHabitat.equalsIgnoreCase("Desert") || animalHabitat.equalsIgnoreCase("Grasslands") ||
                animalHabitat.equalsIgnoreCase("Forest") || animalHabitat.equalsIgnoreCase("Mountains") ||
                animalHabitat.equalsIgnoreCase("Freshwater") || animalHabitat.equalsIgnoreCase("Ocean") ||
                animalHabitat.equalsIgnoreCase("Tundra"))
        {
            habitat = animalHabitat; // assign the method parameter to the habitat
        }
    }
    
    public void setSize(String animalSize) // Mutator for animal size variable 
    {
        // If the method parameter is one of the accepted animal sizes...
        if (animalSize.equalsIgnoreCase("Small") || animalSize.equalsIgnoreCase("Medium") ||
                animalSize.equalsIgnoreCase("Large"))
        {
            size = animalSize; // assign the method parameter to the size
        }
    }
    
    public void setIsEndangered(boolean endangerment) // Mutator for animal isEndangered boolean
    {
        isEndangered = endangerment; // assign method parameter to isEndangered
    }
    
    public void setCanFly(boolean hasFlight) // Mutator for animal canFly boolean
    {
        canFly = hasFlight; // assign method parameter to canFly
    }
    
    public void setCanSwim(boolean isAquatic) // Mutator for animal canSwim boolean
    {
        canSwim = isAquatic; // assign parameter to canSwim
    }
}

class AnimalMenu
{
    ValidateInput validation = new ValidateInput(); // Declare and instantiate reference to ValidateInput to validate user input
    
    String tempInput; // Declare String to temporarily store user input (name, type, diet, habitat, size)
    boolean tempBoolean; // Declare boolean to temporarily store user input (endangered, flight, swimming)
    
    public void displayMenu()
    {
        do { // Do-while loop to prompt user for actions and manipulate animal ArrayList
            
            System.out.println("\n----------- WELCOME TO THE ANIMAL DATABASE ----------\n");  // Display welcome message to the user
            String consoleMessage = "What would you like to do (Add, Edit, Delete, Search, End)? "; // Display options to user 
            tempInput = validation.validateString(consoleMessage, false); // Validate user input
        
            switch(tempInput.toLowerCase()) // Switch statement used to navigate options based upon user input
            {
                case "add": // If user wants to add an animal
                    System.out.println("\n----------- ADD AN ANIMAL TO THE DATABASE ----------\n"); // Display the add message to the user
                    CreateAnimal createOperation = new CreateAnimal(); // Declare and instantiate reference to CreateAnimal
                    createOperation.populateAnimal(); // Call populateAnimal() to add new animals to the database
                    break; // Break out of switch statement and return to the welcome message
                case "edit": // If user wants to edit an animal
                    System.out.println("\n----------- EDIT AN ANIMAL IN THE DATABASE ----------\n"); // Display the edit message to the user
                    EditAnimal editOperation = new EditAnimal(); // Declare and instantiate reference to EditAnimal
                    editOperation.edit(); // Call edit() to edit an animal on the database
                    break; // Break out of switch statement and return to the welcome message
                case "delete": // If user wants to delete an animal
                    System.out.println("\n----------- DELETE AN ANIMAL IN THE DATABASE ----------\n"); // Display the deletion message to the user
                    DeleteAnimal deleteOperation = new DeleteAnimal(); // Declare and instantiate reference to DeleteAnimal
                    deleteOperation.delete(); // Call delete() to remove an animal from the database
                    break; // Break out of switch statement and return to the welcome message
                case "search": // If user wants to search for an animal
                    System.out.println("\n----------- SEARCH FOR AN ANIMAL IN THE DATABASE ----------\n"); // Display the search message to the user
                    SearchAnimal searchOperation = new SearchAnimal(); // Declare and instantiate reference to SearchAnimal
                    searchOperation.search(); // Call search() to search the database for an animal
                    break; // Break out of switch statement and return to the welcome message
            }
            
        } while (!tempInput.equalsIgnoreCase("End")); // If the user inputs the word "end," exit the application
    }
    
    public Animal attributeToUppercase(Animal animal) // attributeToUppercase converts all user input to UPPERCASE
    {                                                 // to make output more readable
        tempInput = animal.getName(); // Assign animal name to tempInput
        tempInput = tempInput.toUpperCase(); // Call toUppercase() and assign new String to tempInput
        animal.setName(tempInput); // Assign tempInput to animal name
        
        tempInput = animal.getType(); // Assign animal type to tempInput
        tempInput = tempInput.toUpperCase(); // Call toUppercase() and assign new String to tempInput
        animal.setType(tempInput); // Assign tempInput to animal type
        
        tempInput = animal.getDiet(); // Assign animal diet to tempInput
        tempInput = tempInput.toUpperCase(); // Call toUppercase() and assign new String to tempInput
        animal.setDiet(tempInput); // Assign tempInput to animal diet
        
        tempInput = animal.getHabitat(); // Assign animal habitat to tempInput
        tempInput = tempInput.toUpperCase(); // Call toUppercase() and assign new String to tempInput
        animal.setHabitat(tempInput); // Assign tempInput to animal habitat
        
        tempInput = animal.getSize(); // Assign animal size to tempInput
        tempInput = tempInput.toUpperCase(); // Call toUppercase() and assign new String to tempInput
        animal.setSize(tempInput); // Assign tempInput to animal size
        
        return animal; // Return modified animal to caller
    }
}

/**
 * Author: John D. Snurr
 * Date: January 4, 2017
 */
