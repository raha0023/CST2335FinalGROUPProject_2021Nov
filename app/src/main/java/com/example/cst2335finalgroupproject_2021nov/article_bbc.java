package com.example.cst2335finalgroupproject_2021nov;

/**
 * this article_bbc class that holds the data
 * used for data storage of the strings below
 */
public class article_bbc {
    private int id;
    private String title;
    private String description;
    private String webUrl;
    private String pubDate;


    public article_bbc(String title, String pubDate, String description, String webUrl) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.webUrl = webUrl;
    }


    public article_bbc(int id, String title, String pubDate, String description, String webUrl) {

        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.webUrl = webUrl;
    }

    public void changeTitle(String text){
        title = text;
    }

    /**
     * Method used to set title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }


    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * setting the weburl method
     * @param webUrl
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * method used to getId @wreturn will be the the ID
     * @return
     */
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getPubDate() {
        return pubDate;
    }

    /**
     * will pull up article title to display
     * @return
     */

    public String getTitle() {
        return title;
    }


    /**
     * pulls up description from the article pulled
     * @return
     */
    public String getDescription() {
        return description;
    }


    public String getWebUrl() {
        return webUrl;
    }

}
