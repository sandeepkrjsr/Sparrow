package com.kodexlabs.sparrow;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class FireAdapter_Story extends FirebaseRecyclerAdapter<Obj_story, FireViewHolder_Story> {

    private Context context;
    private StoryFragment storyFragment;

    public FireAdapter_Story(Class<Obj_story> modelClass, int modelLayout, Class<FireViewHolder_Story> viewHolderClass,
                             Query ref, Context context, StoryFragment storyFragment) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.storyFragment = storyFragment;
        this.context = context;

    }

    @Override
    protected void populateViewHolder(FireViewHolder_Story holder, Obj_story model, int position) {

       storyFragment.hideLoading();

        Picasso.with(context).load(model.getImage()).fit().into(holder.image_entry);

        holder.id_pass.setText(model.getId());
        holder.name_entry.setText(model.getTitle());
        holder.desc_entry.setText(model.getStory());
    }
}
