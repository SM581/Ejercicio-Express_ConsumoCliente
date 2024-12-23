package com.example.figuras_geometricas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTriangulo, btnRectangulo, btnCuadrado, btnTrinomio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTriangulo = findViewById(R.id.btnTriangulo);
        btnRectangulo = findViewById(R.id.btnRectangulo);
        btnCuadrado = findViewById(R.id.btnCuadrado);
        btnTrinomio = findViewById(R.id.btnTrinomio);

        btnTriangulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrianguloActivity.class);
                startActivity(intent);
            }
        });

        btnRectangulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RectanguloActivity.class);
                startActivity(intent);
            }
        });

        btnCuadrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CuadradoActivity.class);
                startActivity(intent);
            }
        });
        btnTrinomio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrinomioActivity.class);
                startActivity(intent);
            }
        });
    }
}