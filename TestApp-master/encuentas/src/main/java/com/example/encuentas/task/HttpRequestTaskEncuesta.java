package com.example.encuentas.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.encuentas.R;
import com.example.encuentas.adapter.AdapterPregunta;
import com.example.encuentas.entity.ClientePromo;
import com.example.encuentas.entity.EncuestaList;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by cvazquez on 02/04/2018.
 */

public class HttpRequestTaskEncuesta extends AsyncTask<Void, Void,EncuestaList[] > {
    Activity baseContext;
    Integer operation;
    ClientePromo clientePromo;

    public HttpRequestTaskEncuesta(Activity baseContext, Integer operation, ClientePromo clientePromo) {
        this.baseContext=baseContext;
        this.operation=operation;
        this.clientePromo=clientePromo;

    }

    @Override
    protected  EncuestaList[] doInBackground(Void... params) {
        String url = "http://10.0.2.2:8080/api/encuesta/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {

            if(operation==3){
                url+="/"+clientePromo.getTipoEncuesta();
                ResponseEntity<EncuestaList[]> result = restTemplate.getForEntity(url,EncuestaList[].class);
                return result.getBody();
            }

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(EncuestaList[] result) {
        Log.i("pinta encuesta","result get encuest");
        AdapterPregunta adapter = new AdapterPregunta(baseContext, new ArrayList<EncuestaList>(Arrays.asList(result)));
        ListView lv=(ListView) baseContext.findViewById(R.id.recipe_list_view);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                //CODIGO AQUI

            }
        });

    }

}
