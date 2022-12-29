package com.example.kalkulator_binnus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<History>listHistory;
    private Context context;
    private SharedPreferences sharedPreferences;

    public HistoryAdapter(ArrayList<History> listHistory, Context context, SharedPreferences sharedPreferences) {
        this.listHistory = listHistory;
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        ViewHolder holder= new ViewHolder(inflater.inflate(R.layout.history_layout, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        History history = listHistory.get(position);
        holder.history.setText(history.getHistory());
    }

    @Override
    public int getItemCount() { return listHistory.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView history;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            history = itemView.findViewById(R.id.txtHstry);

            itemView.setOnLongClickListener(view -> {

                int p = getLayoutPosition();
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Hapus History?")
                        .setMessage("Ingin Hapus History?")
                        .setPositiveButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel())
                        .setNegativeButton("Yes", (dialogInterface, i) -> {
                            String id = listHistory.get(p).getId();

                            sharedPreferences.edit().remove(id).commit();

                            for (int j=0;j<listHistory.size();j++){
                                if (id.equalsIgnoreCase(listHistory.get(j).getId())){
                                    listHistory.remove(j);
                                    notifyItemRemoved(j);
                                    notifyItemChanged(j);
                                    notifyItemRangeChanged(j, listHistory.size());

                                }
                            }
                        });
                AlertDialog dialog = alert.create();
                dialog.show();

                return true;
            });
        }

    }
}
