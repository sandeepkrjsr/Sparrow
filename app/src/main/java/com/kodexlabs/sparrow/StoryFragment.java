package com.kodexlabs.sparrow;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class StoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FireAdapter_Story storyFireAdapter;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar progressBar;

    public StoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_story, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.story_loading);
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sparrow-d5f1d.firebaseio.com/Stories");
        databaseReference.keepSynced(true);
        storyFireAdapter = new FireAdapter_Story(Obj_story.class, R.layout.story_row_layout, FireViewHolder_Story.class,
                databaseReference, getContext(), this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.story_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setAdapter(storyFireAdapter);

        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
