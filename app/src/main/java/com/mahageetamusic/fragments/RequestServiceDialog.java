package com.mahageetamusic.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mahageetamusic.R;
import com.mahageetamusic.beans.RequestServiceDialBean;
import com.mahageetamusic.beans.ResponseObject;
import com.mahageetamusic.network.RequestAsyncTask;
import com.mahageetamusic.network.ResponseListener;
import com.mahageetamusic.utils.Utils;

/**
 * Created by Kishore on 9/19/2015.
 */
public class RequestServiceDialog extends DialogFragment {

    TextView sr_service_textView,sr_time_textView;
    Spinner sr_service,sr_time;
    String service_req,service_time;
    RequestServiceDialBean sdBean;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_service_dialog, container, false);
        sr_service_textView = (TextView) view.findViewById(R.id.request_service_type_textView);
        sr_service = (Spinner) view.findViewById(R.id.request_service_spinner);
        sr_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    sr_service_textView.setVisibility(View.INVISIBLE);
                } else {
                    sr_service_textView.setVisibility(View.VISIBLE);
                    service_req = ((TextView) view).getText().toString();
                }
                //Toast.makeText(getActivity(),""+position,Toast.LENGTH_SHORT).show();
                Log.i("Service Selected: ", getResources().getStringArray(R.array.service_request_types)[position] + "  " + ((TextView) view).getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        sr_time_textView = (TextView) view.findViewById(R.id.request_service_time_textView);
        sr_time = (Spinner)view.findViewById(R.id.request_service_time_spinner);
        sr_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    sr_time_textView.setVisibility(View.INVISIBLE);
                } else {
                    sr_time_textView.setVisibility(View.VISIBLE);
                    service_time = ((TextView) view).getText().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button button = (Button) view.findViewById(R.id.service_request_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Submited service form", "Click found");
                prepareServiceDialogBean(view);
                if(sdBean.validateFields())
                    new RequestAsyncTask(v.getContext(), new ResponseListener() {
                        @Override
                        public void onResponseListener(int requestFor, Object mResponse) {
                            Log.v("SignUp Response",""+mResponse);
                            if(requestFor == Utils.REQUEST_SERVICE_REQUEST){
                                if(mResponse instanceof ResponseObject){
                                    ResponseObject responseObject = (ResponseObject)mResponse;
                                    if(responseObject.getmResponseStatus().equals("Success")){
                                        Log.v("Success response --> ", responseObject.getmResponse().toString());
                                        //dismissProgressDialog();
                                        /*showAlertDialog("Success", false, responseObject.getmResponse().toString(), "Sign In", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                startActivity(new Intent(SignUp.this, LoginScreen.class));
                                            }
                                        }, "", null);*/
                                        //startActivity(new Intent(v.this, HomeActivity.class));
                                    } else if(responseObject.getmResponseStatus().equals("Failure")){
                                        Log.v("Failure response --> ", responseObject.getmResponse().toString());
                                        //dismissProgressDialog();
                                        //showAlertDialog("Failure", true, responseObject.getmResponse().toString(), "OK", null, "", null);
                                    }
                                }
                            }
                        }
                    }, Utils.REQUEST_SERVICE_REQUEST).execute(sdBean.getGsonObject());
                Log.v("Dialogue data --> ", sdBean.toString());
            }
        });
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //dialog.setTitle("Service Request Form");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }

    private void prepareServiceDialogBean(View view) {
        try {
            sdBean = new RequestServiceDialBean((EditText) view.findViewById(R.id.request_service_fname), (EditText) view.findViewById(R.id.request_service_subject),
                    (TextView) view.findViewById(R.id.request_service_type_textView),(TextView) view.findViewById(R.id.request_service_time_textView), (EditText) view.findViewById(R.id.request_service_cd),
                    (EditText) view.findViewById(R.id.request_service_contact), (EditText) view.findViewById(R.id.request_service_email),
                    (RadioButton) view.findViewById(R.id.radio1), (EditText) view.findViewById(R.id.request_service_message));
        } catch (Exception e) {
            Log.v("Exception --> ", e.getMessage());
        }
    }
}
