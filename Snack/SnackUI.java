/**Class: SnackUI
 * @author Joshua Sims
 * @version 1.0
 *
 * Creates Snack() class objects, constructs parameters, then gets and sets them.
 */

package Snack;

/**
 * Class: Snack
 *
 * Business class for program. Validates parameters for instance and provides gets/sets/constructs.
 */
public class SnackUI {
    private String name;
    private boolean healthy;
    private int calories;
    private String type;

    /**
     * Method: Snack
     *
     * Empty Constructor for Snack class object
     */
    public SnackUI() {
        this.name = "Peanut butter crackers";
        this.healthy = true;
        this.calories = 180;
        this.type = "Crackers";
    }

    /**
     * Method: Snack
     *
     * Non-Empty Constructor for Snack class object
     * @param name Name of snack in instance.
     * @param healthy Boolean value for healthiness of snack in instance.
     * @param calories Integer value for calories of snack in instance.
     * @param type Type of snack in instance.
     */
    public SnackUI(String name, boolean healthy, int calories, String type) {
        this.name = name;
        this.healthy = healthy;
        this.calories = calories;
        this.type = type;
    }

    /**
     * Method: getName
     *
     * Getter for name of snack.
     * @return Returns name of snack for instance.
     */
    public String getName() {
        return name;
    }

    /**
     * Method: setName
     *
     * Setter for name of snack. Validates name and returns boolean.
     * @param name Name of snack in instance.
     * @return Returns boolean value for valid/invalid name.
     */
    public boolean setName(String name) {
        boolean valid  = !name.isBlank();
        if (valid) {
            this.name = name;
        }
        return valid;
    }

    /**
     * Method: isHealthy
     *
     * Getter for boolean healthy.
     * @return Returns boolean healthy.
     */
    public boolean isHealthy() {
        return healthy;
    }

    /**
     * Method: setHealthy
     *
     * Setter for healthy. Validates if snack is healthy and returns boolean accordingly.
     * @param healthy Boolean value for healthiness of snack in instance.
     * @return Returns boolean value for valid/invalid healthy boolean.
     */
    public boolean setHealthy(boolean healthy) {
        boolean valid = (healthy == true || healthy == false);
        if (valid) {
            this.healthy = healthy;
        }
        return valid;
    }

    /**
     * Method: getCalories
     *
     * Getter for calories for snack.
     * @return Returns calorie count for snack in instance.
     */
    public int getCalories() {
        return calories;
    }

    /**
     * Method: setCalories
     *
     * Setter for calories for snack. Validates calories and returns boolean accordingly.
     * @param calories Integer value for calories of snack in instance.
     * @return Returns boolean value for valid/invalid calorie count.
     */
    public boolean setCalories(int calories) {
        boolean valid = calories > 0;
        if (valid) {
            this.calories = calories;
        }
        return valid;
    }

    /**
     * Method: getType
     *
     * Getter for the type of snack.
     * @return Returns type of snack in instance.
     */
    public String getType() {
        return type;
    }

    /**
     * Method: setType
     *
     * Setter for the type of snack. Validates snack type and returns boolean accordingly.
     * @param type Type of snack in instance.
     * @return Returns boolean value for valid/invalid type.
     */
    public boolean setType(String type) {
        boolean valid = (type.equalsIgnoreCase("candy")
                || type.equalsIgnoreCase("crackers")
                || type.equalsIgnoreCase("nuts")
                || type.equalsIgnoreCase("fruit"));
        if (valid) {
            this.type = type;
        }
        return valid;
    }
}