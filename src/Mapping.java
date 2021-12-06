import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /*
     * create a static LocationMap object
     */
    public static LocationMap locationMap = new LocationMap();

    /*
     * create a vocabulary HashMap to store all directions a user can go
     */
    HashMap<String, String> vocab = new HashMap<>();

    /*
     * create a FileLogger object
     */
    FileLogger fileLogger = new FileLogger();

    /*
     * create a ConsoleLogger object
     */
    ConsoleLogger consoleLogger = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /*
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */

        vocab.put("NORTH", "N");
        vocab.put("N", "N");
        vocab.put("SOUTH", "S");
        vocab.put("S", "S");
        vocab.put("WEST", "W");
        vocab.put("W", "W");
        vocab.put("EAST", "E");
        vocab.put("E", "E");

        vocab.put("DOWN", "D");
        vocab.put("D", "D");
        vocab.put("UP", "U");
        vocab.put("U", "U");

        vocab.put("QUIT", "Q");
        vocab.put("Q", "Q");

        vocab.put("NORTHEAST", "NE");
        vocab.put("NE", "NE");
        vocab.put("NORTHWEST", "NW");
        vocab.put("NW", "NW");
        vocab.put("SOUTHEAST", "SE");
        vocab.put("SE", "SE");
        vocab.put("SOUTHWEST", "SW");
        vocab.put("SW", "SW");

    }

    public void mapping() {

        /*
         * create a Scanner object
         */
        Scanner sc = new Scanner(System.in);

        /*
         * initialize a location variable with the INITIAL_LOCATION
         */
        int location = INITIAL_LOCATION;
        while (location!=0) { //originally was while(true)

            /*
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            String description = ((locationMap.get(location)).getDescription())+ (System.lineSeparator());
            fileLogger.log(description);
            consoleLogger.log(description);


            /* TODO
             * verify if the location is exit ??
             */

            /*
             * get a map of the exits for the location
             */
            Map<String, Integer> exits = (locationMap.get(location)).getExits();
            /*
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            StringBuilder exitString = new StringBuilder();
            exitString.append("Available exits are ");
            for (String value : exits.keySet()) {
                exitString.append(value);
                exitString.append(", ");
            }
            exitString.append(System.lineSeparator());
            fileLogger.log(exitString.toString());
            consoleLogger.log(exitString.toString());


            /*
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String userInput = sc.nextLine().toUpperCase().trim();

            /*
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            boolean validDirection = false;
            String[] userInputArray = userInput.split(" ");
            String direction = "";

            for (int i = (userInputArray.length - 1); i >= 0; i--) {
                if ((vocab.get(userInputArray[i]) != null) && (userInputArray[i].length() != 1) &&
                        ((userInputArray[i].length() != 2) || (userInputArray[i].equalsIgnoreCase("UP")))){
                    validDirection = true;
                    direction = vocab.get(userInputArray[i]);
                    break;
                }else if ((vocab.get(userInputArray[i]) != null) && (userInputArray[i].length() == 1 || userInputArray[i].length() == 2)
                        && (userInputArray.length==1)){
                    validDirection = true;
                    direction = vocab.get(userInputArray[i]);
                    break;
                }else if ((userInputArray[i].length() == 4 || userInputArray[i].length() == 5 || userInputArray[i].length() == 9)
                        && !(vocab.get(userInputArray[i]) == null)&& (userInputArray.length==1)){
                    validDirection = true;
                    direction = vocab.get(userInputArray[i]);
                    break;
                }
            }

            /*
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
            if (!validDirection || (locationMap.get(location)).getExits().get(direction) == null){
                String errorMessage = "You cannot go in that direction";
                fileLogger.log(errorMessage);
                fileLogger.log(System.lineSeparator());
                consoleLogger.log(errorMessage);
                consoleLogger.log(System.lineSeparator());
            }else {
                location = (locationMap.get(location)).getExits().get(direction);
            }

        }
        fileLogger.log((locationMap.get(0).getDescription()) + System.lineSeparator());
        consoleLogger.log((locationMap.get(0).getDescription()) + System.lineSeparator());
    }

    public static void main(String[] args) {
        /*
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping mapping = new Mapping();
        mapping.mapping();
    }
}
