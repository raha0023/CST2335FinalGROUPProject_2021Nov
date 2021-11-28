package com.example.cst2335finalgroupproject_2021nov;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * creating adapter_bbcfav.java
 * will link RecycleView
 */

public class adapter_bbcfav extends RecyclerView.Adapter<fav_viewholder_bbc>{
    private Context favouriteContext;

    private ArrayList<BbcFavItem> favItemList;
    /**
     * Arraylist of the saved fav articles saved in the database
     */

    private favdb_bbc favDB;

    /**
     * database fav articles saved
     * @param favItemList
     * @param favouriteContext
     */

    public adapter_bbcfav(ArrayList<BbcFavItem> favItemList, Context favouriteContext) {
        this.favouriteContext = favouriteContext;
        this.favItemList = favItemList;
    }


    @NonNull
    @Override
    public fav_viewholder_bbc onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbcfavitem, parent, false);
        favDB = new favdb_bbc(favouriteContext);
    return new fav_viewholder_bbc(view);
    }

    /**
     * Link wiht RecyclerView
     * @param holder
     * @param position
     */


    @Override
    public void onBindViewHolder(fav_viewholder_bbc holder, int position) {
        holder.favTitleView.setText(favItemList.get(position).getFavourite_title());
        holder.favPubDateView.setText(favItemList.get(position).getFavourite_pubDate());
        holder.favDescriptionView.setText(favItemList.get(position).getFav_description());
        holder.favLinkView.setText(favItemList.get(position).getFav_webUrl());
        holder.deleteBtn.setOnClickListener((view) -> {
            favDB.deleteArticle(favItemList.get(position).getId());
            removeItem(position);
        });
    }

    /**
     * returns total # of items in adapter
     * @return size of list
     */
    @Override
    public int getItemCount () {
        if (favItemList == null)
            return 0;
        else
            return favItemList.size();
    }


    private void removeItem ( int position){
        favItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, favItemList.size());
    }


    public void filterList (ArrayList <BbcFavItem> filteredList) {
        favItemList = filteredList;
        notifyDataSetChanged();
    }
}