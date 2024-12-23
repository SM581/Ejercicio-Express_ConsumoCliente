package com.example.figuras_geometricas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TrinomioActivity extends AppCompatActivity {

    EditText edA, edB;
    RadioGroup rgTipo;
    RadioButton rbPositivo, rbNegativo;
    Button btnCalcularTrinomio;
    TextView tvResultadoTrinomio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinomio);

        edA = findViewById(R.id.edA);
        edB = findViewById(R.id.edB);
        rgTipo = findViewById(R.id.rgTipo);
        rbPositivo = findViewById(R.id.rbPositivo);
        rbNegativo = findViewById(R.id.rbNegativo);
        btnCalcularTrinomio = findViewById(R.id.btnCalcularTrinomio);
        tvResultadoTrinomio = findViewById(R.id.tvResultadoTrinomio);

        btnCalcularTrinomio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTrinomio();
            }
        });
    }

    private void calcularTrinomio() {
        String a = edA.getText().toString();
        String b = edB.getText().toString();
        String tipo = rbPositivo.isChecked() ? "positivo" : "negativo";

        if (!a.isEmpty() && !b.isEmpty()) {
            String url = "http://10.10.19.191:3001/trinomio/" + a + "/" + b + "/" + tipo;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> tvResultadoTrinomio.setText("Error al conectar con el servidor."));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String respuesta = response.body().string();
                        runOnUiThread(() -> tvResultadoTrinomio.setText(respuesta));
                    } else {
                        runOnUiThread(() -> tvResultadoTrinomio.setText("Error en la respuesta del servidor."));
                    }
                }
            });
        } else {
            tvResultadoTrinomio.setText("Por favor ingrese los valores de a y b.");
        }
    }
}
