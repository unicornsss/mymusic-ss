package com.music.bean;

/**
 * Created by 羊 on 2018/10/29.
 */
public class Songs {
    private int id;
    private int singerId;
    private String songName;
    private int specialId;
    private String songLanguage;
    private String songStyle;
    private String createTime;
    private String songPicture;
    private String songText;
    private int songPlayNum;
    private String songFile;
    private int songFlag;
    private String songZ;
    private Singer singer;
    private Special special;

    public Songs() {
    }

    public Songs(int id, int singerId, String songName, int specialId, String songLanguage, String songStyle, String createTime, String songPicture, String songText, int songPlayNum, String songFile, int songFlag, String songZ, Singer singer, Special special) {
        this.id = id;
        this.singerId = singerId;
        this.songName = songName;
        this.specialId = specialId;
        this.songLanguage = songLanguage;
        this.songStyle = songStyle;
        this.createTime = createTime;
        this.songPicture = songPicture;
        this.songText = songText;
        this.songPlayNum = songPlayNum;
        this.songFile = songFile;
        this.songFlag = songFlag;
        this.songZ = songZ;
        this.singer = singer;
        this.special = special;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "id=" + id +
                ", singerId=" + singerId +
                ", songName='" + songName + '\'' +
                ", specialId=" + specialId +
                ", songLanguage='" + songLanguage + '\'' +
                ", songStyle='" + songStyle + '\'' +
                ", createTime='" + createTime + '\'' +
                ", songPicture='" + songPicture + '\'' +
                ", songText='" + songText + '\'' +
                ", songPlayNum=" + songPlayNum +
                ", songFile='" + songFile + '\'' +
                ", songFlag=" + songFlag +
                ", songZ='" + songZ + '\'' +
                ", singer=" + singer +
                ", special=" + special +
                '}';
    }

    public Special getSpecial() {
        return special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSpecialId() {
        return specialId;
    }

    public void setSpecialId(int specialId) {
        this.specialId = specialId;
    }

    public String getSongLanguage() {
        return songLanguage;
    }

    public void setSongLanguage(String songLanguage) {
        this.songLanguage = songLanguage;
    }

    public String getSongStyle() {
        return songStyle;
    }

    public void setSongStyle(String songStyle) {
        this.songStyle = songStyle;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSongPicture() {
        return songPicture;
    }

    public void setSongPicture(String songPicture) {
        this.songPicture = songPicture;
    }

    public String getSongText() {
        return songText;
    }

    public void setSongText(String songText) {
        this.songText = songText;
    }

    public int getSongPlayNum() {
        return songPlayNum;
    }

    public void setSongPlayNum(int songPlayNum) {
        this.songPlayNum = songPlayNum;
    }

    public String getSongFile() {
        return songFile;
    }

    public void setSongFile(String songFile) {
        this.songFile = songFile;
    }

    public int getSongFlag() {
        return songFlag;
    }

    public void setSongFlag(int songFlag) {
        this.songFlag = songFlag;
    }

    public String getSongZ() {
        return songZ;
    }

    public void setSongZ(String songZ) {
        this.songZ = songZ;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
}
