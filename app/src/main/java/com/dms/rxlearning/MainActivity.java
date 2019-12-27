package com.dms.rxlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button rxbutton;
    private Button disposableButton;
    private Button compositeDisposable;
    private Button operadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
    }

    public void setupView(){
        rxbutton = findViewById(R.id.rxbutton);
        rxbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Rx00IntroActivity.class));
            }
        });

        disposableButton = findViewById(R.id.disposablebutton);
        disposableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Rx01DisposableActivity.class));
            }
        });

        compositeDisposable = findViewById(R.id.compositeDisposable);
        compositeDisposable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Rx02CompositeDisActivity.class));
            }
        });

        operadores = findViewById(R.id.operadores);
        operadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Rx03OperadoresActivity.class));
            }
        });
    }
}
