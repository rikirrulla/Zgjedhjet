package com.example.zgjedhjet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class RegisterActivity extends Activity {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button tvVazhdoni;
    ImageView tvKthehu;
    EditText tvEmri,tvMbiemri,tvEmaili,tvFjalkalimi,tvAdresa,tvVendi;
    Spinner spinnerKomuna;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        tvVazhdoni = findViewById(R.id.idVazhdoniRegjistrohu);
        tvEmri = findViewById(R.id.idEmriRegjistrohu);
        tvMbiemri = findViewById(R.id.idMbiemriRegjistrohu);
        tvEmaili = findViewById(R.id.idEmailRegjistrohu);
        tvFjalkalimi = findViewById(R.id.idFjalkalimiRegjistrohu);
        tvAdresa = findViewById(R.id.idAdresaRegjistrohu);
        tvVendi = findViewById(R.id.idAVendiRegjistrohu);
        spinnerKomuna = findViewById(R.id.idKomunaRegjistrohu);
        tvKthehu = findViewById(R.id.idKthehuRegjistrohu);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.komuna_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKomuna.setAdapter(adapter);
        spinnerKomuna.setPrompt("Zgjedh komunen...");
        spinnerKomuna.setSelection(18);

        tvVazhdoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validoniFushat()) {
                    String emri = tvEmri.getText().toString().trim();
                    String mbiemri = tvMbiemri.getText().toString().trim();
                    String emaili = tvEmaili.getText().toString().trim();
                    String fjalkalimi = tvFjalkalimi.getText().toString().trim();
                    String adresa = tvAdresa.getText().toString().trim();
                    String vendi = tvVendi.getText().toString().trim();
                    String komuna = spinnerKomuna.getSelectedItem().toString().trim();

                    Intent i = new Intent(RegisterActivity.this, RegisterSkipActivity.class);
                    i.putExtra("emri",emri);
                    i.putExtra("mbiemri",mbiemri);
                    i.putExtra("emaili",emaili);
                    i.putExtra("fjalkalimi",fjalkalimi);
                    i.putExtra("adresa",adresa);
                    i.putExtra("vendi",vendi);
                    i.putExtra("komuna",komuna);
                    startActivity(i);
                }
            }
        });

        tvKthehu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        


    }

    private boolean validoniFushat() {
        String emri = tvEmri.getText().toString().trim();
        String mbiemri = tvMbiemri.getText().toString().trim();
        String emaili = tvEmaili.getText().toString().trim();
        String fjalkalimi = tvFjalkalimi.getText().toString().trim();
        String adresa = tvAdresa.getText().toString().trim();
        String vendi = tvVendi.getText().toString().trim();
        String komuna = spinnerKomuna.getSelectedItem().toString().trim();

        if(emri.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni emrin", Toast.LENGTH_SHORT).show();
        }else if(mbiemri.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni mbiemrin", Toast.LENGTH_SHORT).show();
        }else if(emaili.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni emailin", Toast.LENGTH_SHORT).show();
        }else if(fjalkalimi.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni fjalkalimin", Toast.LENGTH_SHORT).show();
        }else if(adresa.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni adresen", Toast.LENGTH_SHORT).show();
        }else if(vendi.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni vendin", Toast.LENGTH_SHORT).show();
        }else if(komuna.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni komunen", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }
        return false;
    }
}
