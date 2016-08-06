package com.example.simpson;

import android.os.Parcel;
import android.os.Parcelable;

public class SimCharacter implements Parcelable {

    @Override
    public String toString() {
        return "SimCharacter{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    String name;
    String description;

    public SimCharacter( String name, String desc) {

        this.name = name;
        this.description = desc;
    }

    protected SimCharacter(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SimCharacter> CREATOR = new Parcelable.Creator<SimCharacter>() {
        @Override
        public SimCharacter createFromParcel(Parcel in) {
            return new SimCharacter(in);
        }

        @Override
        public SimCharacter[] newArray(int size) {
            return new SimCharacter[size];
        }
    };
}
