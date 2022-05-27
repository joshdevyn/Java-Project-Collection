/**Class: MovieDriver
 * @author Joshua Sims
 * @version 1.0
 *
 * This class will capture information about movies then print them.
 */

package Movie;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieDriver {

    public Scanner input = new Scanner(System.in);

    /**
     * Method: printResults
     *
     * Prints the results of for each movie object
     * @param movie
     */
    public void printResults(Movie movie) {
            System.out.println(movie.toString(movie.getTitle(), movie.getGenre(), movie.getMinutes()));
    }

    /**
     * Method: getMinutes
     *
     * Gets information for minutes for movie length
     * @param mov
     */
    public void getMinutes(Movie mov) {
        boolean valid;
        do {
            System.out.println("Please enter the time for " + mov.getTitle());
            valid = mov.setMinutes(input.nextInt());
            input.nextLine();
        } while (!valid);
    }

    /**
     * Method: getGenre
     *
     * Gets information for genre
     * @param mov
     */
    public void getGenre(Movie mov) {
        boolean valid;
        do {
            System.out.println("Please enter the genre for " + mov.getTitle());
            valid = mov.setGenre(input.nextLine());
        } while (!valid);
    }

    /**
     * Method: getActors
     *
     * Gets information for actor(s)
     * @param mov
     */
    public void getActors(Movie mov) {
        boolean valid;
        do {
            boolean condition;
            do {
                System.out.println("Please enter the actor(s) for " + mov.getTitle());
                condition = mov.addActor(input.nextLine());
            } while (!condition);
            System.out.println("Do you want to enter another actor?");
            String check = input.nextLine();
            valid = mov.setActor(check);
        } while (!valid);
    }

    /**
     * Method: getTitle
     *
     * Gets information for title
     * @param mov
     */
    public void getTitle(Movie mov) {
        boolean valid;
        System.out.println("Please enter the title for the movie");
        do {
            valid = mov.setTitle(input.nextLine());
        } while (!valid);
    }

    /**
     * Method: main
     *
     * Contains logic for getting information for movies from methods and prints them then closes the scanner
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        MovieDriver md = new MovieDriver();
        int i = 0;

        do {
            Movie mov = new Movie();
            movies.add(mov);

            md.getTitle(mov);

            if (movies.get(i).getTitle().length() > 0) {
                md.getActors(mov);
                md.getGenre(mov);
                md.getMinutes(mov);
                movies.set(i, new Movie(mov.getTitle(), mov.getGenre(), mov.actors, mov.getMinutes()));
            }

            i++;

        } while (!(movies.get(movies.size()-1).getTitle().length() < 1));

        if (movies.size() > 1) {
            for (int index = 0; index < movies.size() - 1; index++) {
                md.printResults(movies.get(index));
            }
        }

        md.input.close();
    }
}
