package com.music.bean;

/**
 * Created by 羊 on 2018/10/29.
 */
public class Special {
    private int id;
    private int singerId;
    private String specialName;
    private String specialStyle;
    private String specialLanguage;
    private String specialCompany;
    private String specialSummary;
    private String specialArea;
    private String specialPicture;
    private String createTime;
    private int specialFlag;
    private String specialY;
    private String specialZ;
    private Singer singer;

    public Special() {
    }

    public Special(int id, int singerId, String specialName, String specialStyle, String specialLanguage, String specialCompany, String specialSummary, String specialArea, String specialPicture, String createTime, int specialFlag, String specialY, String specialZ, Singer singer) {
        this.id = id;
        this.singerId = singerId;
        this.specialName = specialName;
        this.specialStyle = specialStyle;
        this.specialLanguage = specialLanguage;
        this.specialCompany = specialCompany;
        this.specialSummary = specialSummary;
        this.specialArea = specialArea;
        this.specialPicture = specialPicture;
        this.createTime = createTime;
        this.specialFlag = specialFlag;
        this.specialY = specialY;
        this.specialZ = specialZ;
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "Special{" +
                "id=" + id +
                ", singerId=" + singerId +
                ", specialName='" + specialName + '\'' +
                ", specialStyle='" + specialStyle + '\'' +
                ", specialLanguage='" + specialLanguage + '\'' +
                ", specialCompany='" + specialCompany + '\'' +
                ", specialSummary='" + specialSummary + '\'' +
                ", specialArea='" + specialArea + '\'' +
                ", specialPicture='" + specialPicture + '\'' +
                ", createTime='" + createTime + '\'' +
                ", specialFlag=" + specialFlag +
                ", specialY='" + specialY + '\'' +
                ", specialZ='" + specialZ + '\'' +
                ", singer=" + singer +
                '}';
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
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

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public String getSpecialStyle() {
        return specialStyle;
    }

    public void setSpecialStyle(String specialStyle) {
        this.specialStyle = specialStyle;
    }

    public String getSpecialLanguage() {
        return specialLanguage;
    }

    public void setSpecialLanguage(String specialLanguage) {
        this.specialLanguage = specialLanguage;
    }

    public String getSpecialCompany() {
        return specialCompany;
    }

    public void setSpecialCompany(String specialCompany) {
        this.specialCompany = specialCompany;
    }

    public String getSpecialSummary() {
        return specialSummary;
    }

    public void setSpecialSummary(String specialSummary) {
        this.specialSummary = specialSummary;
    }

    public String getSpecialArea() {
        return specialArea;
    }

    public void setSpecialArea(String specialArea) {
        this.specialArea = specialArea;
    }

    public String getSpecialPicture() {
        return specialPicture;
    }

    public void setSpecialPicture(String specialPicture) {
        this.specialPicture = specialPicture;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getSpecialFlag() {
        return specialFlag;
    }

    public void setSpecialFlag(int specialFlag) {
        this.specialFlag = specialFlag;
    }

    public String getSpecialY() {
        return specialY;
    }

    public void setSpecialY(String specialY) {
        this.specialY = specialY;
    }

    public String getSpecialZ() {
        return specialZ;
    }

    public void setSpecialZ(String specialZ) {
        this.specialZ = specialZ;
    }
}
