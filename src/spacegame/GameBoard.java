package spacegame;

import javax.swing.JFrame;

public class GameBoard extends JFrame {

    public GameBoard(String title) {
        super(title);
    }

    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard("Space Game");
        gameBoard.setResizable(false); // yeniden boyutlanabilirlik
        gameBoard.setFocusable(false); // jframe'e odaklanmayacak, jpanele odaklanacak
        gameBoard.setSize(800, 600);
        gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game game = new Game();
        game.requestFocus(); // klavye için odak veriyor.
        game.addKeyListener(game); // klavyeden işlemlerimizi alır
        game.setFocusable(true); // odağı jpanele verdik
        game.setFocusTraversalKeysEnabled(false); // ?

        gameBoard.add(game);

        gameBoard.setLocation(500, 300);
        gameBoard.setVisible(true);

    }
}
