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

/**
 * Created by Kishore on 10/9/2015.
 */
public class Help extends Fragment {
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
    public static Help newInstance(String param1, String param2) {
        Help fragment = new Help();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Help() {
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
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        textView = (TextView) view.findViewById(R.id.help_txtview);
        stringContent = "<html><head></head><body style=\"text-align:justify;\"><b>What genre of music do you create?</b>" +
                "<br/><br/>We focus on creating music that imparts relaxation to the listener. Please check the Music Categories section above to browse all genres we've created so far.<br/>" +
                "<br/><br/><b>Where are your albums recorded?</b>" +
                "<br/><br/>In some of the best music studios in." +
                "<br/><br/><br/><b>Can I sell or distribute your albums?</b><br/>" +
                "<br/>Yes. Please contact us for more information. You can use the 'Contact Us' link above to reach us.<br/>" +
                "<br/><br/><b>I am interested in partnering with your company and promote your music. Who should I contact?</b>" +
                "<br/><br/>Please contact Mr. Srinivas Bhagi through the form provided in 'Contact Us' above or call on the numbers listed on the same page.<br/>" +
                "<br/><br/><b>What are the new albums and projects you have in the pipeline?</b>" +
                "<br/><br/>We will update details of new releases in our website and blog as and when we release new albums. Please check the 'New Releases' link above to see our latest releases.<br/><br/></body></html>";
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
