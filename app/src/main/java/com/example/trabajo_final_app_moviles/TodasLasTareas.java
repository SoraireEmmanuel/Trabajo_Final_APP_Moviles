package com.example.trabajo_final_app_moviles;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

public class TodasLasTareas extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todas_las_tareas);


        /*logica del TAB*/
        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Espera",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Revisando",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab5");
        spec.setContent(R.id.tab5);
        spec.setIndicator("Terminada",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
    }
}
