package com.myp.myapplication;

/**
 * Created by Administrator on 2018/5/10.
 */

public class Book{

    private String bookName;
    private String author;
    private int publishTime;
    private boolean isSelected = false;
    public Book(){};

    public Book(String bookName,String author,int publishTime){
        this.bookName = bookName;
        this.author = author;
        this.publishTime = publishTime;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPublishTime() {
        return publishTime;
    }
    public void setPublishTime(int publishTime) {
        this.publishTime = publishTime;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}