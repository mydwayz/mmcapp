package com.mahageetamusic.beans;

import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mahageetamusic.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pkante002 on 12/6/2015.
 */
public class RequestServiceDialBean {
    private String fullName, subject, serviceType,serviceTime, serviceCDNo, serviceContact, serviceEmail, serviceUrgent, serviceMsg;
    private TextInputLayout fullNameTIL, subjectTIL, serviceTypeTIL,serviceTimeTIL, serviceCDNoTIL, serviceContactTIL, serviceEmailTIL, serviceMsgTIL;
    private RadioGroup serviceUrgentRG;

    public RequestServiceDialBean(EditText fullName, EditText subject, TextView serviceType,TextView serviceTime,
                                  EditText serviceCDNo, EditText serviceContact, EditText serviceEmail,
                                  RadioButton radioYes, EditText serviceMsg) {
        this.fullName = fullName.getText().toString().trim();
        this.subject = subject.getText().toString().trim();
        this.serviceType = serviceType.getText().toString().trim();
        this.serviceTime = serviceTime.getText().toString().trim();
        this.serviceCDNo = serviceCDNo.getText().toString().trim();
        this.serviceContact = serviceContact.getText().toString().trim();
        this.serviceEmail = serviceEmail.getText().toString().trim();
        if(radioYes.isChecked())
            this.serviceUrgent = "Yes";
        else
            this.serviceUrgent = "No";
        this.serviceMsg = serviceMsg.getText().toString().trim();
    }
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String mobilePattern = "[0-9]{10}";
    // Handle validation for sign up form
    public boolean validateFields() {
        boolean validationStatus = true;
        if(fullName.length() == 0) {
            fullNameTIL.setErrorEnabled(true);
            fullNameTIL.setError("Enter valid Full Name");
            validationStatus = false;
        }
        if(subject.length() == 0) {
            subjectTIL.setErrorEnabled(true);
            subjectTIL.setError("Enter valid Subject");
            validationStatus = false;
        }
        if(serviceCDNo.length() == 0) {
            serviceCDNoTIL.setErrorEnabled(true);
            serviceCDNoTIL.setError("Enter valid No.of CD\\'s");
            validationStatus = false;
        }
        if(serviceContact.length() == 0) {
            serviceContactTIL.setErrorEnabled(true);
            serviceContactTIL.setError("Enter valid Contact Number");
            validationStatus = false;
        } else if(!serviceContact.matches(mobilePattern)){
            serviceContactTIL.setErrorEnabled(true);
            serviceContactTIL.setError("Invalid Contact Number");
            validationStatus = false;
        }
        if(serviceEmail.length() == 0) {
            serviceEmailTIL.setErrorEnabled(true);
            serviceEmailTIL.setError("Enter valid Email ID");
            validationStatus = false;
        } else if(!serviceEmail.matches(emailPattern)){
            serviceEmailTIL.setErrorEnabled(true);
            serviceEmailTIL.setError("Invalid Email ID");
            validationStatus = false;
        }
        if(serviceMsg.length() == 0) {
            serviceContactTIL.setErrorEnabled(true);
            serviceContactTIL.setError("Enter valid Message");
            validationStatus = false;
        }
        return validationStatus;
    }

    public JSONObject getGsonObject()  {
        JSONObject jsonObject = new JSONObject();
        Utils.CART_LIST cl;
        try {
            jsonObject.put("name", fullName);
            jsonObject.put("subject", subject);
            jsonObject.put("service_required", serviceType);
            jsonObject.put("service_time",serviceTime);
            jsonObject.put("cds", serviceCDNo);
            jsonObject.put("mobile_number", serviceContact);
            jsonObject.put("email", serviceEmail);
            jsonObject.put("urgent", serviceUrgent);
            jsonObject.put("message", serviceMsg);
        } catch (JSONException e) {
            Log.v(e.getMessage(), "");
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return "RequestServiceDialBean{" +
                "serviceUrgent=" + serviceUrgent +
                ", fullName='" + fullName + '\'' +
                ", subject='" + subject + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", serviceCDNo='" + serviceCDNo + '\'' +
                ", serviceContact='" + serviceContact + '\'' +
                ", serviceEmail='" + serviceEmail + '\'' +
                ", serviceMsg='" + serviceMsg + '\'' +
                '}';
    }
}
