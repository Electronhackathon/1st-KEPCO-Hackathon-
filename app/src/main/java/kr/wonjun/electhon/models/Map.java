package kr.wonjun.electhon.models;

import java.io.Serializable;

public class Map implements Serializable {
    private String title, address;
    private double x, y;

    public Map(String title, String address, double x, double y) {
        this.title = title;
        this.address = address;
        this.x = x;
        this.y = y;
    }

    public String getTitle() {
        return title.replace("<b>", "").replace("</b>", "");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
