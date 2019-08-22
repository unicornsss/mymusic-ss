package com.music.bean;

/**
 * Created by 羊 on 2018/10/29.
 */
public class Administrator {
    private int id;
    private String administratorNumber;
    private String password;

    public Administrator() {
    }

    public Administrator(int id, String administratorNumber, String password) {
        this.id = id;
        this.administratorNumber = administratorNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "id=" + id +
                ", administratorNumber='" + administratorNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdministratorNumber() {
        return administratorNumber;
    }

    public void setAdministratorNumber(String administratorNumber) {
        this.administratorNumber = administratorNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
