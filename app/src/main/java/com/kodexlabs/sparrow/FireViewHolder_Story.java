package com.kodexlabs.sparrow;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FireViewHolder_Story extends RecyclerView.ViewHolder implements View.OnClickListener{
    public CardView cardView;

    public TextView name_entry, desc_entry, id_pass;
    public ImageView image_entry;

    public FireViewHolder_Story(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        cardView = (CardView) itemView.findViewById(R.id.story_card_view);
        name_entry = (TextView) itemView.findViewById(R.id.tx1_name);
        desc_entry = (TextView) itemView.findViewById(R.id.tx1_desc);
        id_pass = (TextView) itemView.findViewById(R.id.tx_id);
        image_entry = (ImageView) itemView.findViewById(R.id.story_bg);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(v.getContext(), StoryViewActivity.class);
        intent.putExtra("Id", id_pass.getText().toString());
        v.getContext().startActivity(intent);

    }
}
