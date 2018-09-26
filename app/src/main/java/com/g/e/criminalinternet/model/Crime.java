package com.g.e.criminalinternet.model;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
//    private boolean mRequiresPolice;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    public void setSolved(boolean solved) {
        mSolved=solved;
    }

    public boolean isSolved(){
        return mSolved;
    }

//    public void setRequiredPolice (boolean requiredPolice){
//        mRequiresPolice =requiredPolice;
//    }

//    public boolean isRequiresPolice(){
//        return mRequiresPolice;
//    }

}
