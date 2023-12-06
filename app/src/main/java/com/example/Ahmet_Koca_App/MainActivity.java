package com.example.Ahmet_Koca_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

    SQLiteDatabase db;
    MediaPlayer mpbutton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseOlustur();
        TabloOlustur();



    }


    public void ListelemeSayfasi(View w) {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }



    private void DatabaseOlustur() {
        File yol=getApplication().getFilesDir(); // uygulama yolu
        String myDbYol= yol+"/"+"MYO_db";
        try {
            db=SQLiteDatabase.openDatabase(myDbYol,null,
                    SQLiteDatabase.CREATE_IF_NECESSARY);
            Toast.makeText(this, "Database oluşturuldu", Toast.LENGTH_LONG).show();
        } catch (SQLiteException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void TabloOlustur() {
        try {
            db.execSQL("create table if not exists ogr(id integer primary key autoincrement, num text, ad text);");
            Toast.makeText(this, "Tablo oluşturuldu", Toast.LENGTH_LONG).show();
        } catch (SQLiteException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void Ekle(View w) {
        EditText id=(EditText) findViewById(R.id.idEditText);
        EditText num=(EditText) findViewById(R.id.numEditText);
        EditText ad=(EditText) findViewById(R.id.adEditText);
        try {
            ContentValues kayit= new ContentValues();
            kayit.put("num", num.getText().toString());
            kayit.put("ad", ad.getText().toString());
            db.insert("ogr",null, kayit);
            Toast.makeText(this, "Kayıt Yapıldı", Toast.LENGTH_LONG).show();
        } catch (SQLiteException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void Degistir(View w) {
        EditText id=(EditText) findViewById(R.id.idEditText);
        EditText num=(EditText) findViewById(R.id.numEditText);
        EditText ad=(EditText) findViewById(R.id.adEditText);
        try {
            String[] kisit= new String [1];// bir parametre olacak
            kisit[0]= id.getText().toString(); // parametre id EditText’inden alınacak
            ContentValues kayit= new ContentValues();
            kayit.put("num", num.getText().toString());
            kayit.put("ad", ad.getText().toString());
            int i = db.update("ogr", kayit, "id=?", kisit );
            if(i>0)
                Toast.makeText(this, "Kayıt Değiştirildi", Toast.LENGTH_LONG).show();
        } catch (SQLiteException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Sil(View w) {
        EditText num=(EditText) findViewById(R.id.numEditText);
        try {
            String[] param= new String[1];
            param[0]= num.getText().toString();
            db.execSQL("delete from ogr where num=?", param);
            Toast.makeText(this, "Silindi", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



}