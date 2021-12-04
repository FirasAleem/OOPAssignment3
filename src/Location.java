import java.util.HashMap;
import java.util.Map;

public class Location {

    /*
     * declare private final locationId, description, exits
     */
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;



    public Location(int locationId, String description, Map<String, Integer> exits) {
        /*
         * set the locationId and the description
         */
        this.locationID = locationId;
        this.description = description;

        /*
         * if exits are not null, set the exit
         * otherwise, set the exit HashMap to (Q,0)
         */
        if (exits.isEmpty()){
            this.exits = new HashMap<>();
            this.exits.put("Q", 0);
        }else {
            this.exits = exits;
        }
    }

    protected void addExit(String direction, int location) {
        /*
         * put the direction and the location in the exits HashMap
         */
        exits.put(direction, location);
    }

    public int getLocationId() {
        /*
         * complete getter to return the location id
         */
        return locationID;
    }

    public String getDescription() {
        /*
         * complete getter to return the description
         */
        return description;
    }

    public Map<String, Integer> getExits() {
        /*
         * complete getter to return a copy of exits
         * (preventing modification of exits from outside the Location instance)
         */
        return exits;
    }
}
