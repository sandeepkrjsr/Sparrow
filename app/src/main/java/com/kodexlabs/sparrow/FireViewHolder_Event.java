package com.kodexlabs.sparrow;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class FireViewHolder_Event extends RecyclerView.ViewHolder implements View.OnClickListener{
    public CardView cardView;

    public TextView name_entry, desc_entry, date_entry, id_pass, venue_entry;
    public ImageView image_entry;

    public FireViewHolder_Event(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
            cardView = (CardView) itemView.findViewById(R.id.event_card_view);
            name_entry = (TextView) itemView.findViewById(R.id.tx_name);
            date_entry = (TextView) itemView.findViewById(R.id.tx_date);
            id_pass = (TextView) itemView.findViewById(R.id.tx_id);
            venue_entry = (TextView) itemView.findViewById(R.id.tx_venue);
        image_entry = (ImageView)itemView.findViewById(R.id.event_bg);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(v.getContext(), EventViewActivity.class);
        intent.putExtra("Id", id_pass.getText().toString());
        v.getContext().startActivity(intent);


    }
}
