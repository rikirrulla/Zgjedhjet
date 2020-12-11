package com.example.zgjedhjet;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
import java.util.List;
import java.util.Map;

import static com.example.zgjedhjet.LoginActivity.votuesiArrayList;

public class PollingFragment extends Fragment {

    Spinner spinnerPartia;
    List<String> listPartia;

    Spinner spinnerKandidati1;
    Spinner spinnerKandidati2;
    Spinner spinnerKandidati3;
    Spinner spinnerKandidati4;
    Spinner spinnerKandidati5;
    List<String> listKandidati;
    FrameLayout btnVoto, btnAnulo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_polling, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerPartia = view.findViewById(R.id.idPartiaPolling);
        spinnerKandidati1 = view.findViewById(R.id.idKandidati1);
        spinnerKandidati2 = view.findViewById(R.id.idKandidati2);
        spinnerKandidati3 = view.findViewById(R.id.idKandidati3);
        spinnerKandidati4 = view.findViewById(R.id.idKandidati4);
        spinnerKandidati5 = view.findViewById(R.id.idKandidati5);
        btnAnulo = view.findViewById(R.id.idAnuloButton);
        btnVoto = view.findViewById(R.id.idVotoButton);

        partia();
        kandidati();
        butoniVoto();
        butoniAnulo();

    }

    private void butoniAnulo() {
        btnAnulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerPartia.setSelection(0);
                spinnerKandidati1.setSelection(0);
                spinnerKandidati2.setSelection(0);
                spinnerKandidati3.setSelection(0);
                spinnerKandidati4.setSelection(0);
                spinnerKandidati5.setSelection(0);
            }
        });
    }

    private void butoniVoto() {


        btnVoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String partia = spinnerPartia.getSelectedItem().toString();
                final String kandidati1 = spinnerKandidati1.getSelectedItem().toString();
                final String kandidati2 = spinnerKandidati2.getSelectedItem().toString();
                final String kandidati3 = spinnerKandidati3.getSelectedItem().toString();
                final String kandidati4 = spinnerKandidati4.getSelectedItem().toString();
                final String kandidati5 = spinnerKandidati5.getSelectedItem().toString();

              /*  if (partia.equals("Partia x")) {

                    Toast.makeText(getActivity(), "Zgjedh nje subjekt politik!", Toast.LENGTH_SHORT).show();

                } else if (kandidati1.equals("Kandidati x") || kandidati1.equals(kandidati2) || kandidati1.equals(kandidati3) || kandidati1.equals(kandidati4) || kandidati1.equals(kandidati5)) {

                    Toast.makeText(getActivity(), "Nuk keni zgjedhur kandidatin ose keni zgjedhur dy kandidat te njejt!", Toast.LENGTH_LONG).show();


                } else if (kandidati2.equals("Kandidati x") || kandidati2.equals(kandidati3) || kandidati2.equals(kandidati4) || kandidati2.equals(kandidati5)) {

                    Toast.makeText(getActivity(), "Nuk keni zgjedhur kandidatin ose keni zgjedhur dy kandidat te njejt!", Toast.LENGTH_LONG).show();

                } else if (kandidati3.equals("Kandidati x") || kandidati3.equals(kandidati4) || kandidati3.equals(kandidati5)) {

                    Toast.makeText(getActivity(), "Nuk keni zgjedhur kandidatin ose keni zgjedhur dy kandidat te njejt!", Toast.LENGTH_LONG).show();

                } else if (kandidati4.equals("Kandidati x") || kandidati4.equals(kandidati5)) {

                    Toast.makeText(getActivity(), "Nuk keni zgjedhur kandidatin ose keni zgjedhur dy kandidat te njejt!", Toast.LENGTH_LONG).show();

                } else if (kandidati5.equals("Kandidati x")) {

                    Toast.makeText(getActivity(), "Nuk keni zgjedhur kandidatin ose keni zgjedhur dy kandidat te njejt!", Toast.LENGTH_LONG).show();

                } else {*/

                    dergoTeDhenat(partia, kandidati1, kandidati2, kandidati3, kandidati4, kandidati5);
              //  }


            }
        });

    }

    private void dergoTeDhenat(final String partia, final String kandidati1, final String kandidati2, final String kandidati3, final String kandidati4, final String kandidati5) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        final StringRequest request = new StringRequest(Request.Method.POST, Connection.url + "voto.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("Data Inserted")) {
                    Toast.makeText(getActivity(), "U insertuan me sukses", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    //btnAnulo();
                } else {
                    Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                System.out.println(error.getLocalizedMessage());
                System.out.println(error.getMessage());
                System.out.println(error.getCause());

                progressDialog.dismiss();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> stringMap = new HashMap<String, String>();
                stringMap.put("partia", partia);
                stringMap.put("kandidati1", kandidati1);
                stringMap.put("kandidati2", kandidati2);
                stringMap.put("kandidati3", kandidati3);
                stringMap.put("kandidati4", kandidati4);
                stringMap.put("kandidati5", kandidati5);
                stringMap.put("data_votes", "2020-01-01");
                stringMap.put("statusi", "Po");
                stringMap.put("numri_zgjedhjeve","3124124");
                stringMap.put("vendi", votuesiArrayList.get(0).getVendi());
                stringMap.put("komuna", votuesiArrayList.get(0).getKomuna());
                return stringMap;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }


    private void kandidati() {
        listKandidati = new ArrayList<String>();
        retrieveDataKandidati();

        listPartia = new ArrayList<>();
        retrieveData();

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.kandidati_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      /*  //te dhenat prej databaze--------
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listKandidati);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/

        spinnerKandidati1.setAdapter(adapter1);
        spinnerKandidati2.setAdapter(adapter1);
        spinnerKandidati3.setAdapter(adapter1);
        spinnerKandidati4.setAdapter(adapter1);
        spinnerKandidati5.setAdapter(adapter1);

    }

    private void retrieveDataKandidati() {
        StringRequest request = new StringRequest(Request.Method.POST, Connection.url + "retrieveKandidati.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("kandidati");

                    if (succes.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = String.valueOf(object.getInt("id"));
                            String emri = object.getString("emri_kandidatit");
                            String logo = object.getString("mosha");
                            String data = object.getString("data_regjistrimit");

                            listKandidati.add(emri);
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


    private void partia() {
        listPartia = new ArrayList<>();
        retrieveData();

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.partia_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

     /*   //te dhenat prej databaze--------
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listPartia);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/
        spinnerPartia.setAdapter(adapter1);


        spinnerPartia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST, Connection.url + "retrievePartia.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("partia");

                    if (succes.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = String.valueOf(object.getInt("id"));
                            String emri = object.getString("emri_partis");
                            String logo = object.getString("logo_partis");
                            String data = object.getString("data_regjistrimit");

                            listPartia.add(emri);
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
