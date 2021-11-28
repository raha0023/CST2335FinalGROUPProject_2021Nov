package com.example.cst2335finalgroupproject_2021nov;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**creating class for bbc adapter */
public class activity_bbc_fav extends AppCompatActivity {

    ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcFavItem> bbcFavItems = new ArrayList<>();
    private favdb_bbc favDataBase;
    ImageButton returnButttn;
    private adapter_bbcfav favAdapter;
    private RecyclerView favRecyclerView;

    /**
     * used prior to activity initialisation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbcfavouritelist);

        favDataBase = new favdb_bbc(this);
        favRecyclerView = findViewById(R.id.recyclerView);
        favRecyclerView.setHasFixedSize(true);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toolbar tBar = findViewById(R.id.toolbar);
        setSupportActionBar(tBar);

/**
 * calling the database from other activity
 */
        loadData();

    /**
    * for article search bar
    */
        EditText edSearch = findViewById(R.id.searchEditText2);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        /**
         * pulling intent from last page loaded
         */

        Intent resultIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, resultIntent);

        /**
         * creating return button to return to last page
         */
        returnButttn = findViewById(R.id.returnBtn);
        returnButttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     * load database information
     */
    private void loadData() {
        if (bbcFavItems != null){
            bbcFavItems.clear();
        }
        SQLiteDatabase db = favDataBase.getReadableDatabase();
        Cursor cursor = favDataBase.select_all_favorite_list();
        try{
            while(cursor. moveToNext()){
                long id = cursor.getLong(cursor.getColumnIndex(favdb_bbc.COL_ID));
                String title = cursor.getString(cursor.getColumnIndex(favdb_bbc.COL_TITLE));
                String pubDate = cursor.getString(cursor.getColumnIndex(favdb_bbc.COL_PUBDATE));
                String description = cursor.getString(cursor.getColumnIndex(favdb_bbc.COL_DESCRIPTION));
                String webLink = cursor.getString(cursor.getColumnIndex(favdb_bbc.COL_URL));
                BbcFavItem favItem = new BbcFavItem(title, pubDate, description, webLink);
                favItem.setId((int)id);
                bbcFavItems.add(favItem);
            }
        }finally {
            if(cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
        favAdapter = new adapter_bbcfav(bbcFavItems,this);
        favRecyclerView.setAdapter(favAdapter);
    }

    /**
     * filtiner search
     * @param text
     */
    private void filter(String text){
        ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcFavItem> filteredList = new ArrayList<>();
        for (com.example.cst2335finalgroupproject_2021nov.BbcFavItem item : bbcFavItems){
            if (item.getFavourite_title().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        favAdapter.filterList(filteredList);
    }

    /**
     * create toolbar menu with various options
     * @param menu options will be placed here
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bbcmainmenu, menu);
        return true;
        /**
         * inflating menu for actionbar
         */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;

        switch(item.getItemId())
        {
            case R.id.helpicon:
                message = getString(R.string.help_toast);
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.help__menu_title))
                        .setMessage(getString(R.string.help_alert))
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id){

                            }
                        })
                        .show();

                break;
            case R.id.mail:
                message = getString(R.string.mail_toast);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(getString(R.string.text_plain));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.sample_email)});
                intent.putExtra(Intent.EXTRA_SUBJECT, (getString(R.string.subj_here)));
                intent.putExtra(Intent.EXTRA_TEXT, (getString(R.string.body_text)));
                startActivity(intent);
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }


}






