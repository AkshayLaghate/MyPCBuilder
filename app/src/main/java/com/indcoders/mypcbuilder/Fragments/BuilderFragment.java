package com.indcoders.mypcbuilder.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.indcoders.mypcbuilder.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuilderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuilderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuilderFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView tvProcessorName;
    private RelativeLayout rlProcessor;
    private ImageView ivStatusPro;

    private OnFragmentInteractionListener mListener;

    private int TAG_MB = 1, TAG_PRO = 2, TAG_GC = 3, TAG_RAM = 4, TAG_HD = 5, TAG_SSD = 6, TAG_MON = 7, TAG_PSU = 8, TAG_OPT = 9;

    public BuilderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuilderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuilderFragment newInstance(String param1, String param2) {
        BuilderFragment fragment = new BuilderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View v = inflater.inflate(R.layout.fragment_builder, container, false);

        tvProcessorName = (TextView) v.findViewById(R.id.tvProcessorName);

        rlProcessor = (RelativeLayout) v.findViewById(R.id.rlProcessor);
        rlProcessor.setOnClickListener(this);

        ivStatusPro = (ImageView) v.findViewById(R.id.ivStatusProcessor);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlProcessor:
                Intent i = new Intent(getContext(), com.indcoders.mypcbuilder.PickerActivity.class);
                i.putExtra("Tag", "Processor");
                startActivityForResult(i, TAG_PRO);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == TAG_PRO) {
                Toast.makeText(getContext(), data.getStringExtra("Result"), Toast.LENGTH_SHORT).show();
                tvProcessorName.setText(data.getStringExtra("Result"));
                ivStatusPro.setImageResource(R.drawable.success);
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
