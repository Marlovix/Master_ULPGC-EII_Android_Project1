package es.ulpgc.eii.android.project1.modal;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class Game implements Parcelable {

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
    private int accumulatedScore;
    private Die die;
    private GameState gameState;
    private int lastThrowing;
    private int maxScore;
    private Players players;

    public Game(Player... players) {
        gameState = GameState.START;

        maxScore = 100; // 100 points are necessary to win the game //
        accumulatedScore = 0;
        lastThrowing = 0;

        die = new Die();

        this.players = new Players();
        Collections.addAll(this.players, players);
    }

    protected Game(Parcel in) {
        this.accumulatedScore = in.readInt();
        this.die = in.readParcelable(Die.class.getClassLoader());
        int tmpGameState = in.readInt();
        this.gameState = tmpGameState == -1 ? null : GameState.values()[tmpGameState];
        this.lastThrowing = in.readInt();
        this.maxScore = in.readInt();
        this.players = in.readParcelable(Players.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.accumulatedScore);
        dest.writeParcelable(this.die, flags);
        dest.writeInt(this.gameState == null ? -1 : this.gameState.ordinal());
        dest.writeInt(this.lastThrowing);
        dest.writeInt(this.maxScore);
        dest.writeParcelable(this.players, flags);
    }

    public int getAccumulatedScore() {
        return accumulatedScore;
    }

    public void setAccumulatedScore(int accumulatedInGame) {
        this.accumulatedScore = accumulatedInGame;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getLastThrowing() {
        return lastThrowing;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public Players getPlayers() {
        return players;
    }

    public void restart() {
        changeTurn();
        setAccumulatedScore(0);
        for (Player player : players) player.setScore(0);
        lastThrowing = 0;

        Player newPlayer = getTurnPlayer();
        start(newPlayer);
    }

    public void changeTurn() {
        players.changePlayer();
    }

    public Player getTurnPlayer() {
        return this.players.getPlayer();
    }

    // The game starts with the player who is set as parameter //
    public void start(Player firstPlayer) {
        this.players.setFirstPlayer(firstPlayer);
    }

    public void setStateGame() {
        gameState = GameState.GAME;
    }

    public void setStateOne() {
        gameState = GameState.ONE;
    }

    public void setStateReady() {
        gameState = GameState.READY;
    }

    public void setStateStart() {
        gameState = GameState.START;
    }

    public void setStateTurn() {
        gameState = GameState.TURN;
    }

    public void setStateWinner() {
        gameState = GameState.WINNER;
    }

    public int throwDie() {
        lastThrowing = die.getValue();
        return lastThrowing;
    }
}