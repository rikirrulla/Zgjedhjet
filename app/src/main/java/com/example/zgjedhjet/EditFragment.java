package com.example.zgjedhjet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.zgjedhjet.LoginActivity.adresa;
import static com.example.zgjedhjet.LoginActivity.emaili;
import static com.example.zgjedhjet.LoginActivity.emri;
import static com.example.zgjedhjet.LoginActivity.id;
import static com.example.zgjedhjet.LoginActivity.komuna;
import static com.example.zgjedhjet.LoginActivity.mbiemri;
import static com.example.zgjedhjet.LoginActivity.numri_leternjoftimit;
import static com.example.zgjedhjet.LoginActivity.vendi;

public class EditFragment extends Fragment {
    EditText tvEmri, tvMbiemri, tvEmaili, tvNumriLetenjoftimit, tvAdresa, tvVendi;
    Spinner spinnerKomuna;
    Button btnEdito;
    TextView tvNdryshoFjalkalimin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();


        btnEdito = view.findViewById(R.id.idEditoEdit);
        tvEmri = view.findViewById(R.id.idEmriRegjistrohuEdit);
        tvMbiemri = view.findViewById(R.id.idMbiemriRegjistrohuEdit);
        tvEmaili = view.findViewById(R.id.idEmailRegjistrohuEdit);
        tvNumriLetenjoftimit = view.findViewById(R.id.idNumriLeternjoftimitEdit);
        tvAdresa = view.findViewById(R.id.idAdresaRegjistrohuEdit);
        tvVendi = view.findViewById(R.id.idAVendiRegjistrohuEdit);
        spinnerKomuna = view.findViewById(R.id.idKomunaRegjistrohuEdit);
        tvNdryshoFjalkalimin = view.findViewById(R.id.idNdryshoFjalkalimin);



        tvEmri.setText(emri);
        tvMbiemri.setText(mbiemri);
        tvEmaili.setText(emaili);
        tvNumriLetenjoftimit.setText(numri_leternjoftimit);
        tvAdresa.setText(adresa);
        tvVendi.setText(vendi);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.komuna_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKomuna.setAdapter(adapter);
        spinnerKomuna.setSelection(adapter.getPosition(komuna));

        btnEdito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emri = tvEmri.getText().toString().trim();
                final String mbiemri = tvMbiemri.getText().toString().trim();
                final String emaili = tvEmaili.getText().toString().trim();
                final String vendi = tvVendi.getText().toString().trim();
                final String adresa = tvAdresa.getText().toString().trim();
                final String komuna = spinnerKomuna.getSelectedItem().toString();


                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Ju lutem prisni....");
                progressDialog.show();

                StringRequest request = new StringRequest(Request.Method.POST, Connection.url + "updateVotuesi.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        ProfileFragment fragment = new ProfileFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewDivider,fragment).commit();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> stringMap = new HashMap<String, String>();
                        stringMap.put("id", id);
                        stringMap.put("emri", emri);
                        stringMap.put("mbiemri", mbiemri);
                        stringMap.put("emaili", emaili);
                        stringMap.put("vendi", vendi);
                        stringMap.put("adresa", adresa);
                        stringMap.put("komuna", komuna);

                        return stringMap;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(request);
            }
        });

    }
}

