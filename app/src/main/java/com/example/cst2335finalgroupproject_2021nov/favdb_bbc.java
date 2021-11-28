package com.example.cst2335finalgroupproject_2021nov;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * create and update database class
 */
public class favdb_bbc extends SQLiteOpenHelper {

    /**
     * DB version
     */

    private static final int DATABASE_VERSION = 1;
    /**
     * naming DB
     */

    private static final String DATABASE_NAME = "BbcNewsArticlesDB";
    /**
     * naming table
     */

    private static final String TABLE_NAME = "BbcNewsArticles";
    /**
     * Col ID #
     */

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_PUBDATE = "pubDate";
    /**
     * publication date
     */

    public static final String COL_DESCRIPTION = "description";
    /**
     * COL URL linkage to name
     */

    public static final String COL_URL = "weblink";


    /**
     * context of itemsx in fav_db
     * @param context
     */
    favdb_bbc(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * If not DB found method is called
     * @param db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BBC_ARTICLE_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                + COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_TITLE + " TEXT, " + COL_PUBDATE + " TEXT, "
                + COL_DESCRIPTION + " TEXT, " + COL_URL  + " TEXT)";
        db.execSQL(CREATE_BBC_ARTICLE_TABLE);
    }

    /**
     * If DB version is lower this will be called
     * @param db database
     * @param oldVersion oldversion
     * @param newVersion new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcFavItem> listFavItems() {
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcFavItem> storeFavArticles = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                String pubDate = cursor.getString(2);
                String description = cursor.getString(3);
                String webUrl = cursor.getString(4);
                storeFavArticles.add(new com.example.cst2335finalgroupproject_2021nov.BbcFavItem(title, pubDate, description, webUrl));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return storeFavArticles;
    }

    /**
     * adds fav article to fav list db
     * @param bbcFavItem
     */
    void addArticles(com.example.cst2335finalgroupproject_2021nov.BbcFavItem bbcFavItem) {
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, bbcFavItem.getFavourite_title());
        values.put(COL_PUBDATE, bbcFavItem.getFavourite_pubDate());
        values.put(COL_DESCRIPTION, bbcFavItem.getFav_description());
        values.put(COL_URL, bbcFavItem.getFav_webUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);

    }

    void updateArticles(com.example.cst2335finalgroupproject_2021nov.BbcFavItem bbcFavItem) {
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, bbcFavItem.getFavourite_title());
        values.put(COL_PUBDATE, bbcFavItem.getFavourite_pubDate());
        values.put(COL_DESCRIPTION, bbcFavItem.getFav_description());
        values.put(COL_URL, bbcFavItem.getFav_webUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, COL_ID + " = ?", new String[]{String.valueOf(bbcFavItem.getId())});
    }

    /**
     * used to delete article from fav list
     * @param id
     */
    void deleteArticle(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }


    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME;
        return db.rawQuery(sql,null);
    }


}
