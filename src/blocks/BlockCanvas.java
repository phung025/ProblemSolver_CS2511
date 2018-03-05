/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocks;

import framework_puzzle.Canvas;
import framework_puzzle.State;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class BlockCanvas extends Canvas {

    public BlockCanvas(State aState) {
        super(aState);
        this.setPreferredSize(new Dimension(200, 400));
    }

    /**
     * Paint the current state of the block problem
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        //Fill the background and draw a border around it
        g2.setColor(Color.cyan);
        g2.fillRect(0, 0, 200, 400);

        //Draw the border around
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(0, 0, 200, 400);

        //Draw the base line
        g2.drawLine(20, baseLine, 180, baseLine);

        //Draw the stack name
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("p", 35, baseLine + 15);
        g2.drawString("q", 95, baseLine + 15);
        g2.drawString("r", 155, baseLine + 15);

        //Get current state
        BlockState currentState = (BlockState) this.getCurrentState();

        //Draw all the block
        List<Character> listP = new ArrayList<>(currentState.getP());
        List<Character> listQ = new ArrayList<>(currentState.getQ());
        List<Character> listR = new ArrayList<>(currentState.getR());

        //Draw stack p
        for (int i = 0; i < listP.size(); i++) {

            g2.setColor(new Color((float) 0.82, (float) 0.41, (float) 0.12));
            g2.fillRect(20, baseLine - ((i + 1) * 40), 40, 40);

            g2.setColor(Color.black);
            g2.drawRect(20, baseLine - ((i + 1) * 40), 40, 40);

            g2.drawString(listP.get(i).toString(), 35, baseLine - ((i + 1) * 40) + 25);
        }

        //Draw stack q
        for (int i = 0; i < listQ.size(); i++) {

            g2.setColor(new Color((float) 0.82, (float) 0.41, (float) 0.12));
            g2.fillRect(80, baseLine - ((i + 1) * 40), 40, 40);

            g2.setColor(Color.black);
            g2.drawRect(80, baseLine - ((i + 1) * 40), 40, 40);

            g2.drawString(listQ.get(i).toString(), 95, baseLine - ((i + 1) * 40) + 25);
        }

        //Draw stack r
        for (int i = 0; i < listR.size(); i++) {

            g2.setColor(new Color((float) 0.82, (float) 0.41, (float) 0.12));
            g2.fillRect(140, baseLine - ((i + 1) * 40), 40, 40);

            g2.setColor(Color.black);
            g2.drawRect(140, baseLine - ((i + 1) * 40), 40, 40);

            g2.drawString(listR.get(i).toString(), 155, baseLine - ((i + 1) * 40) + 25);
        }

    }

    //Base line of the stacks
    final private int baseLine = 380;

    /**
     * Note: the size of each block is 40x40, space between the blocks, wall is
     * 20
     */
    //Rectangle of stack p
    Rectangle2D stackP = new Rectangle2D.Double(20, baseLine, 40, 0);

    //Rectangle of stack q
    Rectangle2D stackQ = new Rectangle2D.Double(80, baseLine, 40, 0);

    //Rectangle of stack r
    Rectangle2D stackR = new Rectangle2D.Double(140, baseLine, 40, 0);
}
