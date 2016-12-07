package com.example.stephie.proyectomultimedios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.stephie.proyectomultimedios.Models.Detalle;
import com.example.stephie.proyectomultimedios.Presenter.MainPresenterImpl;

import java.util.List;

public class MainBotonLunchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Detalle> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_boton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        items= MainPresenterImpl.obtenerDatos().getDetalleAL();
        adapter = new MyAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}

