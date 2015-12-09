package com.mahageetamusic.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mahageetamusic.R;
import com.mahageetamusic.interfaces.OnFragmentInteractionListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestServices.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestServices#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestServices extends Fragment {
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

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestServices.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestServices newInstance(String param1, String param2) {
        RequestServices fragment = new RequestServices();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RequestServices() {
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
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        textView = (TextView) view.findViewById(R.id.service_txtview);
        button = (Button) view.findViewById(R.id.request_service);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestServiceDialog requestServiceDialog = new RequestServiceDialog();
                requestServiceDialog.show(getFragmentManager(),"Hello");
            }
        });
        stringContent = "<html><head></head><body style=\"text-align:justify;\"><p>Replication/Duplication Services  Bulk Audio CD/Video CD/DVD replication and duplication services.</p><br/><p>" +
                "Refurbishing/Audio Mastering Services  Refurbishing of your Audio Masters. Audio editing of Master CDs such as removal of noises, unwanted sounds,hissing noises etc.</p><br/><p>" +
                "CD/DVD Sticker Pasting, Printing and Packaging Services  Any number of CD sticker printing, pasting and packaging services.</p><br/><p>" +
                "Receive a quotation for any of our services by filling the adjacent form.</p></body></html>";
        textView.setText(Html.fromHtml(stringContent));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
