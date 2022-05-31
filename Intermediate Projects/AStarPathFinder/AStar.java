package AStarPathFinder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AStar extends JComponent implements MouseMotionListener, MouseListener, ActionListener {

    static int boardSize = 10;

    public static void eval() {

        Graph.current.solve();

        mainGame.repaint();
    }

    public static void main(String[] args) {
        AStarNode start = new Point(0, 0);
        AStarNode finish = new Point(10, 6);
        ArrayList<AStarNode> obstacles = new ArrayList<AStarNode>();
        Graph graph = new Graph(obstacles, start, finish, -30, 30, -30, 30);
        Graph.current = graph;
        setup();
        eval();

    }

    public static JButton button(String text, AStar th) {
        JButton button = new JButton(text);
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.LEADING);
        button.addActionListener(th);
        button.setActionCommand(text);
        return button;
    }
    
    boolean setEnd = false;
    boolean setStart = false;

    public AStar() {

        add(button("Clear", this));
        add(button("Start", this));
        add(button("Finish", this));
        add(button("Help", this));
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics graph) {
        if (help) {
            int i = 10;
            graph.drawString("Click (and drag) to add an obstacle (blue).", 10, i);
            i += 15;
            graph.drawString("Click Start/Finish then click on the graph", 10, i);
            i += 15;
            graph.drawString("to set the start/finish of the obstacle.", 0, i);

            i += 15;
            graph.drawString("", 0, i);
        }
        
        for (int row = Graph.current.YMin; row <= Graph.current.YMax; row++) {
            for (int column = Graph.current.XMin; column <= Graph.current.XMax; column++) {
                int columnValue = boardSize * (row - Graph.current.YMin) + 80;
                int rowValue = boardSize * (column - Graph.current.XMin) + 80;
                graph.drawRect(rowValue, columnValue, boardSize, boardSize);
            }
        }

        for (AStarNode finish : Graph.current.path) {
            Point l = (Point) finish;
            int rowSize = boardSize * (l.x - Graph.current.XMin) + 80;
            int columnSize = boardSize * (l.y - Graph.current.YMin) + 80;
            graph.fillRect(rowSize, columnSize, boardSize, boardSize);
        }
        graph.setColor(Color.BLUE);
        
        for (AStarNode line : Graph.current.blocked) {

            int rowSize = boardSize * (((Point) line).x - Graph.current.XMin) + 80;
            int columnSize = boardSize * (((Point) line).y - Graph.current.YMin) + 80;
            graph.fillRect(rowSize, columnSize, boardSize, boardSize);
        }
    }
    
    static AStar mainGame = new AStar();

    public static void setup() {

        JFrame frame = new JFrame("Interactive A* search in Java");

        mainGame.setFocusable(true);
        (frame).setContentPane(mainGame);
        frame.setLayout(new FlowLayout());
        frame.setSize(1000, 700);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    boolean add = true;
    boolean check = false;

    @Override
    public void mouseClicked(MouseEvent arg0) {

        int x = arg0.getX();
        int y = arg0.getY();
        x -= 60;
        y -= 60;
        x /= boardSize;
        y /= boardSize;
        x += Graph.current.XMin;
        y += Graph.current.YMin;

        Point startingPosition = new Point(x, y);
        if (!setStart && !setEnd) {


            if (Graph.current.blocked.contains(startingPosition)) {
                Graph.current.blocked.remove(startingPosition);
            } else {
                Graph.current.blocked.add(new Point(x, y));
            }

        }

        if (setStart) {
            Graph.current.startNode = startingPosition;

        }
        if (setEnd) {
            Graph.current.goalNode = startingPosition;
        }
        setStart = false;
        setEnd = false;
        eval();
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        check = false;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Clear".equals(e.getActionCommand())) {
            Graph.current.blocked.clear();
        }
        if ("Set Start".equals(e.getActionCommand())) {
            setEnd = false;
            setStart = true;
        }
        if ("Set End".equals(e.getActionCommand())) {
            setEnd = true;
            setStart = false;
        }
        if ("Help".equals(e.getActionCommand())) {
            help = !help;

        }
        eval();
    }
    boolean help = false;

    @Override
    public void mouseDragged(MouseEvent arg0) {
        int x = arg0.getX();
        int y = arg0.getY();
        x -= 60;
        y -= 60;
        x /= boardSize;
        y /= boardSize;
        x += Graph.current.XMin;
        y += Graph.current.YMin;

        Point c = new Point(x, y);
        if (!check) {

            check = true;
            add = !Graph.current.blocked.contains(c);
        }
        if (check && add && !Graph.current.blocked.contains(c)) {

            Graph.current.blocked.add(c);
            eval();
        } else {
            if (check && Graph.current.blocked.contains(c) & !add) {
                Graph.current.blocked.remove(c);
                eval();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent m) {
    }
}
