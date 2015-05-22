package com.sodet.paulofernandes.awe;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
public class Session implements Parcelable {


    private boolean is3d;
    private boolean dubbed;
    private String day;
    private String hour;
    private Room room;

    public Session()
    {

    }


    public Session(boolean is3d, boolean dubbed, String day, String hour, Room room) {
        this.is3d = is3d;
        this.dubbed = dubbed;
        this.day = day;
        this.hour = hour;
        this.room = room;

    }

    public boolean is3d() {
        return is3d;
    }

    public void setIs3d(boolean is3d) {
        this.is3d = is3d;
    }

    public boolean isDubbed() {
        return dubbed;
    }

    public void setDubbed(boolean dubbed) {
        this.dubbed = dubbed;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    protected Session(Parcel in) {
        is3d = in.readByte() != 0x00;
        dubbed = in.readByte() != 0x00;
        day = in.readString();
        hour = in.readString();
        room = (Room) in.readValue(Room.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (is3d ? 0x01 : 0x00));
        dest.writeByte((byte) (dubbed ? 0x01 : 0x00));
        dest.writeString(day);
        dest.writeString(hour);
        dest.writeValue(room);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Session> CREATOR = new Parcelable.Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };
}