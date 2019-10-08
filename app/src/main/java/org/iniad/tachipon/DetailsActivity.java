package org.iniad.tachipon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Bundle bundle = getIntent().getExtras();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String situation = bundle.getString("situation");

        TextView textSituation = findViewById(R.id.textSituation);
        textSituation.setText(situation);

        final EditText editDetails = findViewById(R.id.editDetails);

        Button btnAttachImage = findViewById(R.id.btnAttachImage);
        Button btnSubmitDetails = findViewById(R.id.btnSubmitDetails);

        btnAttachImage.setOnClickListener(this);
        btnSubmitDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("details", editDetails.getText().toString());
                Intent i = new Intent(DetailsActivity.this, FinaliseActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

}
