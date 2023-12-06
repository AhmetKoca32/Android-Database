package com.example.Ahmet_Koca_App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent(); // Intentten gelen bilgi al
        String id = intent.getStringExtra("id");
        String num = intent.getStringExtra("num");
        String ad = intent.getStringExtra("ad");

        TextView idTextView=findViewById(R.id.idtextview);
        TextView numtextview=findViewById(R.id.numtextview);
        TextView adtextview=findViewById(R.id.adtextview);


        idTextView.setText("id: " + id);
        numtextview.setText("numara: " + num);
        adtextview.setText("ad: " + ad);

    }



}