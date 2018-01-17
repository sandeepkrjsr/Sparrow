package com.kodexlabs.sparrow;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIdSer extends FirebaseInstanceIdService {

    private static final String REC_TOKEN = "REC_TOKEN";

    @Override
    public void onTokenRefresh() {
        String rec_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REC_TOKEN, rec_token);
    }
}
