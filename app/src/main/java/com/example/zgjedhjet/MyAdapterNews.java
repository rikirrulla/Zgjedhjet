package com.example.zgjedhjet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class MyAdapterNews extends ArrayAdapter<Lajmet> {
    Context context;
    List<Lajmet> lajmetList;


    public MyAdapterNews(@NonNull Context context, List<Lajmet> lajmets) {
        super(context, R.layout.notifitacion_row, lajmets);

        this.context = context;
        this.lajmetList = lajmets;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifitacion_row, null, true);
        TextView tvTrainerName = view.findViewById(R.id.newsNameAdapter);
        TextView tvTrainerTime = view.findViewById(R.id.timeNewsAdapter);

        tvTrainerName.setText(lajmetList.get(position).getTitulliLajmit());
        tvTrainerTime.setText(lajmetList.get(position).getPershkrimiLajmit());

        Lajmet lajmet = getItem(position);

        if (lajmet != null) {
            tvTrainerName.setText(lajmet.getTitulliLajmit());
            tvTrainerTime.setText(lajmet.getPershkrimiLajmit());
        }



        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    private Filter nameFilter = new Filter() {
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Lajmet> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(lajmetList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Lajmet item : lajmetList) {
                    if (item.getTitulliLajmit().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if(results != null && results.count > 0) {
                clear();
                addAll((List) results.values);
                notifyDataSetChanged();
            }else {
                notifyDataSetInvalidated();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Lajmet) resultValue).getTitulliLajmit();
        }


    };
}
