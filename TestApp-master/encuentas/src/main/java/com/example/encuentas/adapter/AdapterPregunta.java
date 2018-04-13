package com.example.encuentas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.encuentas.entity.EncuestaList;
import com.example.encuentas.listener.ListenerRespuesta;
import com.example.encuentas.R;

import java.util.ArrayList;

/**
 * Created by cvazquez on 03/04/2018.
 */

public class AdapterPregunta extends BaseAdapter {

    protected Context activity;
    protected ArrayList<EncuestaList> items;

    public AdapterPregunta (Context activity, ArrayList<EncuestaList> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<EncuestaList> category) {
        for (int i =0 ; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_pregunta, null);
        }

        EncuestaList dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.pregunta);
        title.setText(position+1+".-"+dir.getPregunta());

        TextView a = (TextView) v.findViewById(R.id.a);
        a.setText(dir.getOpciones()[0]);

        TextView b = (TextView) v.findViewById(R.id.b);
        b.setText(dir.getOpciones()[1]);
        TextView c = (TextView) v.findViewById(R.id.c);
        c.setText(dir.getOpciones()[2]);

        a.setOnClickListener(new ListenerRespuesta(a,b,c));
        b.setOnClickListener(new ListenerRespuesta(b,a,c));
        c.setOnClickListener(new ListenerRespuesta(c,a,b));

        return v;
    }
}
