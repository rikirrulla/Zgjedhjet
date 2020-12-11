package com.example.zgjedhjet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class NotifitacionFragment extends Fragment {

    ListView listView;

    private String url = Connection.url+"retrieveNews.php";
    public static ArrayList<Lajmet> lajmetArrayList = new ArrayList<>();
    Lajmet lajmet;
    MyAdapterNews adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notifitacion, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.idListViewNews);

        retrieveData();
        adapter = new MyAdapterNews(getActivity(), lajmetArrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsNotifitacionFragment fragment = new NewsNotifitacionFragment();
                Bundle bundle = new Bundle();
                bundle.putString("titulli", lajmetArrayList.get(position).getTitulliLajmit());
                bundle.putString("pershkrimi", lajmetArrayList.get(position).getPershkrimiLajmit());
                fragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewDivider, fragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void retrieveData () {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                lajmetArrayList.clear();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("lajmet");

                    if (succes.equals("1")) {

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = String.valueOf(object.getInt("id"));
                            String name = object.getString("titulli_lajmit");
                            String time = object.getString("pershkrimi_lajmit");
                            String mobile = object.getString("data_lajmit");

                            lajmet = new Lajmet(id, name, time, mobile);
                            lajmetArrayList.add(lajmet);
                            adapter.notifyDataSetChanged();
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

}