package com.ssafy.happyhouse.qna.model;

public class Qna {
    private int articleNo;
    private String userId;
    private String userName;
    private String subject;
    private String content;
    private int hit;
    private String registerTime;

    public Qna() {
    }

    public Qna(int articleNo, String userId, String userName, String subject, String content, int hit, String registerTime) {
        this.articleNo = articleNo;
        this.userId = userId;
        this.userName = userName;
        this.subject = subject;
        this.content = content;
        this.hit = hit;
        this.registerTime = registerTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "Qna{" +
                "articleNo=" + articleNo +
                ", userId='" + userId + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", hit=" + hit +
                ", registerTime='" + registerTime + '\'' +
                '}';
    }
}
