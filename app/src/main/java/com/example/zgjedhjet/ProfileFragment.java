package com.example.zgjedhjet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.zgjedhjet.LoginActivity.adresa;
import static com.example.zgjedhjet.LoginActivity.data_lindjes;
import static com.example.zgjedhjet.LoginActivity.data_regjistrimit;
import static com.example.zgjedhjet.LoginActivity.emaili;
import static com.example.zgjedhjet.LoginActivity.emri;
import static com.example.zgjedhjet.LoginActivity.fjalkalimiS;
import static com.example.zgjedhjet.LoginActivity.id;
import static com.example.zgjedhjet.LoginActivity.komuna;
import static com.example.zgjedhjet.LoginActivity.mbiemri;
import static com.example.zgjedhjet.LoginActivity.numri_leternjoftimit;
import static com.example.zgjedhjet.LoginActivity.vendi;
import static com.example.zgjedhjet.LoginActivity.votuesiArrayList;

public class ProfileFragment extends Fragment {

    TextView tvEmriMbiemri, tvEmaili, tvFjalkalimi, tvAdresaVendi, tvKomuna, tvNrLeternjoftimit, tvDataLindjes, tvDataRegjistrimit;
    Votuesi votuesi;
    FrameLayout btnNdryshoTeDhenat;
    String url = Connection.url + "retrieve.php";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // retrieveData();
        tvEmriMbiemri = view.findViewById(R.id.idEmriMbiemriProfile);
        tvEmaili = view.findViewById(R.id.idEmailProfile);
//        tvFjalkalimi = view.findViewById(R.id.fjalkalimi);
        tvAdresaVendi = view.findViewById(R.id.idVendiAdresaProfile);
        tvKomuna = view.findViewById(R.id.idKomunaProfile);
        tvNrLeternjoftimit = view.findViewById(R.id.idNumriLeternjoftimitProfile);
        tvDataLindjes = view.findViewById(R.id.idDataLindjesProfile);
        tvDataRegjistrimit = view.findViewById(R.id.idDataRegjistrimitProfile);
        btnNdryshoTeDhenat = view.findViewById(R.id.idNdyrshotTeDhenat);

        refreshData();
        populateTV();

        btnNdryshoTeDhenat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeDataFragment fragment = new ChangeDataFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewDivider, fragment).commit();

            }
        });
    }

    public void populateTV() {

        tvEmriMbiemri.setText(emri + " " + mbiemri);
        tvEmaili.setText(emaili);
        tvAdresaVendi.setText(adresa + " " + vendi);
        tvKomuna.setText(komuna);
        tvNrLeternjoftimit.setText(numri_leternjoftimit);
        tvDataLindjes.setText(data_lindjes);
        tvDataRegjistrimit.setText(data_regjistrimit);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void refreshData() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                votuesiArrayList.clear();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("votuesi");

                    if (succes.equals("1")) {

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


                            votuesi = new Votuesi(id, emri, mbiemri, emaili, fjalkalimiS, adresa, vendi, komuna, numri_leternjoftimit, data_lindjes, data_regjistrimit);
                            votuesiArrayList.add(votuesi);
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<String, String>();
                stringMap.put("id", id);

                return stringMap;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}
