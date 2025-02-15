package blockbreaker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;

    private int totalBlock = 21;
    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private mapGenerator maps;

    public gameplay() {
        maps = new mapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // background color
        g.setColor(Color.blue);
        g.fillRect(1, 1, 692, 592);

        // draw map
        maps.draw((Graphics2D) g);

        // borders
        g.setColor(Color.red);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);


        // score
        g.setColor(Color.red);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score:  "+score, 490, 30);
        // paddle
        g.setColor(Color.yellow);
        g.fillRect(playerX, 550, 100, 8);

        // ball
        g.setColor(Color.green);
        g.fillOval(ballposX, ballposY, 20, 20);

        // If game is finished.
        if(totalBlock <=0){
            play = false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString(" Hurray!!, You won. Your Score: "+score, 150, 300);

            //restart button for game
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString(" Press Enter to Restrart the Game!! ", 180, 330);

           
        }

        if(ballposY >570 ){
            play = false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.green);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString(" Game Over!, Your Score: "+score, 190, 300);

            //restart button for game
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString(" Press Enter to Restrart the Game!! ", 180, 330);

           
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        // making ball move and touch the paddle.
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;
            }

            //run through each block.
            A: for (int i = 0; i < maps.map.length; i++) {
                for (int j = 0; j < maps.map[0].length; j++) {
                    if(maps.map[i][j] > 0){
                        int blockX = j*maps.blockwidth +80;
                        int blockY = i*maps.blockheight +50;
                        int blockwidth = maps.blockwidth;
                        int blockheight = maps.blockheight;


                        Rectangle rect = new Rectangle(blockX, blockY, blockwidth, blockheight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle blockrect = rect;


                        // check intersection of block and ball.
                        if(ballRect.intersects(blockrect)){
                            maps.setBlockValue(0, i, j);
                            totalBlock--;
                            score += 5;

                            if(ballposX +19 <= blockrect.x || ballposX +1 >= blockrect.x + blockrect.width){
                                ballXdir = -ballXdir;
                            }
                            else {
                                ballYdir = - ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            } else if (ballposY < 0) {
                ballYdir = -ballYdir;
            } else if (ballposX > 670) {
                ballXdir = -ballXdir;
            }

        }

        repaint();

        // throw new UnsupportedOperationException("Unimplemented method
        // 'actionPerformed'");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method
        // 'keyReleased'");
    }

    // Setting the paddle movement with the key press.
    @Override
    public void keyPressed(KeyEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposX =120;
                ballposY =350;
                ballXdir = -1;
                ballYdir =-2;
                playerX = 310;
                score=0;
                totalBlock=21;
                maps = new mapGenerator(3, 7);

            }
        }
    }
    // Right movement of paddle.

    public void moveRight() {
        play = true;
        playerX += 10;
    }

    // Left movement of paddle.
    public void moveLeft() {
        play = true;
        playerX -= 10;
    }

}
