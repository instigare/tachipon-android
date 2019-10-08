package org.iniad.tachipon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bundle = getIntent().getExtras();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String situation = bundle.getString("situation");
        
        imageView = findViewById(R.id.imageView);


        TextView textSituation = findViewById(R.id.textSituation);
        textSituation.setText(situation);

        final EditText editDetails = findViewById(R.id.editDetails);

        Button btnAttachImage = findViewById(R.id.btnAttachImage);
        Button btnSubmitDetails = findViewById(R.id.btnSubmitDetails);

        btnAttachImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
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

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bundle.putParcelable("image", (Bitmap) extras.get("data"));
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }


}
