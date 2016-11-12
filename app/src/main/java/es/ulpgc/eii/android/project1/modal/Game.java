package es.ulpgc.eii.android.project1.modal;

import java.util.Collections;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class Game {

    public enum State {START,READY,GAME,ONE,WINNER}

    private State state;
    private Players players;
    private Die die;
    private int maxScore;

    public Game(Player... players) {
        state = State.START;

        // 100 points are necessary to win the game //
        maxScore = 20;

        die = new Die();

        // Players with their bar scores //
        this.players = new Players();
        Collections.addAll(this.players, players);
    }

    // The game starts with the player who is set as parameter //
    public void start(Player firstPlayer) {
        this.players.setFirstPlayer(firstPlayer);
        this.players.resetScores();
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

    public State getState() {
        return state;
    }

    public void setStateStart(){
        state = State.START;
    }
    public void setStateReady(){
        state = State.READY;
    }
    public void setStateGame(){
        state = State.GAME;
    }
    public void setStateOne(){
        state = State.ONE;
    }
    public void setStateWinner(){
        state = State.WINNER;
    }

}