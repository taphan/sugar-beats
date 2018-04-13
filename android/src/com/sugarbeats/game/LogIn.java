package com.sugarbeats.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sugarbeats.service.INet;


public class LogIn implements INet {

    // Client used to sign in with Google APIs
    private GoogleSignInClient mGoogleSignInClient = null;
    private Activity activity;

    // Request code used to invoke sign in user interactions.
    private static final int RC_SIGN_IN = 9001;


//Create a sign-in client via the GoogleSignInOptions object.
    //In the GoogleSignInOptions.Builder to configure your sign-in, you must specify GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN.

    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    private GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestEmail()
            .build();

    public LogIn(Activity act){
        this.activity = act;
        mGoogleSignInClient = GoogleSignIn.getClient(act, gso);

    }

    //determine if a player is already signed in
    private boolean isSignedIn(){
        return GoogleSignIn.getLastSignedInAccount(activity) != null;
    }



    public void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(activity,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        signInClient.silentSignIn().addOnCompleteListener(activity,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            GoogleSignInAccount signedInAccount = task.getResult();
                            Log.d("SIGNEDINACC",signedInAccount.toString());
                        } else {
                            // Player will need to sign-in explicitly using via UI
                        }
                    }
                });
    }



    //Interactive Sign-IN
    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(activity,
                GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        Intent intent = signInClient.getSignInIntent();
//        startActivityForResult(intent , RC_SIGN_IN);
    }












}
