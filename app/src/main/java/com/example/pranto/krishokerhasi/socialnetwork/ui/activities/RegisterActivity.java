package com.example.pranto.krishokerhasi.socialnetwork.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.pranto.krishokerhasi.R;
import com.example.pranto.krishokerhasi.socialnetwork.models.User;
import com.example.pranto.krishokerhasi.socialnetwork.utils.BaseActivity;
import com.example.pranto.krishokerhasi.socialnetwork.utils.FirebaseUtils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 9001;
    public int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.button_sign_in).setOnClickListener(this);
        //flag = getIntent().getIntExtra("flag", 0);
    }


    @Override
    public void onClick(View v) {

        /*if (flag==1)
        {
            showProgressDialog();
            signIn();
            flag = 0;
        }*/
        switch (v.getId()) {
            case R.id.button_sign_in:
                showProgressDialog();
                signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == RC_SIGN_IN) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result.isSuccess()) {
                    GoogleSignInAccount account = result.getSignInAccount();
                    firebaseAuthWithGoogle(account);
                } else {
                    dismissProgressDialog();
                }
            } else {
                dismissProgressDialog();
            }
        } else {
            dismissProgressDialog();
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Let's create the models quick(I know! it's weird time in this tutorial
                            //To be building it
                            User user = new User();
                            String photoUrl = null;

                            if (account.getPhotoUrl() != null) {
                                user.setPhotoUrl(account.getPhotoUrl().toString());
                                //photoUrl = account.getPhotoUrl().toString();
                            }
                            // User user = new User(account.getDisplayName(), account.getEmail(), photoUrl, mAuth.getCurrentUser().getUid());
                            user.setEmail(account.getEmail());
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+account.getDisplayName().toString());
                            user.setUser(account.getDisplayName());
                            user.setUid(mAuth.getCurrentUser().getUid());

                            FirebaseUtils.getUserRef(account.getEmail().replace(".", ","))
                                    .setValue(user, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                            mFirebaseUser = mAuth.getCurrentUser();
                                            finish();
                                        }
                                    });
                        } else {
                            dismissProgressDialog();
                        }
                    }
                });
    }


}

