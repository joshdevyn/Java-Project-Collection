/**Class: GameDriver
 * @author Joshua Sims
 * @version 1.0
 *
 * This class will capture information about the Game and references in the Game<> ArrayList in the GameUI() class then print it.
 */

package Game;

class GameDriver {
    private String name;
    private String genre;
    private char rating;
    private int numPlayers;
    private String description;

    /**
     * Method: Game
     *
     * Empty Constructor for Game() class object
     */
    public GameDriver() {
        this.name = "";
        this.genre = "";
        this.rating = ' ';
        this.numPlayers = 0;
        this.description = "";
    }

    /**
     * Method: Game
     *
     * Non-Empty constructor for Game() class object
     * @param name name of Game for instance
     * @param genre cateogry of Game for instance
     */
    public GameDriver(String name, String genre, char rating, int numPlayers, String description) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.numPlayers = numPlayers;
        this.description = description;
    }

    /**
     * Method: getName
     *
     * Returns the name for the Game
     * @return name of Game for instance
     */
    public String getName() {
        return name;
    }

    /**
     * Method: setName
     *
     * Validates name and sets it accordingly
     * @param name name of Game for instance
     * @return returns true if name is valid
     */
    public boolean setName(String name) {
        boolean valid = name.length() > 0;
        if (valid) {
            this.name = name;
        }
        else {
            System.out.println("You must enter a name.");
        }
        return valid;
    }

    /**
     * Method: getgenre
     *
     * Returns the genre of the Game
     * @return genre of Game for instance
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Method: setgenre
     *
     * Validates genre and sets it accordingly
     * @param genre name of genre for instnace
     * @return returns true if genre is valid
     */
    public boolean setGenre(String genre) {
        boolean valid = genre.equalsIgnoreCase("Puzzle") ||
                genre.equalsIgnoreCase("Shooter") ||
                genre.equalsIgnoreCase("Arcade");
        if (valid) {
            this.genre = genre;
        }
        else {
            System.out.println("genre must be 'Puzzle', 'Shooter', or 'Arcade'.");
        }
        return valid;
    }

    /**
     * Method: getRating
     *
     * Returns the rating of the game
     * @return rating of Game for instance
     */
    public char getRating() {
        return rating;
    }

    /**
     * Method: setRating
     *
     * Validates rating and sets it accordingly
     * @param rating name of rating for instance
     * @return returns true if rating is valid
     */
    public boolean setRating(char rating) {
        boolean valid = rating == 'E' ||
                rating == 'T' ||
                rating == 'M' ||
                rating == 'e' ||
                rating == 't' ||
                rating == 'm';
        if (valid) {
            this.rating = Character.toUpperCase(rating);
        }
        else {
            System.out.println("Rating should be a character, 'E', 'T' or 'M'");
        }
        return valid;
    }

    /**
     * Method: getNumPlayers
     *
     * Returns the numPlayers of the game
     * @return numbers of players of Game for instance
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Method: setNumPlayers
     *
     * Validates numPlayers and sets it accordingly
     * @param numPlayers number of players for instance
     * @return returns true if number of players is valid
     */
    public boolean setNumPlayers(int numPlayers) {
        boolean valid = numPlayers >= 1 && numPlayers <= 10;
        if (valid) {
            this.numPlayers = numPlayers;
        }
        else {
            System.out.println("Number of players should be between 1 and 10 inclusive");

        }
        return valid;
    }

    /**
     * Method: getDescription
     *
     * Returns the description of the game
     * @return description of Game for instance
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method: setDescription
     *
     * Validates description and sets it accordingly
     * @param description description for instance
     * @return returns true if description is valid
     */
    public boolean setDescription(String description) {
        boolean valid = description.length() > 0;
        if (valid) {
            this.description = description;
        }
        else {
            System.out.println("Description cannot be blank.");
        }
        return valid;
    }

    /**
     * Method: toString
     *
     * Returns a formatted string with relevant information from instance for Game class object
     * @param name name of the Game for the instance
     * @param genre genre of the Game for the instance
     * @return returns formatted string
     */
    public String toString (String name, String genre, char rating, int numPlayers, String description) {
        return "Game name = " + name + " genre = " + genre + ", rating = " + rating + ", numPlayers = " + numPlayers + "/n"
                + "Description = " + description;
    }
}