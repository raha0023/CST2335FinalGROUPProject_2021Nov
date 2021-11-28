package com.example.cst2335finalgroupproject_2021nov;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * creating class for BBC adapter
 * Will link with the data and send it to recyclerView
 */
public class adapter_bbc extends RecyclerView.Adapter<adapter_bbc.BbcViewHolder> {
    /**
     * favdb_bbc is a database of all
     * the articles loaded and saved
     */
    private favdb_bbc favDataBase;
    /**
     * Using google, found a list of BBC articles,
     * this is the ARRAYLIST for the articles
     */
    private ArrayList<BbcItem> bbcItems;

    private Context context;

    /**
     *
     * @param articleList list of articles in ArrayList
     * @param context from the articles pulled from ArrayList
     */

    public adapter_bbc(ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcItem> articleList, Context context) {
        this.bbcItems = articleList;
        this.context = context;
        favDataBase = new favdb_bbc(context);
    }


    public adapter_bbc(ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcItem> articleList){
        bbcItems = articleList;
    }


    @NonNull
    @Override
    public BbcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbc_item, parent, false);
        return new BbcViewHolder(view);
    }

    /**
     * This will link the data with the RecyclerView cited above
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull BbcViewHolder holder, int position) {
        final com.example.cst2335finalgroupproject_2021nov.BbcItem bbcItem = bbcItems.get(position);
        holder.titleTextView.setText(bbcItem.getTitle());
        holder.pubDateTextView.setText(bbcItem.getPubDate());
        holder.descriptionTextView.setText(bbcItem.getDescription());
        holder.linkTextView.setText(bbcItem.getWebUrl());
        holder.favButtn.setOnClickListener((view) -> {
            addTaskDialog(bbcItem);
        });
    }

    /**
     * This will return the number of items held in the adapter
     * @return is the size of the arraylist items
     */
    @Override
    public int getItemCount() {
        if (bbcItems == null)
            return 0;
        else
            return bbcItems.size();
    }

    public class BbcViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, pubDateTextView, descriptionTextView, linkTextView;
        Button favButtn;


        public BbcViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtTitle);
            pubDateTextView = itemView.findViewById(R.id.txtPubDate);
            descriptionTextView = itemView.findViewById(R.id.txtDescription);
            linkTextView = itemView.findViewById(R.id.link);
            favButtn = itemView.findViewById(R.id.favBtn);

        }
    }

    /**
     * Creating the search bar to filter when typing
     * @param filteredList
     */
    public void filterList(ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcItem> filteredList) {
        bbcItems = filteredList;
        notifyDataSetChanged();
    }

    /**
     * Alert dialog for the favourate adding
     * will alert you when you add items to fav using the heart @drawable
     * @param bbcItem
     */
    private void addTaskDialog(com.example.cst2335finalgroupproject_2021nov.BbcItem bbcItem) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.bbcarticlelistlayout, null);
        final TextView titleText = subView.findViewById(R.id.txtTitle);
        final TextView pubDateText = subView.findViewById(R.id.txtPubDate);
        final TextView descriptionText = subView.findViewById(R.id.txtDescription);
        final TextView webLinkText = subView.findViewById(R.id.link);

        titleText.setText(bbcItem.getTitle());
        pubDateText.setText(bbcItem.getPubDate());
        descriptionText.setText(bbcItem.getDescription());
        webLinkText.setText(bbcItem.getWebUrl());

        /**
         * creating the alert message that will display
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.alert_title);
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton(R.string.alert_add, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                BbcFavItem newFavItem = new BbcFavItem(bbcItem.getTitle(), bbcItem.getPubDate(), bbcItem.getDescription(), bbcItem.getWebUrl());
                favDataBase.addArticles(newFavItem);
                Toast.makeText(context, R.string.alert_add, Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton(R.string.alert_negative_btn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, R.string.alert_cancel_toast, Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }
}





