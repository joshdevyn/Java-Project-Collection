/**Class: SnackDriver
 * @author: Joshua Sims
 * @version: 1.0
 * File: SnackDriver.java
 *
 * Creates Snack() class objects in Snacks[] class object array, puts them into an array, and then displays their relevant information.
 */

package Snack;

import java.util.Scanner;

/**
 * Class: SnackDriver
 *
 * UI Class for snacks. Gets information for Snack() class object and puts it into Snacks[] class object array and then prints relevant information by calling respective methods.
 */
public class SnackDriver {
    public Scanner input  = new Scanner(System.in);

    /**
     * Method: getInformation
     *
     * Getter for the information for the Snack() class object for the Snacks[] class object array.
     * @return Returns validated and complete Snack() class object
     */
    public Snack getInformation() {
        Snack snack = new Snack();
        boolean valid;
        do {
            System.out.println("Please enter the name of the snack");
            valid = snack.setName(input.nextLine());
            if (!valid) {
                System.out.println("Name cannot be blank");
            }
        } while (!valid);
        do {
            System.out.println("Is " + snack.getName() + " healthy? (Y/N)");
            String check = input.nextLine();
            if (check.equalsIgnoreCase("y") || check.equalsIgnoreCase("n")) {
                if (check.equalsIgnoreCase("y")) {
                    valid = snack.setHealthy(true);
                }
                else {
                    valid = snack.setHealthy(false);
                }
            }
            else {
                System.out.println("Choice must be Y/N");
            }
        } while (!valid);
        do {
            System.out.println("Please enter the calories for " + snack.getName());
            valid = snack.setCalories(input.nextInt());
            input.nextLine();
            if (!valid) {
                System.out.println("Calories must be greater than 0");
            }
        } while (!valid);
        do {
            System.out.println("Please enter the type for " + snack.getName());
            valid = snack.setType(input.nextLine());
            if (!valid) {
                System.out.println("Type must be Crackers, Candy, Nuts, or Fruit");
            }
        } while (!valid);

        return snack;
    }

    /**
     * Method: healthySnack
     *
     * Displays heaalthy snacks from snack[] if they're healthy.
     * @param snacks Snack() class object
     */
    public void healthySnacks (Snack[] snacks) {
        System.out.println("Healthy snacks");
        for(int i = 0; i < snacks.length; i++) {
            if (snacks[i].isHealthy()) {
                System.out.println(snacks[i].getName() + "\s" + snacks[i].isHealthy() + "\s" + snacks[i].getCalories() + "\s" + snacks[i].getType());
            }
        }
        System.out.println();
    }

    /**
     * Method: unhealthySnack
     *
     * Displays unhealthy snacks from snack[] if they're unhealthy.
     * @param snacks Snack() class object
     */
    public void unhealthySnacks (Snack[] snacks) {
        System.out.println("Healthy snacks");
        for(int i = 0; i < snacks.length; i++) {
            if (!snacks[i].isHealthy()) {
                System.out.println(snacks[i].getName() + "\s" + snacks[i].isHealthy() + "\s" + snacks[i].getCalories() + "\s" + snacks[i].getType());
            }
        }
        System.out.println();
    }

    /**
     * Method: main
     *
     * Contains logic implementation intialize Snacks[] and SnackDriver() array/objects, prints their relevant information, then closes Scanner instance.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Snack snacks[] = new Snack[3];
        SnackDriver sd = new SnackDriver();

        for(int i = 0; i < snacks.length; i++) {
            snacks[i] = sd.getInformation();
        }

        sd.healthySnacks(snacks);
        sd.unhealthySnacks(snacks);

        System.out.println("All Snacks");
        for(int i = 0; i < snacks.length; i++) {
            System.out.println(snacks[i].getName() + "\s" + snacks[i].isHealthy() + "\s" + snacks[i].getCalories() + "\s" + snacks[i].getType());
        }

        sd.input.close();
    }
}