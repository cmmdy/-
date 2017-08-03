package com.drop.ttb.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Drop on 2017/7/28.
 */

public class DynamicBean implements Parcelable {
    int id;
    int creatid;
    int channelid;
    int image;
    String name;
    String time;
    String text;
    int count;

    private List<Integer> images = new ArrayList<>();
    private List<String> like = new ArrayList<>();
    private List<String> comment = new ArrayList<>();

    public DynamicBean(int image, String name, String time, String text, List<Integer> images, List<String> like, List<String> comment) {
        this.image = image;
        this.name = name;
        this.time = time;
        this.text = text;
        this.images = images;
        this.like = like;
        this.comment = comment;
    }

    public DynamicBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatid() {
        return creatid;
    }

    public void setCreatid(int creatid) {
        this.creatid = creatid;
    }

    public int getChannelid() {
        return channelid;
    }

    public void setChannelid(int channelid) {
        this.channelid = channelid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images.addAll(images);
    }

    public List<String> getLike() {
        return like;
    }

    public void setLike(List<String> like) {
        this.like = like;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(String s) {
        this.comment.add(s);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.creatid);
        dest.writeInt(this.channelid);
        dest.writeInt(this.image);
        dest.writeString(this.name);
        dest.writeString(this.time);
        dest.writeString(this.text);
        dest.writeInt(this.count);
        dest.writeList(this.images);
        dest.writeStringList(this.like);
        dest.writeStringList(this.comment);
    }

    protected DynamicBean(Parcel in) {
        this.id = in.readInt();
        this.creatid = in.readInt();
        this.channelid = in.readInt();
        this.image = in.readInt();
        this.name = in.readString();
        this.time = in.readString();
        this.text = in.readString();
        this.count = in.readInt();
        this.images = new ArrayList<Integer>();
        in.readList(this.images, Integer.class.getClassLoader());
        this.like = in.createStringArrayList();
        this.comment = in.createStringArrayList();
    }

    public static final Creator<DynamicBean> CREATOR = new Creator<DynamicBean>() {
        @Override
        public DynamicBean createFromParcel(Parcel source) {
            return new DynamicBean(source);
        }

        @Override
        public DynamicBean[] newArray(int size) {
            return new DynamicBean[size];
        }
    };
}
