package com.kodexlabs.sparrow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EventViewActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private TextView evt_organ;
    private TextView evt_desc;
    private TextView evt_date;
    private TextView evt_venue;
    private TextView evt_link;
    private ImageView event_image;
    private ActionBar actionBar;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("fetching content...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        evt_organ = (TextView)findViewById(R.id.evt_organ);
        evt_desc = (TextView)findViewById(R.id.evt_desc);
        evt_date = (TextView)findViewById(R.id.evt_date);
        evt_venue = (TextView)findViewById(R.id.evt_venue);
        evt_link = (TextView)findViewById(R.id.evt_link);
        event_image = (ImageView)findViewById(R.id.event_image);

        fetchMsg();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void fetchMsg() {

        String url = "https://sparrow-d5f1d.firebaseio.com/Events/"+getIntent().getExtras().getString("Id");

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Obj_event obj_event = dataSnapshot.getValue(Obj_event.class);
                Picasso.with(getBaseContext()).load(obj_event.getImage()).fit().into(event_image);
                actionBar.setTitle(obj_event.getName());
                evt_organ.setText(obj_event.getOrganiser());
                evt_venue.setText(obj_event.getVenue());
                evt_date.setText(obj_event.getDate());
                evt_desc.setText(obj_event.getDesc());
                evt_link.setText(obj_event.getWebsite());

                evt_link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(obj_event.getWebsite()));
                        startActivity(intent);
                    }
                });

                if(progressDialog.isShowing())
                    progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
