package com.example.cst2335finalgroupproject_2021nov;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * first page in app, articles will show in this class
 */

public class activity_first_bbc extends AppCompatActivity {

    public static final int REQUEST_RETURN_PAGE = 500;

    /**
     * adapter for retreiving views and details of article
     */

    private adapter_bbc adapterbbc;

    /**
     * array loaded from URL
     */

    private ArrayList<BbcItem> itemsbbc = new ArrayList<>();

    private RecyclerView recyclerView;

    /**
     * favourate list and return button
     */

    ImageButton favoriteButttn;

    private LinearLayout linearLayout;

    favdb_bbc favdataBase;

    private ProgressBar progressBar;

    /**
     * method to initialize
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bbcfirstactivity);

        /**
         * calling recyclerview method
         */
        buildRecyclerView();

        linearLayout = findViewById(R.id.linearLayout);
        favdataBase = new favdb_bbc(this);
        Toolbar toolBar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar2);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        EditText edSearch = findViewById(R.id.searchbartext);
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
         * snackbar
         */

        favoriteButttn = findViewById(R.id.favoriteBtn);
        if (favoriteButttn != null) {
            favoriteButttn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goToFavorites = new Intent(activity_first_bbc.this, activity_bbc_fav.class);
                    startActivityForResult(goToFavorites, REQUEST_RETURN_PAGE);
                    showSnackbar();
                }
            });
        }

        /**
         * load jsonrss from interawebz
         */

        Content content = new Content();
        content.execute("https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.bbci.co.uk%2Fnews%2Fworld%2Fus_and_canada%2Frss.xml");

        if(findViewById(R.id.frameLayout) != null) {
            BbcFragment bbcFragment = new BbcFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bbcFragment).commit();
        }
    }

    /**
     * fav snackbar
     */

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(linearLayout, getText(R.string.snackbar_message), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar1 = Snackbar.make(linearLayout, getText(R.string.undo_message), Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();
    }

    /**
     * article filter for search
     * @param text
     */
    private void filter(String text) {
        ArrayList<BbcItem> filteredList = new ArrayList<>();
        for (BbcItem item : itemsbbc) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapterbbc.filterList(filteredList);
    }


    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapterbbc = new adapter_bbc(itemsbbc, this);
        recyclerView.setAdapter(adapterbbc);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_RETURN_PAGE) {
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }

    private class Content extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {
            publishProgress(0);
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                InputStream response = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                publishProgress(50);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                String result = sb.toString();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String artTitle = item.getString("title");
                    String artPubDate = item.getString("pubDate");
                    String description = item.getString("description");
                    String linkUrl = item.getString("link");
                    itemsbbc.add(new BbcItem(artTitle, artPubDate, description, linkUrl));
                }
                adapterbbc = new adapter_bbc(itemsbbc, activity_first_bbc.this);
                publishProgress(75);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return "Finished task";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }

        protected void onPostExecute(String str) {
            progressBar.setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(adapterbbc);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bbcmainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;

        switch(item.getItemId())
        {

            /**
             * help message when you click ? in the app , shows switch statement and activity
             */
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

        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }
}

