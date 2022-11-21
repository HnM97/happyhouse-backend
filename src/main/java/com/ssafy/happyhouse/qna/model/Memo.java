package com.ssafy.happyhouse.qna.model;

public class Memo {
    private int memoNo;
    private int articleNo;
    private String userId;
    private String comment;
    private String memoTime;

    public Memo() {
    }

    public int getMemoNo() {
        return memoNo;
    }

    public void setMemoNo(int memoNo) {
        this.memoNo = memoNo;
    }

    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {

        this.articleNo = articleNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMemoTime() {
        return memoTime;
    }

    public void setMemoTime(String memoTime) {
        this.memoTime = memoTime;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "memoNo=" + memoNo +
                ", articleNo=" + articleNo +
                ", userId='" + userId + '\'' +
                ", comment='" + comment + '\'' +
                ", memoTime='" + memoTime + '\'' +
                '}';
    }
}
