package es.ulpgc.eii.android.project1.modal;

import java.util.Collections;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class Game {

    private Players players;
    private Die die;
    private int maxScore;

    public Game(Player... players) {
        // 100 points are necessary to win the game //
        maxScore = 10;

        die = new Die();

        // Players with their bar scores //
        this.players = new Players();
        Collections.addAll(this.players, players);
    }

    // The game starts with the player who is set as parameter //
    public void start(Player firstPlayer) {
        this.players.setFirstPlayer(firstPlayer);
    }

    // When a turn is going to be started, the player is changed,
    // the game state is updated and the buttons are hidden //
    public void startTurn() {
        players.changePlayer();
    }

    public int throwDie(){
        return die.getValue();
    }

    public Players getPlayers() {
        return players;
    }

    public int getMaxScore() {
        return maxScore;
    }

}