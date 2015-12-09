package com.mahageetamusic.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mahageetamusic.R;

/**
 * Created by Kishore on 11/3/2015.
 */
public class ForgotPasswordFragment extends Fragment {

    private TextInputLayout mTextInputLayoutOTP,mTextInputLayoutPassword,mTextInputLayoutConfirmPassword;
    private EditText mEditTextOTP,mEditTextPassword,mEditTextConfirmPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forgot_password,null);
        intializeUIElements(view);
        setListeners(view);
        return view;
    }

    private void setListeners(View view) {
        mEditTextOTP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextInputLayoutOTP.setErrorEnabled(false);
            }
        });
        mEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextInputLayoutPassword.setErrorEnabled(false);
            }
        });
        mEditTextConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextInputLayoutConfirmPassword.setErrorEnabled(false);
            }
        });
        ((Button)view.findViewById(R.id.button_save_password)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doValidationsOnFields()) {

                }
            }
        });
    }

    private boolean doValidationsOnFields() {
        String otp = mEditTextOTP.getText().toString();
        String password = mEditTextPassword.getText().toString();
        String confirmPassword = mEditTextConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(otp) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            if (TextUtils.isEmpty(otp)) {
                mTextInputLayoutOTP.setErrorEnabled(true);
                mTextInputLayoutOTP.setError("Invalid OTP");
            } else if (TextUtils.isEmpty(password)) {
                mTextInputLayoutPassword.setErrorEnabled(true);
                mTextInputLayoutPassword.setError("Enter Password");
            } else if (TextUtils.isEmpty(confirmPassword)) {
                mTextInputLayoutConfirmPassword.setErrorEnabled(true);
                mTextInputLayoutConfirmPassword.setError("Enter Confirm Password");
            }
            return false;
        } else if (otp.length() < 6 || password.length() < 6) {
            if (otp.length() < 6) {
                mTextInputLayoutOTP.setErrorEnabled(true);
                mTextInputLayoutOTP.setError("Invalid OTP");
            } else if (password.length() < 6) {
                mTextInputLayoutPassword.setErrorEnabled(true);
                mTextInputLayoutPassword.setError("Password should be minimum of 6 characters");
            }
            return false;
        } else if (password.equals(confirmPassword)) {
            mTextInputLayoutConfirmPassword.setErrorEnabled(true);
            mTextInputLayoutConfirmPassword.setError("Password and Confirm Password mis-match.");
            return false;
        }
        return true;
    }

    private void intializeUIElements(View view) {
        mEditTextOTP = (EditText)view.findViewById(R.id.edittext_otp);
        mEditTextPassword = (EditText)view.findViewById(R.id.edittext_password);
        mEditTextConfirmPassword = (EditText)view.findViewById(R.id.edittext_confirm_password);
        mTextInputLayoutOTP = (TextInputLayout)view.findViewById(R.id.textinputlayout_otp);
        mTextInputLayoutConfirmPassword = (TextInputLayout)view.findViewById(R.id.textinputlayout_confirm_password);
        mTextInputLayoutPassword = (TextInputLayout)view.findViewById(R.id.textinputlayout_password);
    }
}
