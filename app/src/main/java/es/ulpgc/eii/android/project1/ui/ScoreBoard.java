package es.ulpgc.eii.android.project1.ui;

import es.ulpgc.eii.android.project1.modal.Game;
import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.modal.Players;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ScoreBoard implements GameObject {

    private BarScore barScorePlayer1;
    private BarScore barScorePlayer2;

    public ScoreBoard(BarScore barScorePlayer1, BarScore barScorePlayer2) {
        this.barScorePlayer1 = barScorePlayer1;
        this.barScorePlayer2 = barScorePlayer2;
    }

    @Override
    public void finishGame(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void gamePlay(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void lostTurnByOne(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void readyToPlay(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void startGame(Game game) {
        renderScoreBoard(game);
    }

    @Override
    public void startTurn(Game game) {
        renderScoreBoard(game);
    }

    private void renderScoreBoard(Game game) {
        setNames(game);
        showCurrentScores(game);
        setMax(game.getMaxScore());
    }

    // Set the max score as maximum of progress in the ProgressBar widgets //
    private void setMax(int maxScore) {
        barScorePlayer1.setMax(maxScore);
        barScorePlayer2.setMax(maxScore);
    }

    private void setNames(Game game) {
        Players players = game.getPlayers();
        barScorePlayer1.setNameBarScore(players.get(0).getName());
        barScorePlayer2.setNameBarScore(players.get(1).getName());
    }

    private void showCurrentScores(Game game) {
        Player player1 = game.getPlayers().get(0);
        Player player2 = game.getPlayers().get(1);

        barScorePlayer1.setScore(player1.getScore());
        barScorePlayer2.setScore(player2.getScore());
    }

}