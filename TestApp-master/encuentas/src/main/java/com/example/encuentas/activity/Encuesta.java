package com.example.encuentas.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.encuentas.task.HttpRequestTask;
import com.example.encuentas.R;


public class Encuesta extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encuesta);
        new HttpRequestTask(this,1,null).execute();

    }
}
