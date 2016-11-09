package es.ulpgc.eii.android.project1.modal;

import android.content.Context;

import java.util.Collections;

import es.ulpgc.eii.android.project1.R;
import es.ulpgc.eii.android.project1.ui.ButtonsToPlay;
import es.ulpgc.eii.android.project1.ui.Die;
import es.ulpgc.eii.android.project1.ui.GameState;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class Game {

    private Context context;
    private Players players;
    private int maxScore;
    private Die die;
    private GameState gameState;
    private ButtonsToPlay buttons;

    public Game(Context context, Player... players) {
        this.context = context;

        // 100 points are necessary to win the game //
        maxScore = 100;

        // Players with their bar scores //
        this.players = new Players();
        Collections.addAll(this.players, players);
        this.players.setMaxProgress(maxScore);

        // The die image and logic //
        die = new Die(context);

        // GameState enable one of the buttons,
        // so it is necessary to declare buttons before gameState //
        buttons = new ButtonsToPlay(this);
        gameState = new GameState(this);

    }

    // The game starts with the player who is set as parameter //
    public void start(Player firstPlayer) {
        this.players.resetBarScores();
        this.players.setFirstPlayer(firstPlayer);

        // The turn has not been started yet //
        gameState.newTurn();
        update();
        buttons.hideButtons();
    }

    // When a turn is going to be started, the player is changed,
    // the game state is updated and the buttons are hidden //
    public void startTurn() {
        players.changePlayerToPlay();
        update();
        gameState.newTurn();
        buttons.hideButtons();
    }

    // The only element which needs a update is the accumulated score //
    public void update() {
        gameState.getTextViewAccumulated().setText(
                String.format(context.getString(R.string.label_accumulated),
                        players.getPlayerToPlay().getAccumulatedScore()));
    }

    public Context getContext() {
        return context;
    }

    public Players getPlayers() {
        return players;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public Die getDie() {
        return die;
    }

    public ButtonsToPlay getButtons() {
        return buttons;
    }

}