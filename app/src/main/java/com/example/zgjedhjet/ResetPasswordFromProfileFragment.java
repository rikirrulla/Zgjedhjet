package com.example.zgjedhjet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.zgjedhjet.LoginActivity.fjalkalimiS;
import static com.example.zgjedhjet.LoginActivity.id;

public class ResetPasswordFromProfileFragment extends Fragment {

    EditText ETFjalkalimiAkual,ETFjalkalimiRi,ETKonfirmoFjalkaliminERi;
    Button btnNdrysho;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.reset_password_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ETFjalkalimiAkual = view.findViewById(R.id.idFjalkalimiAktualReset);
        ETFjalkalimiRi = view.findViewById(R.id.idFjalkalimiRiReset);
        ETKonfirmoFjalkaliminERi = view.findViewById(R.id.idKonfirmoFjalkaliminERiReset);
        btnNdrysho = view.findViewById(R.id.idNdryshoFjalkaliminReset);

        btnNdrysho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ETFjalkalimiAkual.getText().toString().equals(fjalkalimiS)){
                    Toast.makeText(getContext(), "Fjalkalimi aktual eshte gabim!", Toast.LENGTH_SHORT).show();
                }else if(!ETFjalkalimiRi.getText().toString().equals(ETKonfirmoFjalkaliminERi.getText().toString())){
                    Toast.makeText(getContext(), "Fjalkalimet nuk perputhen!", Toast.LENGTH_SHORT).show();
                }else{
                    resetPassword();
                    Toast.makeText(getContext(), "Fjalkalimi u ndrysha me sukses", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void resetPassword() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Ju lutem prisni....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, Connection.url+"ndyrshofjalkalimin.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "Fjalkalimi u ndryshua me sukses!", Toast.LENGTH_SHORT).show();
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
                stringMap.put("fjalkalimi", ETFjalkalimiRi.getText().toString());

                return stringMap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

}
