import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    /*
     * create a static locations HashMap
     */
    public static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        /*
         * create a FileLogger object
         */
        FileLogger fileLogger = new FileLogger();

        /*
         * create a ConsoleLogger object
         */
        ConsoleLogger consoleLogger = new ConsoleLogger();

        /*
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits ???
         */
        ArrayList<Integer> locationsFileLinesArray = new ArrayList<>();

        try (FileReader fileReader = new FileReader(LOCATIONS_FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String temp = "Available locations:" + System.lineSeparator();
            fileLogger.log(temp);
            consoleLogger.log(temp);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                String[] lineArray = line.split(",");
                sb.append(lineArray[0]); //the location as a number e.g. 82 or 95
                sb.append(": ");
                sb.append(lineArray[1]);

                for (int i = 2; i < lineArray.length; i++) {
                    sb.append(",");
                    sb.append(lineArray[i]);
                }
                StringBuilder newSB = new StringBuilder();
                newSB.append(lineArray[1]);
                for (int i = 2; i < lineArray.length; i++) {
                    newSB.append(",");
                    newSB.append(lineArray[i]);
                }
                LinkedHashMap<String, Integer> tempExitsMap = new LinkedHashMap<>();
                Location tempLoc = new Location((Integer.parseInt(lineArray[0])), newSB.toString(), tempExitsMap);
                locations.put((Integer.parseInt(lineArray[0])), tempLoc); //locations hashmap has 141 entries, each with a location.
                // Each of the 141 Locations contain ID (0-141), description of the places and an empty hashmap for exits
                sb.append(System.lineSeparator());
                fileLogger.log(sb.toString());
                consoleLogger.log(sb.toString());
                boolean addedToArray = false;
                for (Integer integer : locationsFileLinesArray) {
                    if (integer.equals(Integer.parseInt(lineArray[0]))) {
                        addedToArray = true;
                    }
                }
                if (!addedToArray) {
                    locationsFileLinesArray.add(Integer.parseInt(lineArray[0]));
                }
            }
        } catch (Exception e) {
        }
        /*
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit ??????
         */
        try (FileReader fileReader2 = new FileReader(DIRECTIONS_FILE_NAME);
             BufferedReader bufferedReader2 = new BufferedReader(fileReader2)
        ) {
            String line;
            String temp = "Available directions:" + System.lineSeparator();
            fileLogger.log(temp);
            consoleLogger.log(temp);

            while ((line = bufferedReader2.readLine()) != null) {
                LinkedHashMap<String, Integer> exits;
                StringBuilder sb = new StringBuilder();
                String[] lineArray = line.split(",");
                sb.append(lineArray[0]);
                sb.append(": ");
                sb.append(lineArray[1]);
                sb.append(": ");
                sb.append(lineArray[2]);
                /*
                [0] = current location
                [1] = direction (S, W, N, E, etc)
                [2] = destination
                 */
                sb.append(System.lineSeparator());
                fileLogger.log(sb.toString());
                consoleLogger.log(sb.toString());

                for (int x : locationsFileLinesArray) {
                    if (Integer.parseInt(lineArray[0]) == x) {
                        Location locationToBeEdited = locations.get(x);
                        int locationIDToBeEdited = locationToBeEdited.getLocationId();
                        String descriptionToBeEdited = locationToBeEdited.getDescription();
                        exits = (LinkedHashMap<String, Integer>) locationToBeEdited.getExits();
                        exits.put(lineArray[1], Integer.parseInt(lineArray[2])); //can i use addExits?
                        Location newLocation = new Location(locationIDToBeEdited, descriptionToBeEdited, exits);
                        locations.put(x, newLocation);
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    /**
     * implement all methods for Map
     * @return
     */
    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
