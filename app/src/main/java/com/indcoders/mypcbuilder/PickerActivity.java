package com.indcoders.mypcbuilder;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.indcoders.mypcbuilder.Model.Monitor;
import com.indcoders.mypcbuilder.Model.Motherboard;
import com.indcoders.mypcbuilder.Model.PSU;
import com.indcoders.mypcbuilder.Model.Processor;
import com.indcoders.mypcbuilder.Model.Ram;
import com.indcoders.mypcbuilder.Utils.DBHelper;
import com.indcoders.mypcbuilder.Utils.MonitorAdapter;
import com.indcoders.mypcbuilder.Utils.MotherboardAdapter;
import com.indcoders.mypcbuilder.Utils.PSUAdapter;
import com.indcoders.mypcbuilder.Utils.ProcessorAdapter;
import com.indcoders.mypcbuilder.Utils.RamAdapter;

import java.util.ArrayList;

public class PickerActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*Intent i = new Intent();
        i.putExtra("Result","Something");

        setResult(Activity.RESULT_OK,i);
        finish();*/

        prepareData(getIntent().getStringExtra("Tag"));

    }

    public void prepareData(String tag) {

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.close();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor c = null;

        switch (tag) {
            case "Processor":
                c = db.rawQuery("select * from processors", null);

                ArrayList<Processor> items = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Processor temp = new Processor();

                    temp.setBrand(c.getString(c.getColumnIndex("Brand")));
                    temp.setModel(c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setCore(c.getString(c.getColumnIndex("Core")));
                    temp.setSpeed(c.getString(c.getColumnIndex("Speed")));
                    temp.setSocket(c.getString(c.getColumnIndex("Socket")));

                    items.add(temp);

                    c.moveToNext();
                }
                c.close();

                ProcessorAdapter mAdapter = new ProcessorAdapter(getApplicationContext(), items);
                mAdapter.setOnSelectedListener(new ProcessorAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Processor processor) {
                        Intent i = new Intent();
                        i.putExtra("Result", processor.getModel());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(mAdapter);
                recyclerView.invalidate();

                break;

            case "Motherboard":
                c = db.rawQuery("select * from motherboards", null);

                ArrayList<Motherboard> motherboards = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Motherboard temp = new Motherboard();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setForm(c.getString(c.getColumnIndex("Form")));
                    temp.setChipset(c.getString(c.getColumnIndex("Chipset")));
                    temp.setSocket(c.getString(c.getColumnIndex("Socket")));

                    motherboards.add(temp);

                    c.moveToNext();
                }
                c.close();

                MotherboardAdapter motherboardAdapter = new MotherboardAdapter(getApplicationContext(), motherboards);
                motherboardAdapter.setOnSelectedListener(new MotherboardAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Motherboard motherboard) {
                        Intent i = new Intent();
                        i.putExtra("Result", motherboard.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(motherboardAdapter);
                recyclerView.invalidate();

                break;

            case "Ram":
                c = db.rawQuery("select * from rams", null);

                ArrayList<Ram> rams = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Ram temp = new Ram();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setMemory(c.getString(c.getColumnIndex("MemoryType")));

                    rams.add(temp);

                    c.moveToNext();
                }
                c.close();

                RamAdapter ramAdapter = new RamAdapter(getApplicationContext(), rams);
                ramAdapter.setOnSelectedListener(new RamAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Ram ram) {
                        Intent i = new Intent();
                        i.putExtra("Result", ram.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(ramAdapter);
                recyclerView.invalidate();

                break;

            case "HD":
                c = db.rawQuery("select * from hd", null);

                ArrayList<Ram> hds = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Ram temp = new Ram();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setMemory(c.getString(c.getColumnIndex("Capacity")));

                    hds.add(temp);

                    c.moveToNext();
                }
                c.close();

                RamAdapter hdAdapter = new RamAdapter(getApplicationContext(), hds);
                hdAdapter.setOnSelectedListener(new RamAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Ram ram) {
                        Intent i = new Intent();
                        i.putExtra("Result", ram.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(hdAdapter);
                recyclerView.invalidate();

                break;

            case "SSD":
                c = db.rawQuery("select * from ssd", null);

                ArrayList<Ram> ssds = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Ram temp = new Ram();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setMemory(c.getString(c.getColumnIndex("Capacity")));

                    ssds.add(temp);

                    c.moveToNext();
                }
                c.close();

                RamAdapter ssdAdapter = new RamAdapter(getApplicationContext(), ssds);
                ssdAdapter.setOnSelectedListener(new RamAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Ram ram) {
                        Intent i = new Intent();
                        i.putExtra("Result", ram.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(ssdAdapter);
                recyclerView.invalidate();

                break;

            case "GC":
                c = db.rawQuery("select * from graphics", null);

                ArrayList<Ram> gcs = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Ram temp = new Ram();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Chipset")) + " " + c.getString(c.getColumnIndex("GPU")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setMemory(c.getString(c.getColumnIndex("Memory")));

                    gcs.add(temp);

                    c.moveToNext();
                }
                c.close();

                RamAdapter gcAdapter = new RamAdapter(getApplicationContext(), gcs);
                gcAdapter.setOnSelectedListener(new RamAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Ram ram) {
                        Intent i = new Intent();
                        i.putExtra("Result", ram.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(gcAdapter);
                recyclerView.invalidate();

                break;

            case "MON":
                c = db.rawQuery("select * from monitors", null);

                ArrayList<Monitor> mons = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Monitor temp = new Monitor();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setSize(c.getString(c.getColumnIndex("Size")));
                    temp.setPanel(c.getString(c.getColumnIndex("Panel")));
                    temp.setResolution(c.getString(c.getColumnIndex("Resolution")));
                    temp.setResponse(c.getString(c.getColumnIndex("Response")));


                    mons.add(temp);

                    c.moveToNext();
                }
                c.close();

                MonitorAdapter monAdapter = new MonitorAdapter(getApplicationContext(), mons);
                monAdapter.setOnSelectedListener(new MonitorAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Monitor mon) {
                        Intent i = new Intent();
                        i.putExtra("Result", mon.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(monAdapter);
                recyclerView.invalidate();

                break;

            case "PSU":
                c = db.rawQuery("select * from psu", null);

                ArrayList<PSU> psus = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    PSU temp = new PSU();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setPower(c.getString(c.getColumnIndex("Power")));
                    temp.setEfficiency(c.getString(c.getColumnIndex("Efficiency")));


                    psus.add(temp);

                    c.moveToNext();
                }
                c.close();

                PSUAdapter psuAdapter = new PSUAdapter(getApplicationContext(), psus);
                psuAdapter.setOnSelectedListener(new PSUAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(PSU psu) {
                        Intent i = new Intent();
                        i.putExtra("Result", psu.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(psuAdapter);
                recyclerView.invalidate();

                break;

            case "OPT":
                c = db.rawQuery("select * from optical", null);

                ArrayList<Ram> opt = new ArrayList<>();

                c.moveToFirst();

                while (!c.isAfterLast()) {

                    Ram temp = new Ram();

                    temp.setName(c.getString(c.getColumnIndex("Brand")) + " " + c.getString(c.getColumnIndex("Model")));
                    temp.setPrice(c.getString(c.getColumnIndex("Price")));
                    temp.setMemory(c.getString(c.getColumnIndex("Type")));

                    opt.add(temp);

                    c.moveToNext();
                }
                c.close();

                RamAdapter optAdapter = new RamAdapter(getApplicationContext(), opt);
                optAdapter.setOnSelectedListener(new RamAdapter.OnSelectedListener() {
                    @Override
                    public void onSelected(Ram ram) {
                        Intent i = new Intent();
                        i.putExtra("Result", ram.getName());

                        setResult(Activity.RESULT_OK, i);
                        finish();
                    }
                });
                recyclerView.setAdapter(optAdapter);
                recyclerView.invalidate();

                break;
        }


    }

}
