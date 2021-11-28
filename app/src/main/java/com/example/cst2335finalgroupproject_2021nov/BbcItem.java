package com.example.cst2335finalgroupproject_2021nov;

/**
 * bbcitem class to hold data
 */
public class BbcItem {
    private int id;
    private String title;
    private String pubDate;
    private String description;
    private String webUrl;

    /**
     * constructor
     */
    public BbcItem(){
    }


    public BbcItem(int id, String title, String pubDate, String description, String webUrl) {
        this.id = id;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.webUrl = webUrl;
    }

    /**
     * constructor
     * @param title title of article
     * @param pubDate publication date
     * @param description article description
     * @param webUrl URL
     */

    public BbcItem(String title, String pubDate, String description, String webUrl) {
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.webUrl = webUrl;
    }

    /**
     * pulls article id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * method for setting ID
     * @param id
     */
    public void setId( int id) {
        this.id = id;
    }

    /**
     * method for getting title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * method for setting title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * method for date of publication date
     * @return
     */
    public String getPubDate() {
        return pubDate;
    }


    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * method for setting URL
     * @return this will be article URL
     */
    public String getWebUrl() {
        return webUrl;
    }


    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

}
