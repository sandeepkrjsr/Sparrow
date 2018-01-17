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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class StoryViewActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private ImageView startup_image;
    private TextView srt_founder, srt_publisher, srt_link, srt_desc, srt_label_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("fetching content...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        fetchMsg();

        startup_image = (ImageView)findViewById(R.id.startup_image);
        srt_desc = (TextView)findViewById(R.id.srt_desc);
        srt_publisher = (TextView)findViewById(R.id.srt_publisher);
        srt_link = (TextView)findViewById(R.id.srt_link);
        srt_label_link = (TextView)findViewById(R.id.srt_label_link);
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

        String url = "https://sparrow-d5f1d.firebaseio.com/Stories/"+getIntent().getExtras().getString("Id");

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Obj_story obj_story = dataSnapshot.getValue(Obj_story.class);
                Picasso.with(getBaseContext()).load(obj_story.getImage()).fit().into(startup_image);

                if(obj_story.getWebsite().equals("NULL")){
                    srt_link.setVisibility(View.GONE);
                    srt_label_link.setVisibility(View.GONE);
                }
                else
                    srt_link.setText(obj_story.getWebsite());
                actionBar.setTitle(obj_story.getTitle());
                srt_publisher.setText(obj_story.getPublisher());
                srt_desc.setText(obj_story.getStory());
                srt_link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(obj_story.getWebsite()));
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
