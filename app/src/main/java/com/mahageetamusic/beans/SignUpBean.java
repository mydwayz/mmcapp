package com.mahageetamusic.beans;

import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pkante002 on 12/5/2015.
 */
public class SignUpBean {

    private String firstname, lastname, emailid, password, confirmpassword, address, city, state, country, pincode, phone;
    private TextInputLayout firstnametil, lastnametil, emailidtil, passwordtil, confirmpasswordtil, addresstil, citytil, statetil, countrytil, pincodetil, phonetil;

    // String values code starts here
    public SignUpBean(EditText firstname, EditText lastname, EditText emailid, EditText password,
                      EditText confirmpassword, EditText address, EditText city, EditText state,
                      EditText country, EditText pincode, EditText phone) {
        this.firstname = firstname.getText().toString().trim();
        this.lastname = lastname.getText().toString().trim();
        this.emailid = emailid.getText().toString().trim();
        this.password = password.getText().toString().trim();
        this.confirmpassword = confirmpassword.getText().toString().trim();
        this.address = address.getText().toString().trim();
        this.city = city.getText().toString().trim();
        this.state = state.getText().toString().trim();
        this.country = country.getText().toString().trim();
        this.pincode = pincode.getText().toString().trim();
        this.phone = phone.getText().toString().trim();
    }

    @Override
    public String toString() {
        return "SignUpBean{" +
                "phone='" + phone + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }

    public JSONObject getGsonObject()  {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("firstname", firstname);
            jsonObject.put("lastname", lastname);
            jsonObject.put("emailid", emailid);
            jsonObject.put("password", password);
            jsonObject.put("address", address);
            jsonObject.put("city", city);
            jsonObject.put("state", state);
            jsonObject.put("country", country);
            jsonObject.put("zipcode", pincode);
            jsonObject.put("number", phone);
        } catch (JSONException e) {
            Log.v(e.getMessage(), "");
        }
        return jsonObject;
    }

    // Handle validation for sign up form
    public boolean validateFields() {
        boolean validationStatus = true;
        // Reset error messages
        firstnametil.setErrorEnabled(false);
        lastnametil.setErrorEnabled(false);
        emailidtil.setErrorEnabled(false);
        passwordtil.setErrorEnabled(false);
        confirmpasswordtil.setErrorEnabled(false);
        addresstil.setErrorEnabled(false);
        citytil.setErrorEnabled(false);
        statetil.setErrorEnabled(false);
        countrytil.setErrorEnabled(false);
        pincodetil.setErrorEnabled(false);
        phonetil.setErrorEnabled(false);

        if(firstname.length() == 0) {
            firstnametil.setErrorEnabled(true);
            firstnametil.setError("Enter valid First Name");
            validationStatus = false;
        }
        if(lastname.length() == 0) {
            lastnametil.setErrorEnabled(true);
            lastnametil.setError("Enter valid Last Name");
            validationStatus = false;
        }
        if(emailid.length() == 0) {
            emailidtil.setErrorEnabled(true);
            emailidtil.setError("Enter valid Email ID");
            validationStatus = false;
        }
        if(password.length() == 0) {
            passwordtil.setErrorEnabled(true);
            passwordtil.setError("Enter valid Password");
            validationStatus = false;
        }
        if(confirmpassword.length() == 0) {
            confirmpasswordtil.setErrorEnabled(true);
            confirmpasswordtil.setError("Enter valid Confirm Password");
            validationStatus = false;
        }
        if(password.length() > 0 && confirmpassword.length() > 0 &&
                !password.equals(confirmpassword)) {
            confirmpasswordtil.setErrorEnabled(true);
            confirmpasswordtil.setError("Password and Confirm Password must be same");
            validationStatus = false;
        }
        if(address.length() == 0) {
            addresstil.setErrorEnabled(true);
            addresstil.setError("Enter valid Address");
            validationStatus = false;
        }
        if(city.length() == 0) {
            citytil.setErrorEnabled(true);
            citytil.setError("Enter valid City");
            validationStatus = false;
        }
        if(state.length() == 0) {
            statetil.setErrorEnabled(true);
            statetil.setError("Enter valid State");
            validationStatus = false;
        }
        if(country.length() == 0) {
            countrytil.setErrorEnabled(true);
            countrytil.setError("Enter valid Country");
            validationStatus = false;
        }
        if(pincode.length() == 0) {
            pincodetil.setErrorEnabled(true);
            pincodetil.setError("Enter valid Pin Code");
            validationStatus = false;
        }
        if(phone.length() == 0) {
            phonetil.setErrorEnabled(true);
            phonetil.setError("Enter valid Phone");
            validationStatus = false;
        }
        return validationStatus;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailid() {
        return emailid;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPincode() {
        return pincode;
    }

    public String getPhone() {
        return phone;
    }
    // TextInputLayout code starts here

    public void setFirstnametil(TextInputLayout firstnametil) {
        this.firstnametil = firstnametil;
    }

    public void setLastnametil(TextInputLayout lastnametil) {
        this.lastnametil = lastnametil;
    }

    public void setEmailidtil(TextInputLayout emailidtil) {
        this.emailidtil = emailidtil;
    }

    public void setPasswordtil(TextInputLayout passwordtil) {
        this.passwordtil = passwordtil;
    }

    public void setConfirmpasswordtil(TextInputLayout confirmpasswordtil) {
        this.confirmpasswordtil = confirmpasswordtil;
    }

    public void setAddresstil(TextInputLayout addresstil) {
        this.addresstil = addresstil;
    }

    public void setCitytil(TextInputLayout citytil) {
        this.citytil = citytil;
    }

    public void setStatetil(TextInputLayout statetil) {
        this.statetil = statetil;
    }

    public void setCountrytil(TextInputLayout countrytil) {
        this.countrytil = countrytil;
    }

    public void setPincodetil(TextInputLayout pincodetil) {
        this.pincodetil = pincodetil;
    }

    public void setPhonetil(TextInputLayout phonetil) {
        this.phonetil = phonetil;
    }

}
