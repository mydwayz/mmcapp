package com.mahageetamusic.classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mahageetamusic.R;
import com.mahageetamusic.beans.ResponseObject;
import com.mahageetamusic.network.RequestAsyncTask;
import com.mahageetamusic.utils.UserSessionManager;
import com.mahageetamusic.utils.Utils;

public class LoginScreen extends MahageetaMusicBaseActivity {

    EditText username, password;
    TextInputLayout usernametil,passwordtil;
    UserSessionManager userSessionManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        usernametil = (TextInputLayout) findViewById(R.id.usernametil);
        passwordtil = (TextInputLayout) findViewById(R.id.passwordtil);
    }

    public void loginClick(View view) {
        if (view.getId() == R.id.loginbtn) {
            switch (validateFields()) {
                case 1:
                    usernametil.setErrorEnabled(true);
                    passwordtil.setErrorEnabled(true);
                    usernametil.setError(getResources().getString(R.string.error_login_enter_username));
                    passwordtil.setError(getResources().getString(R.string.error_login_enter_password));
                    break;
                case 2:
                    usernametil.setErrorEnabled(true);
                    passwordtil.setErrorEnabled(false);
                    usernametil.setError(getResources().getString(R.string.error_login_enter_username));
                    passwordtil.setError("");
                    break;
                case 3:
                    usernametil.setErrorEnabled(false);
                    passwordtil.setErrorEnabled(true);
                    passwordtil.setError(getResources().getString(R.string.error_login_enter_password));
                    usernametil.setError("");
                    break;
                case 4:
                    usernametil.setErrorEnabled(false);
                    passwordtil.setErrorEnabled(false);
                    showProgressDialog("Logging In","Please wait...");
                    new RequestAsyncTask(LoginScreen.this,this, Utils.REQUEST_LOGIN).execute(username.getText().toString().trim(),password.getText().toString().trim());
                    break;
                default:
                    break;
            }
        } else if (view.getId() == R.id.forgotpwd) {
            startActivity(new Intent(LoginScreen.this, ForgotPassword.class));
        } else if (view.getId() == R.id.login_signup) {
            startActivity(new Intent(LoginScreen.this,SignUp.class));
        }
    }

    private int validateFields() {
        if (username.getText().toString().trim().length() == 0 && password.getText().toString().trim().length() == 0) {
            return 1;
        } else if (username.getText().toString().trim().length() == 0) {
            return 2;
        } else if (password.getText().toString().trim().length() == 0) {
            return 3;
        } else if (username.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() > 0) {
            return 4;
        }
        return 0;
    }


    @Override
    public void onResponseListener(int requestFor, Object mResponse) {
        Log.v("Login Response",""+mResponse);
        if(requestFor==Utils.REQUEST_LOGIN){
            if(mResponse instanceof ResponseObject){
                ResponseObject responseObject = (ResponseObject)mResponse;
                dismissProgressDialog();
                if(responseObject.getmResponseStatus().equals("Success")){
                    startActivity(new Intent(LoginScreen.this,HomeActivity.class));
                } else if(responseObject.getmResponseStatus().equals("Failure")){
                    passwordtil.setErrorEnabled(true);
                    passwordtil.setError((String)responseObject.getmResponse());
                }
            }
            else{

            }
        }
    }
}
