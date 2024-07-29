package blockbreaker;

import javax.swing.JFrame;

public class main{
    public static void main(String[] args) {

        //Use JFrame object for a new window frame.
        JFrame obj1 = new JFrame();
        gameplay gamePlay = new gameplay();

        // Set frame properties.
        obj1.setBounds(10, 10, 708, 605);
        obj1.setTitle("Breaking Ball");
        obj1.setResizable(false);
        obj1.setVisible(true);
        obj1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj1.add(gamePlay);

    }
}