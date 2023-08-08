package spacegame;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Game extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(0, this);

    private int time = 0;
    private int fire = 0;
    private BufferedImage image;
    private ArrayList<Fire> fireArrayList = new ArrayList<>();
    private int fireMoveY = 10; // ateşin her saniyede bir kademe yukarı çıkması
    private int ballX = 0;
    private int ballMoveX = 10; // sağa sola gitme
    private int spaceShipX = 0;
    private int spaceShipMoveX = 20;

    public boolean control() {
        for (Fire fire : fireArrayList) {
            if (new Rectangle(fire.getX(), fire.getY(), 10, 20).intersects(new Rectangle(ballX, 0, 20, 20))) {
                return true;
            }
        }
        return false;
    }

    public Game() {

        try {
            image = ImageIO.read(new FileInputStream(new File("images/spaceShip.png")));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setBackground(Color.black);

        timer.start();

    }

    @Override
    public void repaint() {
        super.repaint(); // Generated from
                         // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Generated from
                        // nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

        time += 5;

        g.setColor(Color.red);
        g.fillOval(ballX, 0, 20, 20);
        g.drawImage(image, spaceShipX, 490, image.getWidth() / 10, image.getHeight() / 10, this);

        for (Fire fire : fireArrayList) {
            if (fire.getY() < 0) {
                fireArrayList.remove(fire);
            }
        }

        g.setColor(Color.blue);

        for (Fire fire : fireArrayList) {
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }

        if (control()) {
            timer.stop();
            String message = "You win!\nFire: " + fire + "\nTime: " + time / 1000.0 + " seconds";
            JOptionPane.showMessageDialog(this, message);

            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Fire fire : fireArrayList) {
            fire.setY(fire.getY() - fireMoveY);
        }

        ballX += ballMoveX;
        if (ballX >= 750) {
            ballMoveX = -ballMoveX;
        }
        if (ballX <= 0) {
            ballMoveX = -ballMoveX;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {

            if (spaceShipX <= 0) {

            } else {
                spaceShipX -= spaceShipMoveX;
            }

        } else if (c == KeyEvent.VK_RIGHT) {

            if (spaceShipX >= 720) {

            } else {
                spaceShipX += spaceShipMoveX;
            }

        } else if (c == KeyEvent.VK_CONTROL) {

            fireArrayList.add(new Fire(spaceShipX + 15, 470));
            fire++;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
