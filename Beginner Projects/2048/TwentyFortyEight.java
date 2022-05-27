/**Class: TwentyFortyEight.java
 * @author Joshua Sims
 * @version 1.0
 * This class will play the game 2048.
 *
 */

package TwentyFortyEight;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.WindowConstants;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TwentyFortyEight {
    static Board board = new Board();
    static int available = 0;
    static int locationWidth = 0;
    static int move = 0;
    static int widthChange = 1;
    static ArrayList<Integer> score = new ArrayList<Integer>();
    static String scl = "";

    public static void main(String[] args) throws InterruptedException {
        board.randomPlacement();
        board.randomPlacement();
        JFrame frame = new JFrame("2048");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(5000,5000);
        frame.setContentPane(new JComponent() {
            public void paintComponent(Graphics g) {
                g.setFont(new Font("Courier",Font.PLAIN,30));
                board.draw(g);
                g.setFont(new Font("Courier",Font.BOLD,12));
                g.drawString("Available: " + available + "   Width: " + locationWidth + "    Score: " + board.getScore() + "     " + (move == -1 ? "NO MOVE" : Board.moveNames[move]) + "    Scores: " + scl,10,10);
            }
        });
        frame.setVisible(true);
        double avg = test(frame);
        System.out.println("Mode: " + 0);
        System.out.println("Width: " + widthChange);
        System.out.println("Value: " + score);
        System.out.println("Average: " + avg);
        score = new ArrayList<>();
    }
    public static double test(JFrame frame) {
        while (true) {
            available = Board.getNumberOfAvailableSpots(board.board);
            locationWidth = 3;
            move = (int) board.solve(locationWidth,1)[1];
            frame.repaint();
            if (move == -1) {

                System.out.println("GAME OVER: " + board);
                System.exit(0);
            }
            board = board.move(move);
            board.placeRandomly();
        }
    }
    public static int getDepth(int open) {
        switch (open) {
            case 16:
            case 15:
            case 14:
            case 13:
            case 12:
            case 11:
            case 10:
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
                return 3;
            default:
                return 4;
        }
    }
}
