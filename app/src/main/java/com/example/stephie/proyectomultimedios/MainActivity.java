package com.example.stephie.proyectomultimedios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stephie.proyectomultimedios.Presenter.MainPresenter;
import com.example.stephie.proyectomultimedios.Presenter.MainPresenterImpl;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView al_totales;
    private TextView al_utilizados;
    private TextView al_disponibles;
    private TextView ce_totales;
    private TextView ce_utilizados;
    private TextView ce_disponibles;
    private Button detalles_al;
    private Button detalles_ce;

    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        al_disponibles = (TextView) findViewById(R.id.al_disponibles);
        al_totales = (TextView)findViewById(R.id.al_total);
        al_utilizados = (TextView)findViewById(R.id.al_utilizados);
        ce_totales = (TextView)findViewById(R.id.ce_total);
        ce_utilizados = (TextView)findViewById(R.id.ce_utilizados);
        ce_disponibles = (TextView)findViewById(R.id.ce_disponibles);
        detalles_al = (Button)findViewById(R.id.detalles_al);
        detalles_ce = (Button)findViewById(R.id.detalles_ce);
        presenter = new MainPresenterImpl(getApplicationContext(), MainActivity.this,al_disponibles,al_utilizados, al_totales, ce_disponibles, ce_utilizados, ce_totales);

    }
}
