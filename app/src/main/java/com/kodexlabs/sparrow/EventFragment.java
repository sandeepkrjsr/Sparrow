package com.kodexlabs.sparrow;

import android.content.Context;
import android.content.SharedPreferences;
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

public class EventFragment extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FireAdapter_Event eventFireAdapter;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar progressBar;

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.event_loading);
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://sparrow-d5f1d.firebaseio.com/Events");
        databaseReference.keepSynced(true);
        eventFireAdapter = new FireAdapter_Event(Obj_event.class, R.layout.event_row_layout, FireViewHolder_Event.class,
                databaseReference, getContext(), this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.event_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(eventFireAdapter);

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
