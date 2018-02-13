package com.example.roni.profiler.dataModel.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by roni on 10/02/18.
 */

public class Profile implements Parcelable {
    private String bio;
    private String interests;
    private String uid;
    private String email;
    private String photoURL;
    private String name;
    private String userId;

    public Profile(String bio, String interests, String uid, String email, String photoURL, String name, String userId) {
        this.bio = bio;
        this.interests = interests;
        this.uid = uid;
        this.email = email;
        this.photoURL = photoURL;
        this.name = name;
        this.userId = userId;
    }

    public Profile() {
        // Required by firebase
    }

    protected Profile(Parcel in) {
        bio = in.readString();
        interests = in.readString();
        uid = in.readString();
        email = in.readString();
        photoURL = in.readString();
        name = in.readString();
        userId = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getUid(){
        return this.uid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bio);
        dest.writeString(interests);
        dest.writeString(uid);
        dest.writeString(email);
        dest.writeString(photoURL);
        dest.writeString(name);
        dest.writeString(userId);
    }
}
