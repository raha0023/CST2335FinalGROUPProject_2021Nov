package com.example.cst2335finalgroupproject_2021nov;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Class to link and describe item view within recylerview
 */

public class fav_viewholder_bbc extends RecyclerView.ViewHolder {

    private ArrayList<com.example.cst2335finalgroupproject_2021nov.BbcFavItem> favItemList;
    private favdb_bbc favDataBase;
    TextView favTitleView, favPubDateView, favDescriptionView, favLinkView;
    Button deleteBtn;


    public fav_viewholder_bbc(@NonNull View itemView) {
            super(itemView);
            favTitleView = itemView.findViewById(R.id.artTitle);
            favPubDateView = itemView.findViewById(R.id.artPubDate);
            favDescriptionView = itemView.findViewById(R.id.artDescription);
            favLinkView = itemView.findViewById(R.id.artLink);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            deleteBtn.setOnClickListener((view) -> {
                int position = getAdapterPosition();
                final com.example.cst2335finalgroupproject_2021nov.BbcFavItem favItem = favItemList.get(position);
                favDataBase.deleteArticle(favItem.getId());
                removeItem(position);
            });

    }

    /**
     * remove items from fav list
     * @param position
     */
    private void removeItem(int position){
        favItemList.remove(position);

    }

}