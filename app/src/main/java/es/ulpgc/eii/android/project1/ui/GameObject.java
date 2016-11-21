package es.ulpgc.eii.android.project1.ui;

import es.ulpgc.eii.android.project1.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public interface GameObject {
    void finishGame(Game game);

    void gamePlay(Game game);

    void lostTurnByOne(Game game);

    void readyToPlay(Game game);

    void startGame(Game game);

    void startTurn(Game game);
}
