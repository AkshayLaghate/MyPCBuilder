package com.indcoders.mypcbuilder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.indcoders.mypcbuilder.Model.Processor;
import com.indcoders.mypcbuilder.Utils.DBHelper;
import com.indcoders.mypcbuilder.Utils.ProcessorAdapter;

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

                    items.add(temp);

                    c.moveToNext();
                }
                c.close();

                ProcessorAdapter mAdapter = new ProcessorAdapter(getApplicationContext(), items);
                recyclerView.setAdapter(mAdapter);
                recyclerView.invalidate();

                break;
        }


    }

}
