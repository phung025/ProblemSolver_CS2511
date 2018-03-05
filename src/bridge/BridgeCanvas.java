/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import framework_puzzle.Canvas;
import framework_puzzle.State;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

/**
 *
 * @author user
 */
public class BridgeCanvas extends Canvas {

    /**
     * Public constructor of the bridge canvas, take in the current state to
     * draw it on the screen
     *
     * @param state
     */
    public BridgeCanvas(State state) {
        super(state);
        this.setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void paintComponent(Graphics g) {

        //Draw the scene of the problem
        drawScenary(g);

        //Draw positions of people
        drawPeopleAndTime(g);

    }

    /**
     * Draw background, river, and bridge using the current graphics context
     * @param g 
     */
    private void drawScenary(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        //width and height of the map, should be 400x400
        final int HEIGHT = this.getSize().height;
        final int WIDTH = this.getSize().width;

        g.setColor(new Color(0, (float) 0.6, 0, 1));
        g.fillRect(0, 0, HEIGHT, WIDTH);

        //Draw river border & color it
        g.setColor(Color.blue);

        //Draw left border of river
        riverPath.moveTo(150, 0);
        riverPath.quadTo(100, 150, 150, 200);
        riverPath.quadTo(200, 250, 150, 300);
        riverPath.quadTo(50, 350, 150, HEIGHT);

        riverPath.lineTo(250, HEIGHT);

        //Draw right border of river
        riverPath.quadTo(200, 300, 250, 200);
        riverPath.quadTo(260, 150, 220, 50);
        riverPath.quadTo(200, 25, 210, 0);

        //Color the river
        riverPath.closePath();
        g2.draw(riverPath);
        g2.fill(riverPath);

        //Draw the bridge
        bridgePath.moveTo(0, 160);
        bridgePath.lineTo(140, 160);
        bridgePath.quadTo(200, 130, 260, 160);
        bridgePath.lineTo(WIDTH, 160);
        bridgePath.lineTo(WIDTH, 240);
        bridgePath.lineTo(260, 240);
        bridgePath.quadTo(200, 210, 140, 240);
        bridgePath.lineTo(0, 240);
        bridgePath.closePath();

        //Draw bridge border
        g2.setColor(Color.gray);
        g2.setStroke(new BasicStroke(2));
        g2.draw(bridgePath);

        //Fill bridge color
        g2.setColor(new Color((float) 0.41, (float) 0.41, (float) 0.41, 1));
        g2.fill(bridgePath);
    }

    /**
     * Draw people and timer using the graphics context of the object
     * @param g 
     */
    private void drawPeopleAndTime(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g2.setColor(Color.black);

        //Get current state
        BridgeState currentState = (BridgeState) this.getCurrentState();

        //Text representation
        String p1 = "P1";
        String p2 = "P2";
        String fl = "FL";
        String p5 = "P5";
        String p10 = "P10";
        
        //Draw P1
        if (currentState.getP1Position() == Position.WEST) {
            g2.drawString(p1, 20, 110);
        } else {
            g2.drawString(p1, 340, 110);
        }

        //Draw P2
        if (currentState.getP2Position() == Position.WEST) {
            g2.drawString(p2, 20, 160);
        } else {
            g2.drawString(p2, 340, 160);
        }

        //Draw Flash light
        if (currentState.getFlashlightPosition() == Position.WEST) {
            g2.drawString(fl, 20, 210);
        } else {
            g2.drawString(fl, 340, 210);
        }

        //Draw P5
        if (currentState.getP5Position() == Position.WEST) {
            g2.drawString(p5, 20, 260);
        } else {
            g2.drawString(p5, 340, 260);
        }

        //Draw P10
        if (currentState.getP10Position() == Position.WEST) {
            g2.drawString(p10, 20, 310);
        } else {
            g2.drawString(p10, 340, 310);
        }
       
        //Draw time
        String stateString = currentState.toString();
        String currentTime = stateString.substring(stateString.indexOf(':')+1, stateString.length()-2);
        currentTime = currentTime.trim();
       
        g2.drawString(currentTime, 130, 210);
    }
    
    /**
     * Instance fields
     */
    
    /**
     * General path object used for drawing river border
     */
    private final GeneralPath riverPath = new GeneralPath();
    
    /**
     * General path object used for drawing bridge border
     */
    private final GeneralPath bridgePath = new GeneralPath();
}
