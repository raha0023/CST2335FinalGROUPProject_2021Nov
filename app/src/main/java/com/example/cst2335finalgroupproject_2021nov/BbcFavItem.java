package com.example.cst2335finalgroupproject_2021nov;

public class BbcFavItem {
    private int id;
    private String favourite_title;
    private String favourite_pubDate;
    private String fav_description;
    private String fav_webUrl;


    public BbcFavItem(){
    }

    /**
     * constructor
     * @param id article name
     * @param favourite_title title of article
     * @param favourite_pubDate publication date
     * @param fav_description description of article pulled from feed
     * @param fav_webUrl link of article
     */
    public BbcFavItem(int id, String favourite_title, String favourite_pubDate, String fav_description, String fav_webUrl) {
        this.id = id;
        this.favourite_title = favourite_title;
        this.favourite_pubDate = favourite_pubDate;
        this.fav_description = fav_description;
        this.fav_webUrl = fav_webUrl;
    }

    /**
     *
     * @param favourite_title title of favourate article
     * @param favourite_pubDate publication dates
     * @param fav_description date of release
     * @param fav_webUrl Url...like its in the name...lol
     */

    public BbcFavItem(String favourite_title, String favourite_pubDate, String fav_description, String fav_webUrl) {
        this.favourite_title = favourite_title;
        this.favourite_pubDate = favourite_pubDate;
        this.fav_description = fav_description;
        this.fav_webUrl = fav_webUrl;
    }

    /**
     * get ID method
     * @return returns ID
     */
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    /**
     * Article title method to get it
     * @return
     */

    public String getFavourite_title() {
        return favourite_title;
    }

    /**
     * Method for getting title of article
     * @param favourite_title
     */
    public void setFavourite_title(String favourite_title) {
        this.favourite_title = favourite_title;
    }

    /**
     * method for article date
     * @return
     */
    public String getFavourite_pubDate() {
        return favourite_pubDate;
    }


    public void setFavourite_pubDate(String favourite_pubDate) {
        this.favourite_pubDate = favourite_pubDate;
    }


    public String getFav_description() {
        return fav_description;
    }


    public void setFav_description(String fav_description) {
        this.fav_description = fav_description;
    }


    public String getFav_webUrl() {
        return fav_webUrl;
    }


    public void setFav_webUrl(String fav_webUrl) {
        this.fav_webUrl = fav_webUrl;
    }

}
