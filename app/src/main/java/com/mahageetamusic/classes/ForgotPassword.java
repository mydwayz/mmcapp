package com.mahageetamusic.classes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mahageetamusic.R;
import com.mahageetamusic.beans.ResponseObject;
import com.mahageetamusic.network.RequestAsyncTask;
import com.mahageetamusic.utils.Utils;

public class ForgotPassword extends MahageetaMusicBaseActivity {
    EditText request_pwd;
    TextInputLayout request_pwdtil;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        request_pwd = (EditText) findViewById(R.id.request_pwd);
        request_pwdtil = (TextInputLayout) findViewById(R.id.request_pwd_floatingtextfield);
    }

    public void PwdRequest(View view) {
        if (view.getId() == R.id.send_request) {
            showProgressDialog("Please wait...", "Processing...");
            String request_data = request_pwd.getText().toString().trim();
            if(validateForgotPassword()) {
                Log.v("Validation success", request_data);
                new RequestAsyncTask(this, this, Utils.REQUEST_FORGOTPASSWORD).execute(request_data);
            } else
                Log.v("Validation Failed", request_data);
            dismissProgressDialog();
        }
    }

    @Override
    public void onResponseListener(int requestFor, Object mResponse) {
        Log.v("ForgotPassword Response", "" + mResponse);
        if(requestFor == Utils.REQUEST_FORGOTPASSWORD){
            if(mResponse instanceof ResponseObject){
                ResponseObject responseObject = (ResponseObject)mResponse;
                if(responseObject.getmResponseStatus().equals("Success")){
                    Log.v("Success response --> ", responseObject.getmResponse().toString());
                    dismissProgressDialog();
                    showAlertDialog("Success", false, responseObject.getmResponse().toString(), "Sign In", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(new Intent(ForgotPassword.this, LoginScreen.class));
                        }
                    }, "", null);
                } else if(responseObject.getmResponseStatus().equals("Failure")){
                    Log.v("Failure response --> ", responseObject.getmResponse().toString());
                    dismissProgressDialog();
                    showAlertDialog("Failure", true, responseObject.getmResponse().toString(), "OK", null, "", null);
                }
            }
        }
    }

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mobilePattern = "[0-9]{10}";
    private boolean validateForgotPassword() {
        boolean validationStatus = true;
        String request_data = request_pwd.getText().toString().trim();
        request_pwdtil.setErrorEnabled(false);
        if(request_data.length() == 0) {
            request_pwdtil.setErrorEnabled(true);
            request_pwdtil.setError("Enter valid Email ID/Mobile");
            validationStatus = false;
        } else if(!request_data.matches(emailPattern) && !request_data.matches(mobilePattern)){
            request_pwdtil.setErrorEnabled(true);
            request_pwdtil.setError("Invalid Email ID/Mobile");
            validationStatus = false;
        }
        return validationStatus;
    }}
