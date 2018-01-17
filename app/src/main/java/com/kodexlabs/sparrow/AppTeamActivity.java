package com.kodexlabs.sparrow;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AppTeamActivity extends AppCompatActivity {

    private TextView prof_aloo, pro_hima, pro_san, pro_pank, pro_sam, copyright;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_team);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Developers");

        pro_pank = (TextView)findViewById(R.id.prof_pank);
        pro_sam = (TextView)findViewById(R.id.prof_sam);
        pro_hima = (TextView)findViewById(R.id.prof_him);
        pro_san = (TextView)findViewById(R.id.prof_san);
        prof_aloo = (TextView) findViewById(R.id.prof_aloo);
        copyright = (TextView)findViewById(R.id.copyright);



        pro_pank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = newFacebookIntent(getPackageManager(), "https://www.facebook.com/profile.php?id=100004315843054");
                startActivity(fbIntent);
            }
        });

        pro_hima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = newFacebookIntent(getPackageManager(), "https://facebook.com/himanshuk27");
                startActivity(fbIntent);
            }
        });

        pro_sam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = newFacebookIntent(getPackageManager(), "https://facebook.com/samsruti");
                startActivity(fbIntent);

            }
        });

        pro_san.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = newFacebookIntent(getPackageManager(), "https://facebook.com/sandeepkr.jsr");
                startActivity(fbIntent);

            }
        });
        prof_aloo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = newFacebookIntent(getPackageManager(), "https://facebook.com/alabhyavaibhav");
                startActivity(fbIntent);
            }
        });

        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fbIntent = newFacebookIntent(getPackageManager(), "https://www.facebook.com/officialkodeX");
                startActivity(fbIntent);
            }
        });

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


    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri;
        try {
            pm.getPackageInfo("com.facebook.katana", 0);
            // http://stackoverflow.com/a/24547437/1048340
            uri = Uri.parse("fb://facewebmodal/f?href=" + url);
        } catch (PackageManager.NameNotFoundException e) {
            uri = Uri.parse(url);
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }
}
