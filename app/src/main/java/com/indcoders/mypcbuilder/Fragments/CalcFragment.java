package com.indcoders.mypcbuilder.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.indcoders.mypcbuilder.R;
import com.indcoders.mypcbuilder.Utils.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalcFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalcFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalcFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] gc_nos = {"1","2"};
    double processorVal = 0,mbVal = 0,gcVal = 0,ramVal = 0,opticalVal=0,hddVal=0,total=0;
    final int INTEL_CODE = 1,AMD_CODE = 2,INTEGRATED=6,NVIDIA=7,ATI=8;
    int PRCOESSOR_CODE,GC_CODE;
    int gcMultiplier,ramMultiplier,opticalMultiplier,hddMultiplier;

    private OnFragmentInteractionListener mListener;

    public CalcFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalcFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalcFragment newInstance(String param1, String param2) {
        CalcFragment fragment = new CalcFragment();
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
        View v = inflater.inflate(R.layout.fragment_calc, container, false);

        final Spinner mbSpinner = (Spinner) v.findViewById(R.id.spinner_mb);
        final Spinner gcSpinner = (Spinner) v.findViewById(R.id.spinner_graphics);
        final Spinner gcNosSpinner = (Spinner) v.findViewById(R.id.spinner_graphics_nos);
        final Spinner ramSpinner = (Spinner) v.findViewById(R.id.spinner_ram);
        final Spinner ramNosSpinner = (Spinner) v.findViewById(R.id.spinner_ram_nos);
        final Spinner opticalSpinner = (Spinner) v.findViewById(R.id.spinner_optical);
        final Spinner opticalNosSpinner = (Spinner) v.findViewById(R.id.spinner_optical_nos);
        final Spinner hddSpinner = (Spinner) v.findViewById(R.id.spinner_hdd);
        final Spinner hddNosSpinner = (Spinner) v.findViewById(R.id.spinner_hdd_nos);

        RadioGroup rg = (RadioGroup) v.findViewById(R.id.rbGroupProcessor);
        RadioGroup rgGraphics = (RadioGroup) v.findViewById(R.id.rbGroupGraphics);
        RadioGroup rgMB = (RadioGroup) v.findViewById(R.id.rbGroupMB);

        DBHelper dbHelper = new DBHelper(getActivity());
        dbHelper.close();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from psu_intel", null);

        final String[] intel = new String[c.getCount()];
        final String[] intel_vals = new String[c.getCount()];
        c.moveToFirst();

        while (!c.isAfterLast()) {
            intel[c.getPosition()] = c.getString(1);
            intel_vals[c.getPosition()] = c.getString(2);
            c.moveToNext();
        }
        c.close();

        Cursor c1 = db.rawQuery("select * from psu_amd", null);

        final String[] amd = new String[c1.getCount()];
        final String[] amd_vals = new String[c1.getCount()];

        c1.moveToFirst();

        while (!c1.isAfterLast()) {
            amd[c1.getPosition()] = c1.getString(1);
            amd_vals[c1.getPosition()] = c1.getString(2);
            c1.moveToNext();
        }
        c1.close();

        Cursor c2 = db.rawQuery("select * from psu_nvidia", null);

        final String[] nvidia = new String[c2.getCount()];
        final String[] nvidia_vals = new String[c2.getCount()];
        c2.moveToFirst();

        while (!c2.isAfterLast()) {
            nvidia[c2.getPosition()] = c2.getString(1);
            nvidia_vals[c2.getPosition()] = c2.getString(2);
            c2.moveToNext();
        }
        c2.close();

        Cursor c3 = db.rawQuery("select * from psu_ati", null);

        final String[] ati = new String[c3.getCount()];
        final String[] ati_vals = new String[c3.getCount()];

        c3.moveToFirst();

        while (!c3.isAfterLast()) {
            ati[c3.getPosition()] = c3.getString(1);
            ati_vals[c3.getPosition()] = c3.getString(2);
            c3.moveToNext();
        }
        c3.close();

        Cursor c4 = db.rawQuery("select * from psu_ram", null);

        final String[] ram = new String[c4.getCount()];
        final String[] ram_vals = new String[c4.getCount()];
        c4.moveToFirst();

        while (!c4.isAfterLast()) {
            ram[c4.getPosition()] = c4.getString(1);
            ram_vals[c4.getPosition()] = c4.getString(2);
            c4.moveToNext();
        }
        c4.close();

        Cursor c5 = db.rawQuery("select * from psu_optical", null);

        final String[] optical = new String[c5.getCount()];
        final String[] optical_vals = new String[c5.getCount()];
        c5.moveToFirst();

        while (!c5.isAfterLast()) {
            optical[c5.getPosition()] = c5.getString(1);
            optical_vals[c5.getPosition()] = c5.getString(2);
            c5.moveToNext();
        }
        c5.close();

        Cursor c6 = db.rawQuery("select * from psu_hdd", null);

        final String[] hdd = new String[c6.getCount()];
        final String[] hdd_vals = new String[c6.getCount()];
        c6.moveToFirst();

        while (!c6.isAfterLast()) {
            hdd[c6.getPosition()] = c6.getString(1);
            hdd_vals[c6.getPosition()] = c6.getString(2);
            c6.moveToNext();
        }
        c6.close();

        ArrayAdapter<String> ram_adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item,ram);
        ramSpinner.setAdapter(ram_adapter);

        String[] ram_nos = {"1","2","3","4","5","6"};
        ramNosSpinner.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_item,ram_nos));

        ArrayAdapter<String> optical_adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item,optical);
        opticalSpinner.setAdapter(optical_adapter);

        String[] optical_nos = {"1","2","3","4"};
        opticalNosSpinner.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_item,optical_nos));

        ArrayAdapter<String> hdd_adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item,hdd);
        hddSpinner.setAdapter(hdd_adapter);

        String[] hdd_nos = {"1","2","3","4","5","6","7","8"};
        hddNosSpinner.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.spinner_item,hdd_nos));



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbIntel:
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item,intel);
                        mbSpinner.setAdapter(adapter);
                        PRCOESSOR_CODE = INTEL_CODE;
                        break;
                    case R.id.rbAMD:
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), R.layout.spinner_item,amd);
                        mbSpinner.setAdapter(adapter1);
                        PRCOESSOR_CODE = AMD_CODE;
                        break;
                }
            }
        });

        rgGraphics.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbIntegrated:
                        gcSpinner.setAdapter(null);
                        gcSpinner.setEnabled(false);
                        gcNosSpinner.setAdapter(null);
                        gcNosSpinner.setEnabled(false);

                        GC_CODE = INTEGRATED;
                        break;
                    case R.id.rbNvidia:

                        gcSpinner.setEnabled(true);
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item,nvidia);
                        gcSpinner.setAdapter(adapter);
                        gcNosSpinner.setEnabled(true);
                        gcNosSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.spinner_item,gc_nos));

                        GC_CODE = NVIDIA;

                        break;
                    case R.id.rbAti:
                        gcSpinner.setEnabled(true);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), R.layout.spinner_item,ati);
                        gcSpinner.setAdapter(adapter1);
                        gcNosSpinner.setEnabled(true);
                        gcNosSpinner.setAdapter(new ArrayAdapter<>(getContext(),R.layout.spinner_item,gc_nos));

                        GC_CODE = ATI;
                        break;
                }
            }
        });

        rgMB.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbDesktop:
                        mbVal = 250;
                        break;
                    case R.id.rbServer:
                        mbVal = 350;
                        break;
                    case R.id.rbMini:
                        mbVal = 150;
                        break;
                }
            }
        });

        mbSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(PRCOESSOR_CODE == INTEL_CODE) {
                    Toast.makeText(getContext(), intel_vals[i], Toast.LENGTH_SHORT).show();
                    processorVal = Double.parseDouble(intel_vals[i]);
                }
                else {
                    Toast.makeText(getContext(),amd_vals[i],Toast.LENGTH_SHORT).show();
                    processorVal = Double.parseDouble(amd_vals[i]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(GC_CODE == INTEGRATED){
                    gcVal = 20;
                }else if(GC_CODE == NVIDIA){
                    gcVal = Double.parseDouble(nvidia_vals[i]);
                }else
                    gcVal = Double.parseDouble(ati_vals[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gcNosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        gcMultiplier = 1;
                        break;
                    case 1:
                        gcMultiplier = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ramSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ramVal = Double.parseDouble(ram_vals[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ramNosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        ramMultiplier = 1;
                        break;
                    case 1:
                        ramMultiplier = 2;
                        break;
                    case 2:
                        ramMultiplier = 3;
                        break;
                    case 3:
                        ramMultiplier = 4;
                        break;
                    case 4:
                        ramMultiplier = 5;
                        break;
                    case 5:
                        ramMultiplier = 6;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        opticalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                opticalVal = Double.parseDouble(optical_vals[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        opticalNosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        opticalMultiplier = 1;
                        break;
                    case 1:
                        opticalMultiplier = 2;
                        break;
                    case 2:
                        opticalMultiplier = 3;
                        break;
                    case 3:
                        opticalMultiplier = 4;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hddSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hddVal = Double.parseDouble(hdd_vals[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hddNosSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        hddMultiplier = 1;
                        break;
                    case 1:
                        hddMultiplier = 2;
                        break;
                    case 2:
                        hddMultiplier = 3;
                        break;
                    case 3:
                        hddMultiplier = 4;
                        break;
                    case 4:
                        hddMultiplier = 5;
                        break;
                    case 5:
                        hddMultiplier = 6;
                        break;
                    case 6:
                        hddMultiplier = 7;
                        break;
                    case 7:
                        hddMultiplier = 8;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button bCalculate = (Button) v.findViewById(R.id.bCalculate);
        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginCalculate();
            }
        });

        return v;
    }

    private void beginCalculate() {
        total = processorVal+mbVal+(gcVal*gcMultiplier)+(ramVal*ramMultiplier)+(opticalVal*opticalMultiplier)+(hddVal*hddMultiplier);
       Toast.makeText(getContext(),"Total = "+total,Toast.LENGTH_SHORT).show();
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
