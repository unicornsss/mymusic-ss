package com.music.bean;

/**
 * Created by 羊 on 2018/10/29.
 */
public class SongList {
    private int id;
    private int userId;
    private String listName;
    private String listLabel;
    private int playNum;
    private String likeNum;
    private String listPicture;
    private String listSummary;
    private int listFlag;
    private String listY;
    private User user;

    public SongList() {
    }

    @Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", userId=" + userId +
                ", listName='" + listName + '\'' +
                ", listLabel='" + listLabel + '\'' +
                ", playNum=" + playNum +
                ", likeNum='" + likeNum + '\'' +
                ", listPicture='" + listPicture + '\'' +
                ", listSummary='" + listSummary + '\'' +
                ", listFlag=" + listFlag +
                ", listY='" + listY + '\'' +
                ", user=" + user +
                '}';
    }

    public SongList(int id, int userId, String listName, String listLabel, int playNum, String likeNum, String listPicture, String listSummary, int listFlag, String listY, User user) {
        this.id = id;
        this.userId = userId;
        this.listName = listName;
        this.listLabel = listLabel;
        this.playNum = playNum;
        this.likeNum = likeNum;
        this.listPicture = listPicture;
        this.listSummary = listSummary;
        this.listFlag = listFlag;
        this.listY = listY;
        this.user = user;
    }

    public int getListFlag() {
        return listFlag;
    }

    public void setListFlag(int listFlag) {
        this.listFlag = listFlag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListLabel() {
        return listLabel;
    }

    public void setListLabel(String listLabel) {
        this.listLabel = listLabel;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    public String getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(String likeNum) {
        this.likeNum = likeNum;
    }

    public String getListPicture() {
        return listPicture;
    }

    public void setListPicture(String listPicture) {
        this.listPicture = listPicture;
    }

    public String getListSummary() {
        return listSummary;
    }

    public void setListSummary(String listSummary) {
        this.listSummary = listSummary;
    }



    public String getListY() {
        return listY;
    }

    public void setListY(String listY) {
        this.listY = listY;
    }
}
