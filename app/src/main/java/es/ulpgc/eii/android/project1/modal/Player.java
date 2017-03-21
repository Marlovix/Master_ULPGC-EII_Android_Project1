package es.ulpgc.eii.android.project1.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
    private int color;
    private String name;
    private int totalScore;

    public Player(String name, int color) {
        this.name = name;
        this.color = color;
        totalScore = 0;
    }

    protected Player(Parcel in) {
        this.name = in.readString();
        this.totalScore = in.readInt();
        this.color = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.totalScore);
        dest.writeInt(this.color);
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return totalScore;
    }

    public void setScore(int totalScore) {
        this.totalScore = totalScore;
    }
}