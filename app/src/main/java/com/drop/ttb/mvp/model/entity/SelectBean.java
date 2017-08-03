package com.drop.ttb.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Drop on 2017/7/31.
 */

public class SelectBean implements Parcelable{
    int id;
    String name;
    int ivid;
    boolean select;

    public SelectBean(int id, String name, int ivid, boolean select) {
        this.id = id;
        this.name = name;
        this.ivid = ivid;
        this.select = select;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIvid() {
        return ivid;
    }

    public void setIvid(int ivid) {
        this.ivid = ivid;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.ivid);
        dest.writeByte(this.select ? (byte) 1 : (byte) 0);
    }

    protected SelectBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.ivid = in.readInt();
        this.select = in.readByte() != 0;
    }

    public static final Creator<SelectBean> CREATOR = new Creator<SelectBean>() {
        @Override
        public SelectBean createFromParcel(Parcel source) {
            return new SelectBean(source);
        }

        @Override
        public SelectBean[] newArray(int size) {
            return new SelectBean[size];
        }
    };
}
