package com.mahageetamusic.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mahageetamusic.R;


public class ContactUs extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;
    private Button button;
    private String stringContent;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUs.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUs newInstance(String param1, String param2) {
        ContactUs fragment = new ContactUs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ContactUs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        textView = (TextView) view.findViewById(R.id.contactus_txtview);
        button = (Button) view.findViewById(R.id.feedbackBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackDialog requestServiceDialog = new FeedbackDialog();
                requestServiceDialog.show(getFragmentManager(),"Hello");
            }
        });
        stringContent = "<html><head></head><body style=\"text-align:justify;\"> You can contact us through one of the following ways: <br/>Address:<br/> <p>Mahageeta Music Company<br/>  5-4-670/B, (Neo Mysore Cafe Lane),<br/>\'Sri Durga Nilayam\' Adjacent to BJP Office<br/> Nampally Station Road,<br/> Hyderabad-500001,  Telangana, India</p><br/> sales@mahageetamusic.com  <br/> 91-40-64601453| 950  291 3333 | 924 627 2735 <br/><br/><br/> <br/>(You can also leave a message on this phone)</body></html>";
        textView.setText(Html.fromHtml(stringContent));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
