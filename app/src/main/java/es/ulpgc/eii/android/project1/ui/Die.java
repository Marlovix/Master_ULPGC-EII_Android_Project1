package es.ulpgc.eii.android.project1.ui;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import java.util.Random;

import es.ulpgc.eii.android.project1.R;

/**
 * Created by Marlovix
 * TODO: Add a class header comment!
 */

// Class which shows the correct image for the different results of the die throwing //
public class Die {

    private ImageView imageViewDie;
    private int faces;

    public Die(Context context) {
        // It establishes the number of faces that the die has //
        faces = 6;
        imageViewDie = (ImageView) ((Activity) context).findViewById(R.id.imageView_die);
    }

    public int getValue() {
        return showRandomInteger(1, faces);
    }

    // Returns a random number between two values //
    private int showRandomInteger(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long) end - (long) start + 1;
        Random random = new Random();
        long fraction = (long) (range * random.nextDouble());
        int result = (int) (fraction + start);
        setImage(result);
        return result;
    }

    private void setImage(Integer value) {
        int image = 0;
        switch (value) {
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