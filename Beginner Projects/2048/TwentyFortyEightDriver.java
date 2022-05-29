/**Class: TwentyFortyEightDriver.java
 * @author Joshua Sims
 * @version 1.0
 * This class will drive the game 2048.
 *
 */

package TwentyFortyEight;

import java.util.Random;
import java.awt.Graphics;

public class Game {
    byte[][] gameBoard;
    int gameScore;
    static final String[] moves = new String[] {"UP","DOWN","LEFT","RIGHT"};
    public Game() {
        gameBoard = new byte[4][4];
        gameScore = 0;
    }
    public Game(byte[][] gameBoard, int gameScore) {
        this(gameBoard,gameScore,false);
    }
    public Game(byte[][] gameBoard, int score, boolean copy) {
        if (copy) {
            this.gameBoard = new byte[4][4];
            for (int row = 0; row < 4; row++) {
                System.arraycopy(gameBoard[row],0,this.gameBoard[row],0,4);
            }
        } else {
            this.gameBoard = gameBoard;
        }
        this.gameScore = score;
    }
    public Board move(int dir) {
        int temporaryScore = gameScore;
        byte[][] res = new byte[4][4];
        switch (dir) {
            case 0: //UP
                for (int column = 0; column < 4; column++) {
                    byte[] column = new byte[] {gameBoard[0][column], gameBoard[1][column], gameBoard[2][column], gameBoard[3][column]};
                    temporaryScore += rowMove(column);
                    res[0][column] = column[0];
                    res[1][column] = column[1];
                    res[2][column] = column[2];
                    res[3][column] = column[3];
                }
                break;
            case 1:
                //DOWN
                for (int column = 0; column < 4; column++) {
                    byte[] col = new byte[] {gameBoard[3][column], gameBoard[2][column], gameBoard[1][column], gameBoard[0][column]};
                    temporaryScore += rowMove(col);
                    res[0][column] = col[3];
                    res[1][column] = col[2];
                    res[2][column] = col[1];
                    res[3][column] = col[0];
                }
                break;
            case 2://LEFT
                for (int row = 0; row < 4; row++) {
                    byte[] ro = new byte[] {gameBoard[row][0], gameBoard[row][1], gameBoard[row][2], gameBoard[row][3]};
                    temporaryScore += rowMove(ro);
                    res[row] = ro;
                }
                break;
            case 3://RIGHT
                for (int row = 0; row < 4; row++) {
                    byte[] ro = new byte[] {gameBoard[row][3], gameBoard[row][2], gameBoard[row][1], gameBoard[row][0]};
                    temporaryScore += rowMove(ro);
                    res[row][0] = ro[3];
                    res[row][1] = ro[2];
                    res[row][2] = ro[1];
                    res[row][3] = ro[0];
                }
        }
        return new Game(res,temporaryScore);
    }
    public static int rowMove(byte[] x) {
        for (int i = 0; i < 3; i++) {
            if (x[i] == 0 && x[i + 1] != 0) {
                x[i] = x[i + 1];
                x[i + 1] = 0;
                i = i == 0 ? -1 : i - 2;
            }
        }
        int score = 0;
        for (int i = 0; i < 3; i++) {
            if (x[i] == x[i + 1] && x[i] != 0) {
                x[i]++;
                score += Math.pow(2,x[i]);
                x[i + 1] = 0;
                for (int j = i + 1; j < x.length - 1; j++) {
                    if (x[j] == 0 && x[j + 1] != 0) {
                        x[j] = x[j + 1];
                        x[j + 1] = 0;
                        j = j == 0 ? -1 : j - 2;
                    }
                }
            }
        }
        return score;
    }
    public static ArrayList<int[]> getOpenLocations(byte[][] board) {
        ArrayList<int[]> openLocations = new ArrayList<>();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (board[row][column] == 0) {
                    openLocations.add(new int[] {row,column});
                }
            }
        }
        return openLocations;
    }
    public static int getNumOpenLocations(byte[][] board) {
        int num = 0;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (board[row][column] == 0) {
                    num++;
                }
            }
        }
        return num;
    }
    public static boolean isUsed(byte[][] board) {
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (board[row][column] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    public static int[] getRandomOpenLocation(byte[][] board) {
        ArrayList<int[]> openLocations = getOpenLocations(board);
        if (openLocations.isEmpty()) {
            return null;
        }
        return openLocations.get(new Random().nextInt(openLocations.size()));
    }
    public void placeRandomly() {
        int[] Location = getRandomOpenLocation(gameBoard);
        if (Location == null) {
            return;
        }
        gameBoard[Location[0]][Location[1]] = (byte) (new Random().nextInt(10) == 0 ? 2 : 1);
    }
    public double getScore1() {
        return getNumOpenLocations(gameBoard);
    }
    public double getScore() {
        return gameScore * getNumOpenLocations(gameBoard);
    }
    public double getScore2() {
        double score = 0;
        int numOpen = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                double sc = Math.pow(2, gameBoard[x][y]);
                if ((x == 1 || x == 2) && (y == 1 || y == 2)) {
                    sc = -sc;
                }
                score += sc;
            }
        }
        return score * getNumOpenLocations(gameBoard);
    }
    public double getScore3() {
        double score = 0;
        byte scoreCap = 3;
        double base = 1.2;
        int openNumber = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (gameBoard[x][y] > scoreCap) {
                    double sc = Math.pow(base, gameBoard[x][y]);
                    if ((x == 1 || x == 2) && (y == 1 || y == 2)) {
                        sc = -sc * 0.4;
                    }
                    score += sc;
                }
                if (gameBoard[x][y] == 0) {
                    openNumber++;
                }
            }
        }
        score = score * getScoreMultiplier(getNumOpenLocations(gameBoard));
        //score += openNumber;
        return score;
    }
    public static double getScoreMultiplier(int numOpen) {
        switch (numOpen) {
            case 1:
                return 0.6;
            case 2:
                return 0.7;
            case 3:
                return 0.8;
            case 4:
                return 0.9;
            default:
                return 1;
        }
    }
    public double[] solve(int depth,double weight) {
        if (depth == 0) {
            if (isUsed(gameBoard)) {
                for (int i = 0; i < 4; i++) {
                    if (move(i).gameScore != gameScore) {//Checking if the move happened
                        return new double[] {weight * getScore(),-1};
                    }
                }
                return new double[] {-100 * weight,-1};//Unable to move
            }
            return new double[] {weight * getScore(),-1};
        }
        double maxScore = -1000000000;
        int maxMove = -1;
        int numcont = 0;
        for (int move = 0; move < 4; move++) {
            ArrayList<Board> open = new ArrayList<>();
            ArrayList<Double> weights = new ArrayList<>();
            Board result = move(move);
            if (result.equal(gameBoard)) {
                numcont++;
                continue;
            }
            ArrayList<int[]> openLocations = getOpenLocations(result.board);
            double w = 1 / ((double) openLocations.size());
            for (int[] coord : openLocations) {
                Board tmp2 = new Game(result.board,result.gameScore,true);
                tmp2.board[coord[0]][coord[1]] = 2;
                open.add(tmp2);
                weights.add(weight * 0.9 * w);
                Board tmp4 = new Game(result.board,result.gameScore,true);
                tmp4.board[coord[0]][coord[1]] = 4;
                open.add(tmp4);
                weights.add(weight * 0.1 * w);
            }
            double scoreForMove = 0;
            for (int i = 0; i < open.size(); i++) {
                Board b = open.get(i);
                double boardScore = b.solve(depth - 1,weights.get(i))[0];
                scoreForMove += boardScore;
            }
            if (scoreForMove > maxScore || maxMove == -1) {
                maxScore = scoreForMove;
                maxMove = move;
            }
        }
        return new double[] {maxScore,maxMove};
    }
    public String toString() {
        int[][] b = new int[4][4];
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                b[x][y] = (int) (gameBoard[x][y] == 0 ? 0 : Math.pow(2, gameBoard[x][y]));
            }
        }
        return gameScore + "\n" + b[0][0] + " " + b[0][1] + " " + b[0][2] + " " + b[0][3] + "\n" + b[1][0] + " " + b[1][1] + " " + b[1][2] + " " + b[1][3] + "\n" + b[2][0] + " " + b[2][1] + " " + b[2][2] + " " + b[2][3] + "\n" + b[3][0] + " " + b[3][1] + " " + b[3][2] + " " + b[3][3] + "\n";
    }
    public void draw(Graphics g) {
        g.drawString("Score: " + gameScore,50,50);
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                g.drawString((int) (gameBoard[row][column] == 0 ? 0 : Math.pow(2, gameBoard[row][column])) + "",column * 100 + 100,row * 100 + 100);
            }
        }
    }
    public boolean equal(byte[][] oth) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (oth[x][y] != gameBoard[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }
}
