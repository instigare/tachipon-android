package org.iniad.tachipon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String situation = bundle.getString("situation");

        TextView textSituation = findViewById(R.id.textSituation);

        textSituation.setText(situation);
    }


}
