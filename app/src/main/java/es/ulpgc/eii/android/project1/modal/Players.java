package es.ulpgc.eii.android.project1.modal;

import java.util.ArrayList;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class witch controls the group of players and
// returns the player who has to play //
public class Players extends ArrayList<Player> {

    private Player playerToPlay;

    Players() {
    }

    void setFirstPlayer(Player player) {
        playerToPlay = player;
    }

    public void changePlayerToPlay() {
        playerToPlay.setAccumulatedScore(0);
        int numPlayer = this.indexOf(playerToPlay) + 1;
        playerToPlay = this.get(numPlayer % this.size());
    }

    public Player getPlayerToPlay() {
        return playerToPlay;
    }

    // Set the max score as maximum of progress in the ProgressBar widgets //
    void setMaxProgress(int maxScore) {
        for (Player player : this) {
            player.getBarScore().getProgressBar().setMax(maxScore);
        }
    }

    // Set to zero the progress and the text of the players //
    void resetBarScores() {
        for (Player player : this) {
            player.getBarScore().setScore(0);
        }
    }

}