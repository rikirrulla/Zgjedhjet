package com.example.zgjedhjet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends Activity {

    TextView tvKrijoLlogari;
    EditText etEmaili, etFjalkalimi;
    Button btnHyni;

    public static ArrayList<Votuesi> votuesiArrayList = new ArrayList<>();


    static String id;
    static String emri;
    static String mbiemri;
    static String emaili;
    static String fjalkalimiS;
    static String adresa;
    static String vendi;
    static String komuna;
    static String numri_leternjoftimit;
    static String data_lindjes;
    static String data_regjistrimit;

    Votuesi votuesi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tvKrijoLlogari = findViewById(R.id.idKrijoLlogariHyni);
        etEmaili = findViewById(R.id.idEmailiHyni);
        etFjalkalimi = findViewById(R.id.idFjalkalimiHyni);
        btnHyni = findViewById(R.id.idBtnHyni);


        btnHyni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valido();
            }
        });


        tvKrijoLlogari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void valido() {

        final String email = etEmaili.getText().toString().trim();
        final String fjalkalimi = etFjalkalimi.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etEmaili.setError("Please enter your username");
            etEmaili.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(fjalkalimi)) {
            etFjalkalimi.setError("Please enter your username");
            etFjalkalimi.requestFocus();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Ju lutem prisni....");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.contains("po")) {
                                votuesiArrayList.clear();
                                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                editor.putString(Config.EMAIL_SHARED_PREF, email);

                                editor.commit();


                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("votuesi");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    id = object.getString("id");
                                    emri = object.getString("emri");
                                    mbiemri = object.getString("mbiemri");
                                    emaili = object.getString("emaili");
                                    fjalkalimiS = object.getString("fjalkalimi");
                                    adresa = object.getString("adresa");
                                    vendi = object.getString("vendi");
                                    komuna = object.getString("komuna");
                                    numri_leternjoftimit = object.getString("numri_leternjoftimit");
                                    data_lindjes = object.getString("data_lindjes");
                                    data_regjistrimit = object.getString("data_regjistrimit");

                                    Toast.makeText(LoginActivity.this, "Mirsevini " + emri , Toast.LENGTH_SHORT).show();

                                    votuesi = new Votuesi(id, emri, mbiemri, emaili, fjalkalimiS, adresa, vendi, komuna, numri_leternjoftimit, data_lindjes, data_regjistrimit);
                                    votuesiArrayList.add(votuesi);
                                }


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Emaili ose fjalkalimi gabim!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            //Toast.makeText(LoginActivity.this , response, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_EMAIL, email);
                params.put(Config.KEY_PASSWORD, fjalkalimi);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
