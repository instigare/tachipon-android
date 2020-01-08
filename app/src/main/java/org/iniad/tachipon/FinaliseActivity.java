package org.iniad.tachipon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FinaliseActivity extends AppCompatActivity {

    private String imageUrl = "https://i.imgur.com/eOSai4B.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalise);

        String postSituation = null;
        final Bundle bundle = getIntent().getExtras();

        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageBitmap((Bitmap) bundle.getParcelable("image"));

        String situation = bundle.getString("situation");
        final String details = bundle.getString("details");

        switch (situation) {
            case "incident":
                setTitle(getResources().getString(R.string.incident) + ": " + getResources().getString(R.string.check));
                postSituation = "事件";
                break;
            case "accident":
                setTitle(getResources().getString(R.string.accident) + ": " + getResources().getString(R.string.check));
                postSituation = "事故";
                break;
            case "ambulance":
                setTitle(getResources().getString(R.string.ambulance) + ": " + getResources().getString(R.string.check));
                postSituation = "救急車";
                break;
        }

        TextView textDetailsFinal = findViewById(R.id.textDetailsFinal);
        textDetailsFinal.setText(details);

        Button btnConfirm = findViewById(R.id.btnConfirm);
        final String finalPostSituation = postSituation;
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue queue = Volley.newRequestQueue(FinaliseActivity.this);

                final String base64String = ImageUtil.convert((Bitmap) bundle.getParcelable("image"));
                final String url = "https://api.imgbb.com/1/upload?key=72eafac47ba0e3d731bba0dc6e7b281b";

                if (bundle.getParcelable("image") != null) {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject object = new JSONObject(response);
                                imageUrl = object.getJSONObject("data").getString("url");
                                Log.d("Network", "The URL is " + imageUrl + ".");

                                String reportUrl = null;

                                try {
                                    reportUrl = ("https://tachipon.herokuapp.com/report?name=" + "Alvi Kazi" + "&phone=" + "123456789" + "&lat=" + "35.7804211" + "&lng=" + "139.7157993" + "&situation=" + URLEncoder.encode(finalPostSituation, "UTF-8") + "&image=" + URLEncoder.encode(imageUrl, "UTF-8") + "&details=" + URLEncoder.encode(details, "UTF-8"));
                                    Log.d("Network", "The URL is " + reportUrl + ".");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }

                                StringRequest stringRequest2 = new StringRequest(Request.Method.GET, reportUrl, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject object = new JSONObject(response);
                                            final String diditWork = object.getString("reportcode");
                                            Log.d("Network", "The report code is " + diditWork + ".");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d("Network","Something went wrong.");
                                    }
                                });

                                queue.add(stringRequest2);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Network","Something went wrong.");
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String>  params = new HashMap<String, String>();
                            params.put("image", base64String);

                            return params;
                        }
                    };

                    queue.add(stringRequest);
                }



                Intent i = new Intent(FinaliseActivity.this, ThanksActivity.class);
                i.putExtras(bundle);
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
