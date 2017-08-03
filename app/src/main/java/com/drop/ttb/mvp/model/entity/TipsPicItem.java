package com.drop.ttb.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZWY on 2017/3/26.
 */

public class TipsPicItem implements Parcelable{
    private String pic;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public TipsPicItem(String pic) {
        this.pic = pic;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pic);
    }

    protected TipsPicItem(Parcel in) {
        this.pic = in.readString();
    }

    public static final Creator<TipsPicItem> CREATOR = new Creator<TipsPicItem>() {
        @Override
        public TipsPicItem createFromParcel(Parcel source) {
            return new TipsPicItem(source);
        }

        @Override
        public TipsPicItem[] newArray(int size) {
            return new TipsPicItem[size];
        }
    };
}
