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
import com.mahageetamusic.beans.SignUpBean;
import com.mahageetamusic.network.RequestAsyncTask;
import com.mahageetamusic.utils.Utils;

public class SignUp extends MahageetaMusicBaseActivity {
    SignUpBean signUpBean;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void signUpClick(View view) {
        prepareSignUpBean(); // Set signUpBean
        showProgressDialog("Please wait...", "Sign Up in progress");
        if(signUpBean.validateFields()) {
            Log.v("Login Success", signUpBean.toString());
            new RequestAsyncTask(this, this, Utils.REQUEST_SIGNUP).execute(signUpBean.getGsonObject());
        } else
            Log.v("Validation Failed", signUpBean.toString());
        dismissProgressDialog();
    }

    @Override
    public void onResponseListener(int requestFor, Object mResponse) {
        Log.v("SignUp Response", "" + mResponse);
        if(requestFor == Utils.REQUEST_SIGNUP){
            if(mResponse instanceof ResponseObject){
                ResponseObject responseObject = (ResponseObject)mResponse;
                if(responseObject.getmResponseStatus().equals("Success")){
                    Log.v("Success response --> ", responseObject.getmResponse().toString());
                    dismissProgressDialog();
                    showAlertDialog("Success", false, responseObject.getmResponse().toString(), "Sign In", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startActivity(new Intent(SignUp.this, LoginScreen.class));
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

    private void prepareSignUpBean() {
        // Injecting Edit text
        signUpBean = new SignUpBean((EditText)findViewById(R.id.firstname), (EditText)findViewById(R.id.lastname),
                (EditText)findViewById(R.id.emailid), (EditText)findViewById(R.id.password),
                (EditText)findViewById(R.id.confirmpassword), (EditText)findViewById(R.id.address),
                (EditText)findViewById(R.id.city), (EditText)findViewById(R.id.state),
                (EditText)findViewById(R.id.country), (EditText)findViewById(R.id.pincode),
                (EditText)findViewById(R.id.phone));
        // Injecting Text input layout
        signUpBean.setFirstnametil((TextInputLayout)findViewById(R.id.firstnametil));
        signUpBean.setLastnametil((TextInputLayout)findViewById(R.id.lastnametil));
        signUpBean.setEmailidtil((TextInputLayout)findViewById(R.id.emailidtil));
        signUpBean.setPasswordtil((TextInputLayout)findViewById(R.id.passwordtil));
        signUpBean.setConfirmpasswordtil((TextInputLayout)findViewById(R.id.confirmpasswordtil));
        signUpBean.setAddresstil((TextInputLayout)findViewById(R.id.addresstil));
        signUpBean.setCitytil((TextInputLayout)findViewById(R.id.citytil));
        signUpBean.setStatetil((TextInputLayout)findViewById(R.id.statetil));
        signUpBean.setCountrytil((TextInputLayout)findViewById(R.id.countrytil));
        signUpBean.setPincodetil((TextInputLayout)findViewById(R.id.pincodetil));
        signUpBean.setPhonetil((TextInputLayout)findViewById(R.id.phonetil));
    }
}
