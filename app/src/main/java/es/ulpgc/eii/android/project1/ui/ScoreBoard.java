package es.ulpgc.eii.android.project1.ui;

import android.content.Context;

import es.ulpgc.eii.android.project1.modal.Player;
import es.ulpgc.eii.android.project1.modal.Players;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public class ScoreBoard {

    private BarScore player1;
    private BarScore player2;

    public ScoreBoard(BarScore player1, BarScore player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    // Set the max score as maximum of progress in the ProgressBar widgets //
    public void setMax(int maxScore) {
        player1.setMax(maxScore);
        player2.setMax(maxScore);
    }

    // Set to zero the progress and the text of the players //
    void resetBarScores() {
        player1.setScore(0);
        player2.setScore(0);
    }

    public void updatePlayer1(int score) {
        player1.setScore(score);
    }

    public BarScore getBarScorePlayer1() {
        return player1;
    }

    public void setPlayer1(String namePlayer1) {
        player1.setNameBarScore(namePlayer1);
    }

    public void updatePlayer2(int score) {
        player2.setScore(score);
    }

    public BarScore getBarScorePlayer2() {
        return player2;
    }

    public void setPlayer2(String namePlayer2) {
        player2.setNameBarScore(namePlayer2);
    }
}
