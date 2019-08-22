package com.music.bean;

/**
 * Created by 羊 on 2018/10/29.
 */
public class User {
    private int id;
    private String username;
    private String accountNumber;
    private String password;
    private String noticeSinger;
    private String noticeUser;
    private String likeSongs;
    private String likeList;
    private String likeSpecial;
    private String createTime;
    private String fans;
    private String userPic;
    private String userY;
    private String userZ;

    public User() {
    }

    public User(int id, String username, String accountNumber, String password, String noticeSinger, String noticeUser, String likeSongs, String likeList, String likeSpecial, String createTime, String fans, String userPic, String userY) {
        this.id = id;
        this.username = username;
        this.accountNumber = accountNumber;
        this.password = password;
        this.noticeSinger = noticeSinger;
        this.noticeUser = noticeUser;
        this.likeSongs = likeSongs;
        this.likeList = likeList;
        this.likeSpecial = likeSpecial;
        this.createTime = createTime;
        this.fans = fans;
        this.userPic = userPic;
        this.userY = userY;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", password='" + password + '\'' +
                ", noticeSinger='" + noticeSinger + '\'' +
                ", noticeUser='" + noticeUser + '\'' +
                ", likeSongs='" + likeSongs + '\'' +
                ", likeList='" + likeList + '\'' +
                ", likeSpecial='" + likeSpecial + '\'' +
                ", createTime='" + createTime + '\'' +
                ", fans='" + fans + '\'' +
                ", userPic='" + userPic + '\'' +
                ", userY='" + userY + '\'' +
                ", userZ='" + userZ + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoticeSinger() {
        return noticeSinger;
    }

    public void setNoticeSinger(String noticeSinger) {
        this.noticeSinger = noticeSinger;
    }

    public String getNoticeUser() {
        return noticeUser;
    }

    public void setNoticeUser(String noticeUser) {
        this.noticeUser = noticeUser;
    }

    public String getLikeSongs() {
        return likeSongs;
    }

    public void setLikeSongs(String likeSongs) {
        this.likeSongs = likeSongs;
    }

    public String getLikeList() {
        return likeList;
    }

    public void setLikeList(String likeList) {
        this.likeList = likeList;
    }

    public String getLikeSpecial() {
        return likeSpecial;
    }

    public void setLikeSpecial(String likeSpecial) {
        this.likeSpecial = likeSpecial;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserY() {
        return userY;
    }

    public void setUserY(String userY) {
        this.userY = userY;
    }

    public String getUserZ() {
        return userZ;
    }

    public void setUserZ(String userZ) {
        this.userZ = userZ;
    }
}
