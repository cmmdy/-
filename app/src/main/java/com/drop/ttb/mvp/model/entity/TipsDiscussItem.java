package com.drop.ttb.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZWY on 2017/3/26.
 */

public class TipsDiscussItem implements Parcelable{
    private String discussName;
    private String discussContent;

    public TipsDiscussItem(String discussName, String discussContent) {
        this.discussName = discussName;
        this.discussContent = discussContent;
    }

    public String getDiscussName() {
        return discussName;
    }

    public void setDiscussName(String discussName) {
        this.discussName = discussName;
    }

    public String getDiscussContent() {
        return discussContent;
    }

    public void setDiscussContent(String discussContent) {
        this.discussContent = discussContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.discussName);
        dest.writeString(this.discussContent);
    }

    protected TipsDiscussItem(Parcel in) {
        this.discussName = in.readString();
        this.discussContent = in.readString();
    }

    public static final Creator<TipsDiscussItem> CREATOR = new Creator<TipsDiscussItem>() {
        @Override
        public TipsDiscussItem createFromParcel(Parcel source) {
            return new TipsDiscussItem(source);
        }

        @Override
        public TipsDiscussItem[] newArray(int size) {
            return new TipsDiscussItem[size];
        }
    };
}
