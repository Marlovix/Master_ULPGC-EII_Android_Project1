package es.ulpgc.eii.android.project1.ui;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which updates the view of the widgets related with the player score //
public class BarScore {

    private TextView textViewNamePlayer;
    private TextView textViewScore;
    private ProgressBar progressBar;
    private int score;

    public BarScore(TextView textViewNamePlayer, TextView textViewScore, ProgressBar progressBar) {
        score = 0;
        this.textViewNamePlayer = textViewNamePlayer;
        this.textViewScore = textViewScore;
        this.progressBar = progressBar;
        this.textViewScore.setText(String.format(Locale.getDefault(), "%d", score));
    }

    public void setNameBarScore(String name) {
        textViewNamePlayer.setText(name);
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        progressBar.setProgress(score);
        textViewScore.setText(String.format(Locale.getDefault(), "%d", score));
    }

    public String getNamePlayer(){
        return textViewNamePlayer.getText().toString();
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setMax(int max){
        progressBar.setMax(max);
    }

}