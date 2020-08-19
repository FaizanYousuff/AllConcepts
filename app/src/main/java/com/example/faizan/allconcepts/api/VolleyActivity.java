package com.example.faizan.allconcepts.api;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faizan.allconcepts.BaseActivity;
import com.example.faizan.allconcepts.R;

public class VolleyActivity extends BaseActivity {

    private static final String TAG = VolleyActivity.class.getName();
    private Button btnRequest;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String url = "http://www.mocky.io/v2/5ca1e86c370000290089951f" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Intent intent = getIntent();
        Uri data = intent.getData();
        String amount = data.getQueryParameter("amount");
        String name = data.getQueryParameter("name");


        Toast.makeText(this, amount + name, Toast.LENGTH_SHORT).show();

        btnRequest = (Button) findViewById(R.id.buttonRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAndRequestResponse();

            }
        });


     }

    private void sendAndRequestResponse() {
       requestQueue = Volley.newRequestQueue(this);
        showDialog(this);

       stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               hideDialog();
               Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               hideDialog();
               Toast.makeText(VolleyActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
               Log.i(TAG,"Error :" + error.toString());
           }
       });
        // To handle Android Volley Timeout you need to use RetryPolicy

        // RETRY POLICY
        // Volley provides an easy way to implement your RetryPolicy for your requests.
        // Volley sets default Socket & ConnectionTImeout to 5 secs for all requests.
        // RetryPolicy is an interface where you need to implement your logic of how you want to retry a particular request when a timeout happens.

        // It deals with these three parameters

        // Timeout - Specifies Socket Timeout in millis per every retry attempt.
        // Number Of Retries - Number of times retry is attempted.
        // Back Off Multiplier - A multiplier which is used to determine exponential time set to socket for every retry attempt.
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
       requestQueue.add(stringRequest);
    }
}
