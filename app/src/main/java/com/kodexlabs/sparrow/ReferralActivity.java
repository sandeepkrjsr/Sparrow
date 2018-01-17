package com.kodexlabs.sparrow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReferralActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private ArrayList<String> ref;
    private Button but_submit;
    private ProgressDialog progressDialog;
    private EditText tx_ref;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("ref_verify", MODE_PRIVATE);
        if (!preferences.getBoolean("verified", false)) {
            setContentView(R.layout.activity_refferal);
            but_submit = (Button)findViewById(R.id.but_submit);
            tx_ref = (EditText)findViewById(R.id.tx_ref);

            but_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loading();
                    verify_ref();
                }
            });
        }

        else{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    public void verify_ref() {
        String url = "https://sparrow-d5f1d.firebaseio.com/Referral";

        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ref = new ArrayList<String>();

                for (DataSnapshot dsp : dataSnapshot.getChildren())
                    ref.add(String.valueOf(dsp.getValue()));

                progressDialog.dismiss();

                if(ref.contains(tx_ref.getText().toString())){
                    final SharedPreferences pref = getSharedPreferences("ref_verify", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("verified", true);
                    editor.commit();
                    startMain();
                }
                else{
                    Toast.makeText(getBaseContext(), "Invalid Referral Code",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void startMain() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void loading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Verifying Referral...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
