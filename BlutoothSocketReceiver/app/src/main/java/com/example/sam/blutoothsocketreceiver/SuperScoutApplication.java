package com.example.sam.blutoothsocketreceiver;

import android.app.Application;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.example.sam.blutoothsocketreceiver.firebase_classes.Match;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.instabug.library.IBGInvocationEvent;
import com.instabug.library.Instabug;

/**
 * Created by sam on 1/31/16.
 */

public class SuperScoutApplication extends Application {
    String url = Constants.dataBaseUrl;

    @Override
    public void onCreate() {

        super.onCreate();
        new Instabug.Builder(this, "your_app_token_here")
                .setInvocationEvent(IBGInvocationEvent.IBGInvocationEventShake)
                .build();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
        Firebase firebase = new Firebase(url);
        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Please wait...", Toast.LENGTH_SHORT).show();
            }
        };
        if (url.equals("https://1678-scouting-2016.firebaseio.com/")) {
            firebase.authWithCustomToken("qVIARBnAD93iykeZSGG8mWOwGegminXUUGF2q0ee", authResultHandler);
        } else if (url.equals("https://1678-dev3-2016.firebaseio.com/")) {
            firebase.authWithCustomToken("AEduO6VFlZKD4v10eW81u9j3ZNopr5h2R32SPpeq", authResultHandler);
        } else if (url.equals("https://1678-dev-2016.firebaseio.com/")) {
            firebase.authWithCustomToken("j1r2wo3RUPMeUZosxwvVSFEFVcrXuuMAGjk6uPOc", authResultHandler);
        } else if (url.equals("https://1678-dev2-2016.firebaseio.com/")) {
            firebase.authWithCustomToken("hL8fStivTbHUXM8A0KXBYPg2cMsl80EcD7vgwJ1u", authResultHandler);
        }
        FirebaseLists.matchesList = new FirebaseList<>(url + "Matches/", new FirebaseList.FirebaseUpdatedCallback() {
            @Override
            public void execute() {
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent("matches_updated"));
            }
        }, Match.class);

        /*Thread.setDefaultUncaughtExceptionHandler (new Thread.UncaughtExceptionHandler()
        {
            @Override
            public void uncaughtException (Thread thread, Throwable e)
            {
                handleUncaughtException (thread, e);
            }
        });

    }

    private void handleUncaughtException (Thread thread, Throwable e)
    {

        // The following shows what I'd like, though it won't work like this.
        Intent intent = new Intent (getApplicationContext(),MainActivity.class);
        startActivity(intent);

        // Add some code logic if needed based on your requirement
    }
*/
    }
}

