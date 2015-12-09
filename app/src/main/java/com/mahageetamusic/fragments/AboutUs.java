package com.mahageetamusic.fragments;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahageetamusic.R;
import com.mahageetamusic.interfaces.OnFragmentInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutUs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutUs extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textView;
    private String stringContent;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutUs.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUs newInstance(String param1, String param2) {
        AboutUs fragment = new AboutUs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AboutUs() {
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
        View view = inflater.inflate(R.layout.layout_fragment_about_us, container, false);
        PackageInfo pInfo = null;
        try {
            pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int version = pInfo.versionCode;
        ((TextView)view.findViewById(R.id.app_version)).setText(new StringBuilder().append("Version ").append(version));
        textView = (TextView) view.findViewById(R.id.contentpanel);
        stringContent = "<html><head></head><body style=\"text-align:justify;\"><h3>Mahageeta Music Company</h3><p>Read about the beginning and progress of Mahageeta Music Company.Mahageeta Music has been in the industry of music and entertainment from the past 18 years and has now earned a respectable reputation for itself. Navigate to the drop down above under 'About us' to visit various sections that include Mahageeta Music Company's vision and policies, details of dealers, testimonials from our customers and the prestigous projects Mahageeta has been involved in. Also read about Srinivas Bhagi, the Founder, Producer-Director of Mahageeta Music Company and his interests in astrology!</p><br/><br/><h3>About Director</h3><p>Bhagi Srinivas Murthy, popularly known as Srinivas Bhagi, is the Founder, Producer and Director of Mahageeta Music Company.An Electronics and Management Graduate, Srinivas Bhagi began this company with the sole vision of providing high quality melodious music for relaxation. Blog : Srinivasbhagi</p></body></html>";
        textView.setText(Html.fromHtml(stringContent));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction(mParam1,);
        }
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String fragment,Object... objects);
    }*/

}
