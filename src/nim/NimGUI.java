/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim;

import framework_AI_game.AutomatedPlayer;
import framework_AI_game.GUI;
import framework_AI_game.HumanPlayer;
import framework_AI_game.Move;
import framework_AI_game.Player;
import framework_AI_game.Turn;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFrame;

/**
 *
 * @author Nam Phung
 */
public class NimGUI extends JFrame {

    public NimGUI() {

        // Set frame title
        this.setTitle("World of Nim");

        // Total number of piles
        final int PILES_SIZE = ThreePilesNimGame.PILE_SIZE;

        // Game introduction
        String introduction = " + There are " + PILES_SIZE + " piles of coins.\n" +
                " + Two players take turns removing coins.\n" + 
                " + Each turn consists of taking one coin or more as long as they are from the same pile.\n" +
                " + The winner is the player who takes the last coin (or coins).\n";

        // Initial game piles
        List<Integer> initialPiles = new ArrayList<>();
        for (int i = 0; i < PILES_SIZE; ++i) {
            // Add a random amount of coins from 6 to 12 to the pile
            initialPiles.add(ThreadLocalRandom.current().nextInt(6, 12));
        }

        // The final state piles
        List<Integer> finalPiles = new ArrayList<>();
        for (int i = 0; i < PILES_SIZE; ++i) {
            finalPiles.add(0);
        }

        // Available move list
        List<Move> moves = new ArrayList<>();
        for (int pile_index = 0; pile_index < initialPiles.size(); ++pile_index) {
            for (int coin_capacity = 1; coin_capacity <= initialPiles.get(pile_index); ++coin_capacity) {
                moves.add(new NimMove("Take " + coin_capacity + " coins from pile " + (pile_index + 1),
                        pile_index + ":" + coin_capacity));
            }
        }

        // All game players - HUMAN vs COMPUTER
        Player player_1 = new HumanPlayer("Nam", moves, null, Turn.HUMAN_1);
        Player player_2 = new AutomatedPlayer("Cortana", moves, null, Turn.COMPUTER);

        // The game states
        // Initial state has 3 piles with random amount and player 1 is playing first
        NimState initialGameState = new NimState(initialPiles, player_1.getPlayerType(), player_2.getPlayerType());
        
        // The winning state of first player would be when the piles are empty and it's second player's turn to play
        NimState player_1_winning_state = new NimState(finalPiles, player_2.getPlayerType(), player_1.getPlayerType());
        
        // The winning state of second player would be when the piles are empty and it's first player's turn to play
        NimState player_2_winning_state = new NimState(finalPiles, player_1.getPlayerType(), player_2.getPlayerType());

        // Create a new nim game
        ThreePilesNimGame nimGame = new ThreePilesNimGame(introduction,
                initialGameState,
                player_1,
                player_1_winning_state,
                player_2,
                player_2_winning_state);

        // Set the game the players are playing
        nimGame.getFirstPlayer().setCurrentGamePlaying(nimGame);
        nimGame.getSecondPlayer().setCurrentGamePlaying(nimGame);

        GUI nimGUI = new GUI(nimGame, new ThreePilesNimCanvas(nimGame, null), 900, 550);
        nimGUI.getCurrentStateCanvas().setFrameGUI(nimGUI);

        add(nimGUI);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * This method launches the gui.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        NimGUI nimGUI = new NimGUI();
    }
}


