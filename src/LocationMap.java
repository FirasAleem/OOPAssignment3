import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

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

        /* TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits ???
         */

        try(FileReader fileReader = new FileReader(LOCATIONS_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String temp = "Available locations:" + System.lineSeparator();
            fileLogger.log(temp);
            consoleLogger.log(temp);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                String[] lineArray = line.split(",");
                sb.append(lineArray[0]);
                sb.append(": ");
                sb.append(lineArray[1]);

                for (int i = 2; i < lineArray.length ; i++) {
                    sb.append(",");
                    sb.append(lineArray[i]);
                }
                HashMap<String, Integer> tempExitsMap = new HashMap<>();
                Location tempLoc = new Location((Integer.parseInt(lineArray[0])), sb.toString(), tempExitsMap);
                sb.append(System.lineSeparator());
                fileLogger.log(sb.toString());
                consoleLogger.log(sb.toString());
                locations.put((Integer.parseInt(lineArray[0])), tempLoc);
            }
        }catch (Exception e){
        }

        /* TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */
        try(FileReader fileReader = new FileReader(DIRECTIONS_FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            int lineCounter = 0;
            String line;
            String temp = "Available directions:" + System.lineSeparator();
            fileLogger.log(temp);
            consoleLogger.log(temp);
            while ((line = bufferedReader.readLine()) != null) {
                StringBuilder sb = new StringBuilder();
                String[] lineArray = line.split(",");
                sb.append(lineArray[0]);
                sb.append(": ");
                sb.append(lineArray[1]);
                sb.append(": ");
                sb.append(lineArray[2]);
                /*
                [0] = current location
                [1] = direction
                [2] = destination
                 */
                sb.append(System.lineSeparator());
                fileLogger.log(sb.toString());
                consoleLogger.log(sb.toString());
                HashMap<String, Integer> tempExitsMap = new HashMap<>();
                tempExitsMap.put(lineArray[1], Integer.parseInt(lineArray[2]));
                Location tempLoc = new Location((Integer.parseInt(lineArray[0])), ((lineArray[1])), tempExitsMap);
                locations.put(lineCounter, tempLoc);
                lineCounter++;
            }

        }catch (Exception e){

        }

    }

    /** TODO
     * implement all methods for Map
     * @return
     */
    @Override
    public int size() {
        //TODO
        return 0;
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        //TODO
        return true;
    }

    @Override
    public boolean containsValue(Object value) {
        //TODO
        return true;
    }

    @Override
    public Location get(Object key) {
        //TODO
        return null;
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO
        return null;
    }

    @Override
    public Location remove(Object key) {
        //TODO
        return null;
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO
    }

    @Override
    public void clear() {
        //TODO
    }

    @Override
    public Set<Integer> keySet() {
        //TODO
        return null;
    }

    @Override
    public Collection<Location> values() {
        //TODO
        return null;
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO
        return null;
    }
}
