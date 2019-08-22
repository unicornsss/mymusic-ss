package com.music.bean;

import java.util.List;

/**
 * Created by 羊 on 2018/10/29.
 */
public class Singer {
    private int id;
    private String singerName;
    private String singerSummary;
    private String singerCountry;
    private String singerSex;
    private String singerStyle;
    private String singerPicture;
    private String singerNotice;
    private int singerFlag;
    private String singerY;
    private List<Songs> songsList;
    public Singer() {
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", singerName='" + singerName + '\'' +
                ", singerSummary='" + singerSummary + '\'' +
                ", singerCountry='" + singerCountry + '\'' +
                ", singerSex='" + singerSex + '\'' +
                ", singerStyle='" + singerStyle + '\'' +
                ", singerPicture='" + singerPicture + '\'' +
                ", singerNotice='" + singerNotice + '\'' +
                ", singerFlag=" + singerFlag +
                ", singerY='" + singerY + '\'' +
                ", songsList=" + songsList +
                '}';
    }

    public Singer(int id, String singerName, String singerSummary, String singerCountry, String singerSex, String singerStyle, String singerPicture, String singerNotice, int singerFlag, String singerY, List<Songs> songsList) {
        this.id = id;
        this.singerName = singerName;
        this.singerSummary = singerSummary;
        this.singerCountry = singerCountry;
        this.singerSex = singerSex;
        this.singerStyle = singerStyle;
        this.singerPicture = singerPicture;
        this.singerNotice = singerNotice;
        this.singerFlag = singerFlag;
        this.singerY = singerY;
        this.songsList = songsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerSummary() {
        return singerSummary;
    }

    public void setSingerSummary(String singerSummary) {
        this.singerSummary = singerSummary;
    }

    public String getSingerCountry() {
        return singerCountry;
    }

    public void setSingerCountry(String singerCountry) {
        this.singerCountry = singerCountry;
    }

    public String getSingerSex() {
        return singerSex;
    }

    public void setSingerSex(String singerSex) {
        this.singerSex = singerSex;
    }

    public String getSingerStyle() {
        return singerStyle;
    }

    public void setSingerStyle(String singerStyle) {
        this.singerStyle = singerStyle;
    }

    public String getSingerPicture() {
        return singerPicture;
    }

    public void setSingerPicture(String singerPicture) {
        this.singerPicture = singerPicture;
    }

    public String getSingerNotice() {
        return singerNotice;
    }

    public void setSingerNotice(String singerNotice) {
        this.singerNotice = singerNotice;
    }

    public int getSingerFlag() {
        return singerFlag;
    }

    public void setSingerFlag(int singerFlag) {
        this.singerFlag = singerFlag;
    }

    public String getSingerY() {
        return singerY;
    }

    public void setSingerY(String singerY) {
        this.singerY = singerY;
    }

    public List<Songs> getSongsList() {
        return songsList;
    }

    public void setSongsList(List<Songs> songsList) {
        this.songsList = songsList;
    }
}
