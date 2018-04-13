package com.example.encuentas.task;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.encuentas.entity.ClientePromo;
import com.example.encuentas.listener.DialogEncuesta;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cvazquez on 02/04/2018.
 */

public class HttpRequestTask extends AsyncTask<Void, Void, ClientePromo> {
    Activity baseContext;
    Integer operation;
    ClientePromo clientePromo;
    public HttpRequestTask(Activity baseContext, Integer operation, ClientePromo clientePromo) {
        this.baseContext=baseContext;
        this.operation=operation;
        this.clientePromo=clientePromo;
    }

    @Override
    protected ClientePromo doInBackground(Void... params) {
        String url = "http://10.0.2.2:8080/api/encuesta/";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        try {
            Log.d("selec operation",operation+"");
            if(operation==1){

                url+="cliente/238909";

                ClientePromo greeting = restTemplate.getForObject(url, ClientePromo.class);
                return greeting;
            }
            if(operation==2){
                url+="cliente/";
               ResponseEntity<ClientePromo> greeting = restTemplate.postForEntity(url,clientePromo,ClientePromo.class);
                return greeting.getBody();
            }


        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(ClientePromo result) {
        Log.d("grett content",result!=null?result.getId():"sinID");
        if(operation==1) {
            if (result != null) {
                if (result.getStatus().equals("1")) {
                    //está en el programa muestra encuesta
                    new HttpRequestTaskEncuesta(baseContext,3,result).execute();
                }
                if (result.getStatus().equals("0")) {
                    //No está en el programa y ve hacia home
                }
                if (result.getStatus().equals("")) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(baseContext);
                    DialogInterface.OnClickListener dialogClickListener = new DialogEncuesta(result, baseContext);
                    builder.setMessage("Forme parte del Programa de Encuesta y reciba grandes beneficios como cliente preferencial, " +
                            "¿Desea formar parte del Programa?").setPositiveButton("Si", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
            }
        }
        if(operation==2){
            Log.d("save option","resultsave");
        }

    }

}
