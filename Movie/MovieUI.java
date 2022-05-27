/**Class: MovieUI
 * @author Joshua Sims
 * @version 1.0
 *
 * This class will capture information about movies then print them.
 */

package Movie;

public class MovieUI {
    public String title;
    public String genre;
    public ArrayList<String> actors;
    public int minutes;

    /**
     * Method: Movie
     *
     * Argument constructor for Movie
     * @param title Title of the movie
     * @param genre Genre of the movie
     * @param minutes Length of movie in minutes
     */
    public MovieUI(String title, String genre, ArrayList<String> actors, int minutes) {
        this.title = title;
        this.genre = genre;
        this.actors = actors;
        this.minutes = minutes;
    }

    /**
     * Method: Movie
     *
     * No argument constructor
     */
    public MovieUI() {
        this.title = "";
        this.genre = "";
        this.actors = new ArrayList<>();
        this.minutes = 0;
    }

    /**
     * Method: getTitle
     *
     * Returns title of the movie
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method: setTitle
     *
     * Validates and sets movie title
     * @param title
     * @return
     */
    public boolean setTitle(String title) {
        boolean valid = title.length() >= 4 && title.length() <= 50;
        if (title.length() == 0){
            return valid = true;
        }
        else if (valid) {
            this.title = title;
        }
        else {
            System.out.println("Title must be between 4 characters and 50 characters.");
        }
        return valid;
    }

    /**
     * Method: getGenre
     *
     * Returns genre of the movie
     * @return
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Method: setGenre
     *
     * Validates and sets the genre for the movie
     * @param genre
     * @return
     */
    public boolean setGenre(String genre) {
        boolean valid = genre.equalsIgnoreCase("Action") || genre.equalsIgnoreCase("Drama") || genre.equalsIgnoreCase("Romance") || genre.equalsIgnoreCase("Science Fiction");
        if (valid) {
            this.genre = genre;
        }
        else {
            System.out.println("Genre must either be Action, Drama, Romance, or Science Fiction");
        }
        return valid;
    }

    /**
     * Method: addActor
     *
     * Validates and adds actor to actor array if it matches format of "String, String" exactly
     * @param actor
     * @return
     */
    public boolean addActor(String actor) {
        Pattern p = Pattern.compile("\\w+,\\s\\w+");
        Matcher m = p.matcher(actor);
        boolean valid = m.matches();
        if (valid) {
            this.actors.add(actor);
        }
        else {
            System.out.println("Actor must be in format Last Name, First Name and cannot be blank");
        }
        return valid;
    }

    /**
     * Method: setActor
     *
     * Validates whether or not to add another actor to the array
     * @param choice
     * @return
     */
    public boolean setActor(String choice) {
        boolean valid = choice.equalsIgnoreCase("n");
        if (!valid && !choice.equalsIgnoreCase("y")) {
            System.out.println("Choice must be Y or N");
        }
        return valid;
    }

    /**
     * Method: getMinutes
     *
     * Returns length of movie in minutes
     * @return
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Method: setMinutes
     *
     * Validates and sets minutes for movie length
     * @param minutes
     * @return
     */
    public boolean setMinutes(int minutes) {
        boolean valid = (minutes >= 60) && (minutes <= 210);
        if (valid) {
            this.minutes = minutes;
        }
        else {
            System.out.println("Minutes must be between 60 and 210, inclusive");
        }
        return valid;
    }

    /**
     * Method: toString
     *
     * Returns formatted String contained all movie information
     * @param title
     * @param genre
     * @param minutes
     * @return
     */
    public String toString(String title, String genre, int minutes) {
        String string = "Title = " + title + "\nActors: \n";
        for (int i = 0; i < actors.size(); i++) {
            string += "\t" + actors.get(i) + "\n";
        }
        string += "Genre = " + genre + "\nMinutes = " + minutes;
        return string;
    }
}