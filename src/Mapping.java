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
        vocab.put("SW", "SW");
        vocab.put("SOUTHWEST", "SW");
        vocab.put("Q", "Q");
        vocab.put("QUIT", "Q");
        vocab.put("U", "U");
        vocab.put("UP", "U");
        vocab.put("D", "D");
        vocab.put("DOWN", "D");
        vocab.put("N", "N");
        vocab.put("NORTH", "N");
        vocab.put("S", "S");
        vocab.put("SOUTH", "S");
        vocab.put("E", "E");
        vocab.put("EAST", "E");
        vocab.put("W", "W");
        vocab.put("WEST", "W");
        vocab.put("NE", "NE");
        vocab.put("NORTHEAST", "NE");
        vocab.put("SE", "SE");
        vocab.put("SOUTHEAST", "SE");
        vocab.put("NW", "NW");
        vocab.put("NORTHWEST", "NW");
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

            /* TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            String description = ((locationMap.get(location)).getDescription())+ (System.lineSeparator());
            fileLogger.log(description);
            consoleLogger.log(description);


            /* TODO
             * verify if the location is exit ??
             */

            /* TODO
             * get a map of the exits for the location
             */
            Map<String, Integer> exits = (locationMap.get(location)).getExits();
            /* TODO
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


            /* TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String userInput = sc.nextLine().toUpperCase();

            /* TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            boolean validDirection = false;
            String direction = "";
            if ((userInput.length() == 1 || userInput.length() == 2) && (exits.get(userInput) != null)){ //if the input is just a letter
                validDirection = true;
                direction = vocab.get(userInput);
            }else if (userInput.length() == 4 || userInput.length() == 5 || userInput.length() == 9){/*if the input is 4, 5 or 9 chars i.e.
                it's just the direction*/
                if (vocab.get(userInput) != null){
                    validDirection = true;
                    direction = vocab.get(userInput);
                }
            }else { //if the input is a string of words containing the key
                String[] userInputArray = userInput.split(" ");
                for (String s : userInputArray) {
                    if (vocab.get(s) != null) {
                        direction = vocab.get(s);
                    }
                }
            }


            /* TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
            if (!validDirection){
                String errorMessage = "You cannot go in that direction";
                fileLogger.log(errorMessage);
                fileLogger.log(System.lineSeparator());
                consoleLogger.log(errorMessage);
                consoleLogger.log(System.lineSeparator());
            }else {
                location = (locationMap.get(location)).getExits().get(direction);
            }

        }
        System.out.println(locationMap.get(0).getDescription());
    }

    public static void main(String[] args) {
        /* TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping mapping = new Mapping();
        mapping.mapping();
    }

}
