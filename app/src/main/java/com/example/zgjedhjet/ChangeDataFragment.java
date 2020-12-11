package com.example.zgjedhjet;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChangeDataFragment extends Fragment {

    Button btnNdryshoFjalkalimin, btnNdryshoDatenELindjes, btnNdryshoTeDhenatPersonale;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.change_data_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNdryshoTeDhenatPersonale = view.findViewById(R.id.idNdyrshoTeDhenatPersonaleMenu);
        btnNdryshoFjalkalimin = view.findViewById(R.id.idNdryshoFjalkaliminMenu);
        btnNdryshoDatenELindjes = view.findViewById(R.id.idNdryshoDatenELindjesMenu);

        btnNdryshoTeDhenatPersonale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditFragment fragment = new EditFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewDivider,fragment).commit();
            }
        });

        btnNdryshoFjalkalimin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPasswordFromProfileFragment fragment = new ResetPasswordFromProfileFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewDivider,fragment).commit();
            }
        });

        btnNdryshoDatenELindjes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


}
