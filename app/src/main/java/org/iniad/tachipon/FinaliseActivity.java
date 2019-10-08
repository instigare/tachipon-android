package org.iniad.tachipon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinaliseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalise);

        Bundle bundle = getIntent().getExtras();

        String situation = bundle.getString("situation");
        String details = bundle.getString("details");

        TextView textSituationFinal = findViewById(R.id.textSituationFinal);
        TextView textDetailsFinal = findViewById(R.id.textDetailsFinal);

        textSituationFinal.setText(situation);
        textDetailsFinal.setText(details);

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
