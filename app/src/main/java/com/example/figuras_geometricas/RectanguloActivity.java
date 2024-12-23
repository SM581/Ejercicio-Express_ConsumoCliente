package com.example.figuras_geometricas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RectanguloActivity extends AppCompatActivity {

    Button btnCalcular, btnRegresar;
    EditText edBase, edAltura;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rectangulo);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnRegresar = findViewById(R.id.btnRegresar);
        edBase = findViewById(R.id.edBase);
        edAltura = findViewById(R.id.edAltura);
        tvResultado = findViewById(R.id.tvResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularAreaPerimetro();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void calcularAreaPerimetro() {
        String base = edBase.getText().toString();
        String altura = edAltura.getText().toString();

        if (!base.isEmpty() && !altura.isEmpty()) {
            String url = "http://192.168.101.9:3001/rectangulo/" + base + "&" + altura;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> tvResultado.setText("Error al conectar con el servidor."));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String respuesta = response.body().string();
                        runOnUiThread(() -> tvResultado.setText(respuesta));
                    } else {
                        runOnUiThread(() -> tvResultado.setText("Error en la respuesta del servidor."));
                    }
                }
            });
        } else {
            tvResultado.setText("Por favor ingrese valores para la base y altura.");
        }
    }
}
