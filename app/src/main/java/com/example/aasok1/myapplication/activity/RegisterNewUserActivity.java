package com.example.aasok1.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aasok1.myapplication.R;
import com.example.aasok1.myapplication.database.DatabaseHandler;
import com.example.aasok1.myapplication.model.UserDetails;


public class RegisterNewUserActivity extends AppCompatActivity {

    DatabaseHandler mHelper = new DatabaseHandler(this);
    Button mButtonRegisterNew;
    EditText mName, mEmail, mUsername, mPassword, mCpassword, mMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_new);
        initiateViews();
        mButtonRegisterNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser(v);
            }
        });

    }

    public void initiateViews() {
        mName = (EditText) findViewById(R.id.editFullName);
        mEmail = (EditText) findViewById(R.id.editemail);
        mUsername = (EditText) findViewById(R.id.editUserName);
        mPassword = (EditText) findViewById(R.id.editPassword);
        mCpassword = (EditText) findViewById(R.id.editCPassword);
        mMobile = (EditText) findViewById(R.id.editMobile);
        mButtonRegisterNew = (Button) findViewById(R.id.buttonRegisterNew);

    }


    public void registerNewUser(View view) {
        String strname = mName.getText().toString();
        String stremail = mEmail.getText().toString();
        String strusername = mUsername.getText().toString();
        String strpassword = mPassword.getText().toString();
        String strcpassword = mCpassword.getText().toString();
        String strmobile = mMobile.getText().toString();

        if (strname.length() == 0) {
            mName.setError(getResources().getString(R.string.fullname_error));
            mName.requestFocus();

        } else if (stremail.length() == 0) {
            mEmail.setError(getResources().getString(R.string.email_error));
            mEmail.requestFocus();

        } else if (strusername.length() == 0) {
            mUsername.setError(getResources().getString(R.string.username_error));
            mUsername.requestFocus();

        } else if (!stremail.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
            mEmail.setError(getResources().getString(R.string.email_error));
            mEmail.requestFocus();
        } else if (strpassword.length() == 0) {
            mPassword.setError(getResources().getString(R.string.password_error));
            mPassword.requestFocus();
        } else if (strpassword.length() < 8) {
            mPassword.setError(getResources().getString(R.string.password_length_error));
            mPassword.requestFocus();
        } else if (strcpassword.length() == 0) {
            mCpassword.setError(getResources().getString(R.string.cpassword_error));
            mCpassword.requestFocus();
        } else if (!strpassword.equals(strcpassword)) {
            mCpassword.setError(getResources().getString(R.string.cpassword_mismatch));
            mCpassword.requestFocus();
        } else if (strmobile.length() == 0) {
            mMobile.setError(getResources().getString(R.string.mobile_error));
            mMobile.requestFocus();
        } else {

            UserDetails con = new UserDetails();
            con.setName(strname);
            con.setEmail(stremail);
            con.setUsername(strusername);
            con.setPassword(strpassword);
            con.setMobile(strmobile);
            mHelper.insertUserDetails(con);
            Intent i = new Intent(RegisterNewUserActivity.this, UserLoginActivity.class);
            startActivity(i);
        }


    }


}
