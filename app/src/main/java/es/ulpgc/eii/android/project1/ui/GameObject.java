package es.ulpgc.eii.android.project1.ui;

import es.ulpgc.eii.android.project1.modal.Game;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

public abstract class GameObject {
    public abstract void finishGame(Game game);

    public abstract void gamePlay(Game game);

    public abstract void lostTurnByOne(Game game);

    public abstract void readyToPlay(Game game);

    public abstract void startGame(Game game);

    public abstract void startTurn(Game game);
}
