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
public class HanoiTowerCanvasIconified extends Canvas {

    public HanoiTowerCanvasIconified(State aState) {
        super(aState);
        this.setPreferredSize(new Dimension(300, 130));
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
        g2.fillRect(0, 0, 300, 130);

        //Draw the border around
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(0, 0, 300, 130);

        //Draw the base line
        g2.drawLine(10, baseLine, 290, baseLine);

        //Draw the stack name
        g2.setFont(new Font("Arial", Font.BOLD, 15));
        g2.drawString("p", 50, baseLine + 15);
        g2.drawString("q", 150, baseLine + 15);
        g2.drawString("r", 250, baseLine + 15);

        //Get current state
        HanoiTowerState currentState = (HanoiTowerState) this.getCurrentState();

        //Draw all the block
        List<Integer> listP = new ArrayList<Integer>(currentState.getP());
        List<Integer> listQ = new ArrayList<Integer>(currentState.getQ());
        List<Integer> listR = new ArrayList<Integer>(currentState.getR());

        g2.setStroke(new BasicStroke(2));

        //Draw stack p
        for (int i = 0; i < listP.size(); i++) {

            g2.setColor(new Color((float)0, (float)0.502, (float)1, 1));
            g2.fillRect(55 - (listP.get(i) * 7 / 2), baseLine - ((i + 1) * 5), listP.get(i) * 7, 5);

            g2.setColor(Color.black);
            g2.drawRect(55 - (listP.get(i) * 7 / 2), baseLine - ((i + 1) * 5), listP.get(i) * 7, 5);

        }

        //Draw stack q
        for (int i = 0; i < listQ.size(); i++) {

            g2.setColor(new Color((float)0, (float)0.502, (float)1, 1));
            g2.fillRect(155 - (listQ.get(i) * 7 / 2), baseLine - ((i + 1) * 5), listQ.get(i) * 7, 5);

            g2.setColor(Color.black);
            g2.drawRect(155 - (listQ.get(i) * 7 / 2), baseLine - ((i + 1) * 5), listQ.get(i) * 7, 5);
        }

        //Draw stack r
        for (int i = 0; i < listR.size(); i++) {

            g2.setColor(new Color((float)0, (float)0.502, (float)1, 1));
            g2.fillRect(255 - (listR.get(i) * 7 / 2), baseLine - ((i + 1) * 5), listR.get(i) * 7, 5);

            g2.setColor(Color.black);
            g2.drawRect(255 - (listR.get(i) * 7 / 2), baseLine - ((i + 1) * 5), listR.get(i) * 7, 5);
        }

    }

    //Base line of the stacks
    final private int baseLine = 110;
}
