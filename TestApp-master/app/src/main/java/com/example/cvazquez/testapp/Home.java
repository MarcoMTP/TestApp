package com.example.cvazquez.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.encuentas.fragment.EncuestaFragment;
import com.example.encuentas.activity.Encuesta;
import com.example.encuentas.listener.InteractionListener;

public class Home extends AppCompatActivity implements View.OnClickListener, InteractionListener {
    Integer vista=0;
    ImageButton home;
    private Fragment OurFragment;
    @Override
    public void onClick(View v) {
        ImageButton imagen= (ImageButton) findViewById(R.id.imageHome);
        switch (vista) {
            case 0:
                   imagen.setBackgroundResource(R.mipmap.s2);
                    vista=1;

                break;
            case 1:
                    imagen.setBackgroundResource(R.mipmap.s5);
                    vista=2;
                break;
            case 2:
                imagen.setBackgroundResource(R.mipmap.s6);
                vista=3;
                break;
            case 3:
                imagen.setBackgroundResource(R.mipmap.s7);
                vista=4;
                break;
            case 4:
                imagen.setBackgroundResource(R.mipmap.s8);
                vista=5;
                break;
            case 5:
                imagen.setBackgroundResource(R.mipmap.s10);
                vista=6;
                break;
            case 6:
                Intent k = new Intent(Home.this, Encuesta.class);
                startActivity(k);
               // replaceFragment();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        home =(ImageButton)findViewById(R.id.imageHome);
        home.setOnClickListener(this);
    }

    public void replaceFragment() {
        OurFragment = new EncuestaFragment().newInstance();

        try {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, OurFragment, "tag").commit();

        } catch (Exception e) {
            ;
        }

    }
    @Override
    public void onFragmentInteraction(String string) {
        Log.d("listener","listener");
    }
}
