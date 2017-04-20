package com.example.aasok1.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aasok1.myapplication.R;
import com.example.aasok1.myapplication.database.DatabaseHandler;

public class UserLoginActivity extends AppCompatActivity {

    Button mButtonLogin;
    TextView mNewAccount, mSignup;
    EditText mUsernameEdit, mPasswordEdit;
    CheckBox mCbShowPwd;
    DatabaseHandler mHelper = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);
        initiateViews();
        onClickListeners();
    }

    public void initiateViews() {
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPwd);
        mButtonLogin = (Button) findViewById(R.id.login_button);
        mUsernameEdit = (EditText) findViewById(R.id.enterusername);
        mPasswordEdit = (EditText) findViewById(R.id.enterpassword);
        mNewAccount = (TextView) findViewById(R.id.new_usertext);
        mSignup = (TextView) findViewById(R.id.newuser_signup);
    }

    public void onClickListeners() {
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister(v);
            }
        });
        mUsernameEdit.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 0) {
                    hint = true;
                } else if (hint) {
                    hint = false;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    hint = true;
                } else if (hint) {
                    hint = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPasswordEdit.addTextChangedListener(new TextWatcher() {
            boolean hint;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    hint = true;
                } else if (hint) {
                    hint = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin(v);
            }
        });

        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (!isChecked) {
                    mPasswordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mPasswordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }

    public void userLogin(View view) {
        EditText text = (EditText) findViewById(R.id.enterusername);
        String str = text.getText().toString();
        EditText pwd = (EditText) findViewById(R.id.enterpassword);
        String strpwd = pwd.getText().toString();
        String password = mHelper.searchPassword(str);

        if (str.length() == 0) {
            text.setError(getResources().getString(R.string.username_error));
            text.requestFocus();
        }
        if (pwd.length() == 0) {
            pwd.setError(getResources().getString(R.string.password_error));
            pwd.requestFocus();
        }

        if ((str.length() != 0) && (strpwd.length() != 0)) {
            if ((strpwd.equals(password))) {
                Intent intent = new Intent(UserLoginActivity.this, CategoryDisplayActivity.class);
                intent.putExtra("Username", str);
                startActivity(intent);

            } else {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(getResources().getString(R.string.user_credentials_dialog))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.ok_button), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mPasswordEdit.setText("");
                                mUsernameEdit.setText("");
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }


    }

    public void userRegister(View v) {
        Intent intent = new Intent(UserLoginActivity.this, RegisterNewUserActivity.class);
        startActivity(intent);
    }

}
