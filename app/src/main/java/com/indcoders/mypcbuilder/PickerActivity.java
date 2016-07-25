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

import com.indcoders.mypcbuilder.Model.Motherboard;
import com.indcoders.mypcbuilder.Model.Processor;
import com.indcoders.mypcbuilder.Model.Ram;
import com.indcoders.mypcbuilder.Utils.DBHelper;
import com.indcoders.mypcbuilder.Utils.MotherboardAdapter;
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
        }


    }

}
