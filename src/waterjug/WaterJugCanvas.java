/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waterjug;

import framework_puzzle.Canvas;
import framework_puzzle.State;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

/**
 * Canvas class represents the graphical version of the water jug problem
 *
 * @author Nam Phung
 */
public class WaterJugCanvas extends Canvas {

    /**
     * Parameterized constructor, take in the initial state for the canvas to
     * display
     *
     * @param currentState
     */
    public WaterJugCanvas(State currentState) {
        super(currentState);

        this.setPreferredSize(new Dimension(400,400));
        
        final WaterJugCanvas thisCanvas = this;

        jugXTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                WaterJugState currentState = (WaterJugState) getCurrentState();
                WaterJugState previousState = (WaterJugState) getPreviousState();

                final int currentAmountInX = currentState.getAmountInJugX() * 40;
                final int previousAmountInX = previousState.getAmountInJugX() * 40;
                final int incrementAmountInX = (currentAmountInX == previousAmountInX) ? 0 : ((currentAmountInX > previousAmountInX) ? 1 : -1);

                if (jugXWater.getHeight() != currentAmountInX) {
                    jugXWater.setRect(jugXBorder.getBounds().x, baseLine - incrementAmountInX - jugXWater.getHeight(), jugsWidth, jugXWater.getHeight() + incrementAmountInX);

                } else {
                    jugXTimer.stop();
                }

                thisCanvas.repaint();
            }
        });

        jugYTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WaterJugState currentState = (WaterJugState) getCurrentState();
                WaterJugState previousState = (WaterJugState) getPreviousState();

                final int currentAmountInY = currentState.getAmountInJugY() * 40;
                final int previousAmountInY = previousState.getAmountInJugY() * 40;
                final int incrementAmountInY = (currentAmountInY == previousAmountInY) ? 0 : ((currentAmountInY > previousAmountInY) ? 1 : -1);

                if (jugYWater.getHeight() != currentAmountInY) {
                    jugYWater.setRect(jugYBorder.getBounds().x, baseLine - incrementAmountInY - jugYWater.getHeight(), jugsWidth, jugYWater.getHeight() + incrementAmountInY);
                } else {
                    jugYTimer.stop();
                }
                thisCanvas.repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        drawScenary(g);
        drawJugs(g);
    }

    /**
     * Draw the background & table holding jugs
     *
     * @param g
     */
    private void drawScenary(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //Widht and height of the problem state drawing
        final int WIDTH = this.getSize().width;
        final int HEIGHT = this.getSize().height;

        //Fill background color
        g2.setColor(new Color((float) 0.93, (float) 0.83, (float) 0.71, 1));
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        //Draw table surface & legs
        g2.setColor(new Color((float) 0.54, (float) 0.27, (float) 0.074, 1));
        g2.fillRect(0, 250, WIDTH, 40);
        g2.fillRect(40, 250, 20, 150);
        g2.fillRect(340, 250, 20, 150);
    }

    /**
     * Draw jugs border
     *
     * @param g
     */
    private void drawJugs(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g;

        WaterJugState currentState = (WaterJugState) this.getCurrentState();

        final int amountInX = currentState.getAmountInJugX() * 40;
        final int amountInY = currentState.getAmountInJugY() * 40;

        //Fill water in jugs
        g2.setColor(Color.blue);
        g2.fill(jugXWater);
        g2.fill(jugYWater);

        //Draw jugs border
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2));
        g2.draw(jugXBorder);
        g2.draw(jugYBorder);

        //Mark the jugs with a character
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial", Font.BOLD, 25));

        //Mark jug X
        g2.drawString(String.format("X(%d)", amountInX / 40), 125, 280);

        //Mark jug Y
        g2.drawString(String.format("Y(%d)", amountInY / 40), 225, 280);

    }

    /**
     * Render animation for water in the jugs the state of the problem
     */
    @Override
    public void render() {

        finishIncompleteRendering();
        
        this.repaint();

        jugXTimer.start();
        jugYTimer.start();
    }

    /**
     * Finish incomplete rendering
     */
    private void finishIncompleteRendering() {
        WaterJugState previousState = (WaterJugState) getPreviousState();
        final int previousAmountInX = previousState.getAmountInJugX() * 40;
        final int previousAmountInY = previousState.getAmountInJugY() * 40;

        if (jugXWater.getHeight() != previousAmountInX) {
            jugXWater.setRect(jugXBorder.getBounds().x, baseLine - previousAmountInX, jugsWidth, previousAmountInX);
        }
        if (jugYWater.getHeight() != previousAmountInY) {
            jugYWater.setRect(jugYBorder.getBounds().x, baseLine - previousAmountInY, jugsWidth, previousAmountInY);
        }
    }

    /**
     * Timer for the jug X animation
     */
    final Timer jugXTimer = new Timer(5, null);
    
    /**
     * Timer for jug Y animation
     */
    final Timer jugYTimer = new Timer(5, null);

    /**
     * Height of each jug
     */
    private final int jugXHeight = 120;
    private final int jugYHeight = 160;
    
    /**
     * Width of the jugs
     */
    private final int jugsWidth = 90;
    
    /**
     * Base line
     */
    private final int baseLine = 250;

    /**
     * Shape of the jug X 
     */
    private Rectangle2D jugXBorder = new Rectangle2D.Double(100, 130, jugsWidth, jugXHeight);
    
    /**
     * Water shape of jug X
     */
    private Rectangle2D jugXWater = new Rectangle2D.Double(100, 170, jugsWidth, 80);

    /**
     * Shape of jug Y
     */
    private Rectangle2D jugYBorder = new Rectangle2D.Double(200, 90, jugsWidth, jugYHeight);
    
    /**
     * Water shape of jug Y
     */
    private Rectangle2D jugYWater = new Rectangle2D.Double(200, 170, jugsWidth, 80);

}
