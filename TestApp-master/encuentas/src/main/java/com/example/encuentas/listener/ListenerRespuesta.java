package com.example.encuentas.listener;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by cvazquez on 03/04/2018.
 */

public class ListenerRespuesta implements View.OnClickListener {

    private final TextView pa;
    private final TextView b;
    private final TextView c;

    public ListenerRespuesta(TextView pa, TextView b, TextView c) {
       this.pa=pa;
        this.b=b;
        this.c=c;
    }

    @Override
    public void onClick(View v) {
        b.setBackgroundColor(Color.WHITE);
        c.setBackgroundColor(Color.WHITE);
        pa.setBackgroundColor(Color.RED);
    }
}
