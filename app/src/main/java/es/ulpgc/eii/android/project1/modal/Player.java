package es.ulpgc.eii.android.project1.modal;

import es.ulpgc.eii.android.project1.ui.BarScore;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class with all information which is necessary for a player //
public class Player {

    private String name;
    private int accumulatedScore;
    private int color;
    private BarScore barScore;

    public Player(String name, int color, BarScore barScore) {
        this.name = name;
        accumulatedScore = 0;
        this.color = color;
        this.barScore = barScore;
        this.barScore.setNameBarScore(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccumulatedScore() {
        return accumulatedScore;
    }

    void setAccumulatedScore(int accumulatedScore) {
        this.accumulatedScore = accumulatedScore;
    }

    public void addAccumulatedScore(int accumulatedScore) {
        this.accumulatedScore += accumulatedScore;
    }

    public int getColor() {
        return color;
    }

    public BarScore getBarScore() {
        return barScore;
    }

}