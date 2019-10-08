package org.iniad.tachipon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FinaliseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalise);

        Bundle bundle = getIntent().getExtras();

        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageBitmap((Bitmap) bundle.getParcelable("image"));

        String situation = bundle.getString("situation");
        String details = bundle.getString("details");

        TextView textSituationFinal = findViewById(R.id.textSituationFinal);
        TextView textDetailsFinal = findViewById(R.id.textDetailsFinal);

        textSituationFinal.setText(situation);
        textDetailsFinal.setText(details);

        Button btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinaliseActivity.this, ThanksActivity.class);
                startActivity(i);
            }
        });

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
