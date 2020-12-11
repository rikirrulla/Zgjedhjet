package com.example.zgjedhjet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterSkipActivity extends Activity {
    DatePickerDialog picker;
    EditText tvNumriLeterNjoftimit,tvDataLindjes,tvidDataLindjesRegjistrohu;
    ImageView ivFoto;
    Button btnKonfirmo,btnNgarko;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_confirmation);
        tvidDataLindjesRegjistrohu = findViewById(R.id.idDataLindjesRegjistrohu);
        tvNumriLeterNjoftimit = findViewById(R.id.idNrLeternjoftimitRegjistrohu);
        ivFoto = findViewById(R.id.idFotoRegjistrohu);
        btnKonfirmo = findViewById(R.id.idKonfirmoRegjistrohy);
        btnNgarko = findViewById(R.id.idNgarkoniRegjistrohu);

        tvidDataLindjesRegjistrohu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(RegisterSkipActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tvidDataLindjesRegjistrohu.setText(year + "-" + (monthOfYear + 1) + "- " + dayOfMonth);
                            }
                        }, year, month, day);
                picker.getDatePicker().setSpinnersShown(true);
                picker.getDatePicker().setCalendarViewShown(false);
                picker.show();
            }
        });

        btnNgarko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zgjedhFoton();
            }
        });

        btnKonfirmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shtoTeDhenat();
            }
        });

    }

    private void zgjedhFoton() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        ivFoto.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage =  data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                ivFoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

    private void shtoTeDhenat() {

        final String emri = getIntent().getStringExtra("emri");
        final String mbiemri = getIntent().getStringExtra("mbiemri");
        final String emaili = getIntent().getStringExtra("emaili");
        final String fjalkalimi = getIntent().getStringExtra("fjalkalimi");
        final String adresa = getIntent().getStringExtra("adresa");
        final String vendi = getIntent().getStringExtra("vendi");
        final String komuna = getIntent().getStringExtra("komuna");
        final String data_lindjes = "test";
        final String numri_leternjoftimit = tvNumriLeterNjoftimit.getText().toString().trim();


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String formattedDate = df.format(c);
        Toast.makeText(this, formattedDate, Toast.LENGTH_SHORT).show();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");


        if(data_lindjes.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni daten e lindjes", Toast.LENGTH_SHORT).show();
        }else if(numri_leternjoftimit.isEmpty()){
            Toast.makeText(this, "Ju lutem plotsoni numrin e leternjoftimit", Toast.LENGTH_SHORT).show();
        }else{
            final StringRequest request = new StringRequest(Request.Method.POST, Connection.url+"votuesi.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    if (response.equalsIgnoreCase("Data Inserted")) {
                        Toast.makeText(RegisterSkipActivity.this, "U insertuan me sukses", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent i = new Intent(RegisterSkipActivity.this,LoginActivity.class);
                        startActivity(i);

                    } else {
                        Toast.makeText(RegisterSkipActivity.this, response, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RegisterSkipActivity.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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
                    stringMap.put("emri", emri);
                    stringMap.put("mbiemri", mbiemri);
                    stringMap.put("emaili", emaili);
                    stringMap.put("fjalkalimi", fjalkalimi);
                    stringMap.put("adresa", adresa);
                    stringMap.put("vendi", vendi);
                    stringMap.put("komuna", komuna);
                    stringMap.put("numri_leternjoftimit", numri_leternjoftimit);
                    stringMap.put("data_lindjes", data_lindjes);
                    stringMap.put("data_regjistrimit", formattedDate);
                    return stringMap;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(RegisterSkipActivity.this);
            requestQueue.add(request);
        }

    }



}
