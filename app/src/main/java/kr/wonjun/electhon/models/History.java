package kr.wonjun.electhon.models;

import java.util.Date;

public class History {
    private int resource;
    private String title;
    private String date;
    private String content;
    private int price;

    public History(String title, String content, int price, int resource, String date) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.resource = resource;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price + "";
    }
}
