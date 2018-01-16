package com.yx.lucencedemo2;

/**
 * Created by MyPC on 2018/1/16.
 */
public class book {

    private Integer id;
    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCountnum() {
        return countnum;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
    }

    public book(Integer id, String author, String content, int countnum) {

        this.id = id;
        this.author = author;
        this.content = content;
        this.countnum = countnum;
    }

    private String content;
    private int countnum;

    public book() {
    }

    @Override
    public String toString() {
        return "book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", countnum=" + countnum +
                '}';
    }
}
