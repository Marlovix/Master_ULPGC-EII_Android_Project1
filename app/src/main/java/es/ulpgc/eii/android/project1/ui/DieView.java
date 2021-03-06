package es.ulpgc.eii.android.project1.ui;

import android.view.View;
import android.widget.ImageView;

import es.ulpgc.eii.android.project1.R;
import es.ulpgc.eii.android.project1.modal.Game;

public class DieView implements GameObject {
    private ImageView imageViewDie;

    public DieView(ImageView imageViewDie) {
        this.imageViewDie = imageViewDie;
    }

    @Override
    public void finishGame(Game game) {
        setImage(game);
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void gamePlay(Game game) {
        setImage(game);
        imageViewDie.setVisibility(View.VISIBLE);
    }

    @Override
    public void lostTurnByOne(Game game) {
        setImage(game);
        imageViewDie.setVisibility(View.VISIBLE);
    }

    @Override
    public void readyToPlay(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startGame(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startTurn(Game game) {
        imageViewDie.setVisibility(View.INVISIBLE);
    }

    private void setImage(Game game) {
        int lastThrowing = game.getLastThrowing();
        int image = 0;
        switch (lastThrowing) {
            case 1:
                image = R.drawable.face1;
                break;
            case 2:
                image = R.drawable.face2;
                break;
            case 3:
                image = R.drawable.face3;
                break;
            case 4:
                image = R.drawable.face4;
                break;
            case 5:
                image = R.drawable.face5;
                break;
            case 6:
                image = R.drawable.face6;
                break;
        }
        imageViewDie.setImageResource(image);
    }
}
