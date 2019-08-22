package com.music.bean;

/**
 * Created by 羊 on 2018/10/30.
 */
public class SingerType {
    private int idSingerType;
    private String singerType;

    public SingerType() {
    }

    public SingerType(int idSingerType, String singerType) {
        this.idSingerType = idSingerType;
        this.singerType = singerType;
    }

    @Override
    public String toString() {
        return "SingerType{" +
                "idSingerType=" + idSingerType +
                ", singerType='" + singerType + '\'' +
                '}';
    }

    public int getIdSingerType() {
        return idSingerType;
    }

    public void setIdSingerType(int idSingerType) {
        this.idSingerType = idSingerType;
    }

    public String getSingerType() {
        return singerType;
    }

    public void setSingerType(String singerType) {
        this.singerType = singerType;
    }
}
