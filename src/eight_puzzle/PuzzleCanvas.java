/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eight_puzzle;

import framework_puzzle.Canvas;
import framework_puzzle.State;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * This class represents the graphical state of the 8-puzzle problem
 *
 * @author Nam Phung
 */
public class PuzzleCanvas extends Canvas {

    /**
     * Parameterized constructor takes in the current state and use it to draw
     * the state of the problem
     *
     * @param currentState
     */
    public PuzzleCanvas(State currentState) {
        super(currentState);
        this.setPreferredSize(new Dimension(220,220));
    }

    /**
     * Paint the state of 8-puzzle problem
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        //Paint all tiles
        PuzzleState currentState = (PuzzleState) super.getCurrentState();
        char[] currentTiles = currentState.getTiles();

        //For every tile v in the set, draw & paint v
        for (int i = 0; i < currentTiles.length; ++i) {
            int x = 25 + (60 * (i % 3));
            int y = 25 + (60 * (i / 3));
            char c = currentTiles[i];

            paintTile(g2, c, x, y);
        }
    }

    /**
     * Draw the tile with x-y coordinate and name of the tile
     *
     * @param g2
     * @param c name of the tile in character
     * @param x integer value represents the horizontal position of the tile
     * @param y integer value represents the vertical position of the tile
     */
    private void paintTile(Graphics2D g2, char c, int x, int y) {

        //Don't paint the empty tile
        if (c != ' ') {
            
            //Draw border
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(5));
            Rectangle2D.Double tileBorder = new Rectangle2D.Double(x, y, 50, 50);
            g2.draw(tileBorder);

            //Fill background color
            g2.setColor(Color.orange);
            Rectangle2D.Double tileBackground = new Rectangle2D.Double(x, y, 50, 50);
            g2.fill(tileBackground);

            //Draw the number
            g2.setColor(Color.white);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.drawString(String.valueOf(c), x + 17, y + 36);
        }
    }

}
