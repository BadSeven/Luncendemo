package com.yx.lucence;

/**
 * Created by MyPC on 2018/1/16.
 */
public class Article {

    private Integer id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contenet='" + content + '\'' +
                '}';
    }

    public Article() {
    }

    public Article(Integer id, String title, String contenet) {

        this.id = id;
        this.title = title;
        this.content = contenet;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setContenet(String content) {
        this.content = content;
    }
}
