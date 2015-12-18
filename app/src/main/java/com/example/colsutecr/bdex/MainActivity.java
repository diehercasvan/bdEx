package com.example.colsutecr.bdex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
    }

    public void formUnoOnClick(View v) {
        Toast.makeText(this, "Has hecho click", Toast.LENGTH_SHORT).show();
        enviarDatos("William","Forero","7329434");
    }

    public void enviarDatos(String nombre, String apellido, String telefono) {
        AsyncHttpClient cliente = new AsyncHttpClient();
        String URL = "http://192.168.42.6/insert_user.php?";
        String parametros = "nombre=" + nombre + "&apellido=" + apellido + "&telefono=" + telefono;
        cliente.post(URL + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String respuesta = new String(responseBody);
                    Toast.makeText(MainActivity.this,"Respuesta: "+respuesta,Toast.LENGTH_LONG).show();
                    tvInfo.setText(respuesta);

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                tvInfo.setText(responseBody.toString());
            }
        });

    }
}
