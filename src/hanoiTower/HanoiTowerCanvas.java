/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoiTower;

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
public class HanoiTowerCanvas extends Canvas {

    public HanoiTowerCanvas(State aState) {
        super(aState);
        this.setPreferredSize(new Dimension(500, 300));
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
        g2.setColor(Color.white);
        g2.fillRect(0, 0, 500, 300);

        //Draw the border around
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(0, 0, 500, 300);

        //Draw the base line
        g2.drawLine(10, baseLine, 490, baseLine);

        //Draw the stack name
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("p", 96, baseLine + 15);
        g2.drawString("q", 246, baseLine + 15);
        g2.drawString("r", 396, baseLine + 15);

        //Get current state
        HanoiTowerState currentState = (HanoiTowerState) this.getCurrentState();

        //Draw all the block
        List<Integer> listP = new ArrayList<Integer>(currentState.getP());
        List<Integer> listQ = new ArrayList<Integer>(currentState.getQ());
        List<Integer> listR = new ArrayList<Integer>(currentState.getR());

        g2.setStroke(new BasicStroke(3));

        //Draw stack p
        for (int i = 0; i < listP.size(); i++) {

            g2.setColor(new Color((float)0, (float)0.502, (float)1, 1));
            g2.fillRect(100 - (listP.get(i) * 15 / 2), baseLine - ((i + 1) * 10), listP.get(i) * 15, 10);

            g2.setColor(Color.black);
            g2.drawRect(100 - (listP.get(i) * 15 / 2), baseLine - ((i + 1) * 10), listP.get(i) * 15, 10);

        }

        //Draw stack q
        for (int i = 0; i < listQ.size(); i++) {

            g2.setColor(new Color((float)0, (float)0.502, (float)1, 1));
            g2.fillRect(250 - (listQ.get(i) * 15 / 2), baseLine - ((i + 1) * 10), listQ.get(i) * 15, 10);

            g2.setColor(Color.black);
            g2.drawRect(250 - (listQ.get(i) * 15 / 2), baseLine - ((i + 1) * 10), listQ.get(i) * 15, 10);
        }

        //Draw stack r
        for (int i = 0; i < listR.size(); i++) {

            g2.setColor(new Color((float)0, (float)0.502, (float)1, 1));
            g2.fillRect(400 - (listR.get(i) * 15 / 2), baseLine - ((i + 1) * 10), listR.get(i) * 15, 10);

            g2.setColor(Color.black);
            g2.drawRect(400 - (listR.get(i) * 15 / 2), baseLine - ((i + 1) * 10), listR.get(i) * 15, 10);
        }

    }

    //Base line of the stacks
    final private int baseLine = 280;

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
