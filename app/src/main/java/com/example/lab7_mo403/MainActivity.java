package com.example.lab7_mo403;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button btnDelete, btnUpdate;
    TextView tvKQ;
    EditText edName, edPrice, edDescription, edID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initByViewID();

        clickListener();
    }

    private void clickListener() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePOST();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePOST();
            }
        });

    }

    private void initByViewID() {
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        tvKQ = findViewById(R.id.tvKQ);
        edName = findViewById(R.id.ed_name);
        edID = findViewById(R.id.ed_id);
        edPrice = findViewById(R.id.ed_price);
        edDescription = findViewById(R.id.ed_description);
    }

    public void updatePOST() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://tucaomypham.000webhostapp.com/android_networking_mob403/update_product.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvKQ.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData = new HashMap<>();
                myData.put("id", edID.getText().toString());
                myData.put("name", edName.getText().toString());
                myData.put("price", edPrice.getText().toString());
                myData.put("description", edDescription.getText().toString());
                return myData;
            }
        };

        queue.add(stringRequest);
    }

    public void deletePOST() {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "https://tucaomypham.000webhostapp.com/android_networking_mob403/delete_product_by_id.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvKQ.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> myData = new HashMap<>();
                myData.put("id", edID.getText().toString());
                return myData;
            }
        };

        queue.add(stringRequest);
    }
}