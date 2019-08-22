package com.music.bean;

/**
 * Created by 羊 on 2018/10/29.
 */
public class MusicComment {
    private int id;
    private int userId;
    private int musicId;
    private int parentId;
    private int depth;
    private String content;
    private int giveGood;
    private String createTime;
    private String commentX;
    private String commentY;
    private User user;

    public MusicComment() {
    }

    public MusicComment(int id, int userId, int musicId, int parentId, int depth, String content, int giveGood, String createTime, String commentX, String commentY, User user) {
        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
        this.parentId = parentId;
        this.depth = depth;
        this.content = content;
        this.giveGood = giveGood;
        this.createTime = createTime;
        this.commentX = commentX;
        this.commentY = commentY;
        this.user = user;
    }

    @Override
    public String toString() {
        return "MusicComment{" +
                "id=" + id +
                ", userId=" + userId +
                ", musicId=" + musicId +
                ", parentId=" + parentId +
                ", depth=" + depth +
                ", content='" + content + '\'' +
                ", giveGood=" + giveGood +
                ", createTime='" + createTime + '\'' +
                ", commentX='" + commentX + '\'' +
                ", commentY='" + commentY + '\'' +
                ", user=" + user +
                '}';
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

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGiveGood() {
        return giveGood;
    }

    public void setGiveGood(int giveGood) {
        this.giveGood = giveGood;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCommentX() {
        return commentX;
    }

    public void setCommentX(String commentX) {
        this.commentX = commentX;
    }

    public String getCommentY() {
        return commentY;
    }

    public void setCommentY(String commentY) {
        this.commentY = commentY;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
