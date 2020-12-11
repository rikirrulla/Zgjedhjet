package com.example.zgjedhjet;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsNotifitacionFragment extends Fragment {

    TextView tvTitulli,tvPershkrimi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifitacion_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        String myValue = bundle.getString("message");

        tvTitulli = view.findViewById(R.id.idTitulliNotifitacionNews);
        tvPershkrimi = view.findViewById(R.id.idPershkrimiNotifitacionNews);

        String titulli = getArguments().getString("titulli");
        String pershkrimi = getArguments().getString("pershkrimi");
        tvTitulli.setText(titulli);
        tvPershkrimi.setText(pershkrimi);

    }
}
