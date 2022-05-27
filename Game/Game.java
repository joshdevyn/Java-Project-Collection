/**Class: GameUI
 * @author Joshua Sims
 * @version 1.0
 *
 * This class will capture information about the Game and references in the Game<> ArrayList in the GameUI() class then print it.
 */

package Game;

import java.util.Scanner;

public class GameUI {
    private Scanner input = new Scanner(System.in);
    public static final int NUM_GAMES = 3;

    /**
     * Method: enterGame
     *
     * Returns Game class object with instance variables matching user input once validated by setters in the Game class
     * @return returns Game class object
     */
    public Game getInformation() {
        Game game = new Game();
        boolean valid;
        do {
            System.out.println("Please enter the name of your game.");
            valid = game.setName(input.nextLine());
        } while (!valid);
        do {
            System.out.println("Please enter the genre for " + game.getName());
            valid = game.setGenre(input.nextLine());
        } while (!valid);
        do {
            System.out.println("Please enter the rating for " + game.getName());
            valid = game.setRating(input.next().charAt(0));
        } while (!valid);
        do {
            System.out.println("Please enter the number of players for " + game.getName());
            valid = game.setNumPlayers(input.nextInt());
            input.nextLine();
        } while (!valid);
        do {
            System.out.println("Please enter the description for " + game.getName());
            valid = game.setDescription(input.nextLine());
        } while (!valid);
        return game;
    }

    /**
     * Method printResults
     *
     * Prints results for Game<> ArrayList. Method not specified in requirements so left out.
     */
    public void printResults(Game[] games) {
        for (Game game : games) {
            System.out.println(game.toString(game.getName(), game.getGenre(), game.getRating(), game.getNumPlayers(), game.getDescription()));
        }
    }

    /**
     * Method: main
     *
     * Contains logic for collecting hobbies, their respective references, and then prints them to the console.
     * @param args
     */
    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        Game[] games = new Game[NUM_GAMES];

        for (int i = 0; i < NUM_GAMES; i++) {
            games[i] = gameUI.getInformation();
        }

        gameUI.printResults(games);

        gameUI.input.close();
    }

}