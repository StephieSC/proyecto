package com.example.stephie.proyectomultimedios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.stephie.proyectomultimedios.Presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.recyclerview.R.attr.layoutManager;
import static com.example.stephie.proyectomultimedios.R.id.recyclerView;

public class MainBotonActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Detalle> items = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_boton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //item deberia ser detalle_al que es una lista de detalle
        adapter = new MyAdapter(items);
        recyclerView.setAdapter(adapter);
    }
}

