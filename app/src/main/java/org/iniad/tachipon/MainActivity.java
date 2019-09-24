package org.iniad.tachipon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIncident = findViewById(R.id.btnIncident);
        Button btnAccident = findViewById(R.id.btnAccident);
        Button btnAmbulance = findViewById(R.id.btnAmbulance);

        btnIncident.setOnClickListener(this);
        btnAccident.setOnClickListener(this);
        btnAmbulance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIncident:
                bundle.putString("situation", "incident");
                break;
            case R.id.btnAccident:
                bundle.putString("situation", "accident");
                break;
            case R.id.btnAmbulance:
                bundle.putString("situation", "ambulance");
                break;
        }

        Intent i = new Intent(this, DetailsActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}