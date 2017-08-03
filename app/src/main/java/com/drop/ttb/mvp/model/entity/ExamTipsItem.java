package com.drop.ttb.mvp.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWY on 2017/3/26.
 */

public class ExamTipsItem implements Parcelable {
    private String writerPhoto;
    private String writerName;
    private String writerTime;
    private String writerLocation;
    private String issueContent;
    private List<TipsPicItem> picItems;
    private List<TipsDiscussItem> tipsDiscussItems;
    public ExamTipsItem(String writerPhoto, String writerName, String writerTime, String
            writerLocation, String issueContent, List<TipsPicItem> picItems,
                        List<TipsDiscussItem> tipsDiscussItems) {
        this.writerPhoto = writerPhoto;
        this.writerName = writerName;
        this.writerTime = writerTime;
        this.writerLocation = writerLocation;
        this.issueContent = issueContent;
        this.picItems = picItems;
        this.tipsDiscussItems = tipsDiscussItems;
    }

    public List<TipsPicItem> getPicItems() {
        return picItems;
    }

    public void setPicItems(List<TipsPicItem> picItems) {
        this.picItems = picItems;
    }



    public List<TipsDiscussItem> getTipsDiscussItems() {
        return tipsDiscussItems;
    }

    public void setTipsDiscussItems(List<TipsDiscussItem> tipsDiscussItems) {
        this.tipsDiscussItems = tipsDiscussItems;
    }

    public String getWriterPhoto() {
        return writerPhoto;
    }

    public void setWriterPhoto(String writerPhoto) {
        this.writerPhoto = writerPhoto;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public String getWriterTime() {
        return writerTime;
    }

    public void setWriterTime(String writerTime) {
        this.writerTime = writerTime;
    }

    public String getWriterLocation() {
        return writerLocation;
    }

    public void setWriterLocation(String writerLocation) {
        this.writerLocation = writerLocation;
    }

    public String getIssueContent() {
        return issueContent;
    }

    public void setIssueContent(String issueContent) {
        this.issueContent = issueContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.writerPhoto);
        dest.writeString(this.writerName);
        dest.writeString(this.writerTime);
        dest.writeString(this.writerLocation);
        dest.writeString(this.issueContent);
        dest.writeList(this.picItems);
        dest.writeList(this.tipsDiscussItems);
    }

    protected ExamTipsItem(Parcel in) {
        this.writerPhoto = in.readString();
        this.writerName = in.readString();
        this.writerTime = in.readString();
        this.writerLocation = in.readString();
        this.issueContent = in.readString();
        this.picItems = new ArrayList<TipsPicItem>();
        in.readList(this.picItems, TipsPicItem.class.getClassLoader());
        this.tipsDiscussItems = new ArrayList<TipsDiscussItem>();
        in.readList(this.tipsDiscussItems, TipsDiscussItem.class.getClassLoader());
    }

    public static final Creator<ExamTipsItem> CREATOR = new Creator<ExamTipsItem>() {
        @Override
        public ExamTipsItem createFromParcel(Parcel source) {
            return new ExamTipsItem(source);
        }

        @Override
        public ExamTipsItem[] newArray(int size) {
            return new ExamTipsItem[size];
        }
    };
}
