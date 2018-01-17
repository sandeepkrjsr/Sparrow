package com.kodexlabs.sparrow;

import android.content.Context;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;


public class FireAdapter_Event extends FirebaseRecyclerAdapter<Obj_event, FireViewHolder_Event> {

    private Context context;
    private EventFragment eventFragment;

    public FireAdapter_Event(Class<Obj_event> modelClass, int modelLayout, Class<FireViewHolder_Event> viewHolderClass,
                             Query ref, Context context, EventFragment eventFragment) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.eventFragment = eventFragment;
        this.context = context;

    }

    @Override
    protected void populateViewHolder(FireViewHolder_Event holder, Obj_event model, int position) {

        eventFragment.hideLoading();

        Picasso.with(context).load(model.getImage()).fit().transform(new GradientTransformation()).into(holder.image_entry);
        holder.image_entry.setImageResource(R.drawable.gradient);

        holder.id_pass.setText(model.getId());
        holder.name_entry.setText(model.getName());
        holder.date_entry.setText(model.getDate());
        holder.venue_entry.setText(model.getVenue());

    }

}
