package com.example.Ahmet_Koca_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {


    SQLiteDatabase db;

    public List<Ogrenci> liste = new ArrayList<Ogrenci>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        File yol=getApplication().getFilesDir(); // uygulama yolu
        String myDbYol= yol+"/"+"MYO_db";
        db=SQLiteDatabase.openDatabase(myDbYol,null,
                SQLiteDatabase.CREATE_IF_NECESSARY);

        Liste();
        Secilen();

    }

    private void Secilen() {
        ListView lw=(ListView) findViewById(R.id.lstogrenciler);
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Ogrenci ogr = liste.get(position);
                Intent myintent= new Intent(getBaseContext(), Main3Activity.class);
                myintent.putExtra("id", ogr.getId().toString());
                myintent.putExtra("num", ogr.getNum().toString()); // numarayı gönder
                myintent.putExtra("ad", ogr.getAd().toString());
                startActivity(myintent);
            }
        });
    }

    private void Liste() {
        try {
            Cursor c = db.rawQuery("select * from ogr", null);
            c.moveToPosition(-1);
            while(c.moveToNext()) {
                liste.add(new Ogrenci(c.getString(0), c.getString(1), c.getString(2)));
            }
            MyAdapter adapter= new MyAdapter();
            ListView lw=(ListView) findViewById(R.id.lstogrenciler);
            lw.setAdapter(adapter);
        } catch (SQLiteException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public class MyAdapter extends ArrayAdapter<Ogrenci> {

        public MyAdapter() {
            super(Main2Activity.this, R.layout.listegoster, liste);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myview = convertView;
            if (myview == null) {
                myview = getLayoutInflater().inflate(R.layout.listegoster, parent, false);
            }


            Ogrenci ogr = liste.get(position);

            TextView id = (TextView) myview.findViewById(R.id.textView);
            TextView num = (TextView) myview.findViewById(R.id.textView2);
            TextView ad = (TextView) myview.findViewById(R.id.textView3);

            id.setText(ogr.getId());
            num.setText(ogr.getNum());
            ad.setText(ogr.getAd());

            return myview;
        }

    }


}



