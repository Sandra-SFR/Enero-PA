package com.example.practicasenerodam.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.practicasenerodam.R;
import com.example.practicasenerodam.TareaDetallesActivity;
import com.example.practicasenerodam.db.AppDatabase;
import com.example.practicasenerodam.domain.Tarea;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaHolder> {

    private Context context;
    private List<Tarea> taskList;

    public TareaAdapter(Context context, List<Tarea> dataList){
        this.context = context;
        this.taskList = dataList;
    }

    @Override
    public TareaHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tarea_item, parent, false);
        return new TareaHolder(view);
    }
    @Override
    public void onBindViewHolder(TareaHolder holder, int position) {
        holder.tareaName.setText(taskList.get(position).getName());
        holder.tareaDescription.setText(taskList.get(position).getDescription());
        holder.tareaDone.setChecked(taskList.get(position).isDone());
    }

    @Override
    public int getItemCount() {return taskList.size();}

    public class TareaHolder extends RecyclerView.ViewHolder {
        public TextView tareaName;
        public TextView tareaDescription;
        public CheckBox tareaDone;
        public Button doTareaButton;
        public Button verDetailsButton;
        public Button deleteTareaButton;
        public View parentView;

        public TareaHolder(View view) {
            super(view);
            parentView = view;

            tareaName = view.findViewById(R.id.tarea_name);
            tareaDescription = view.findViewById(R.id.tarea_description);
            tareaDone = view.findViewById(R.id.check_tarea_done);
            doTareaButton = view.findViewById(R.id.buttonDo);
            verDetailsButton = view.findViewById(R.id.buttonDetails);
            deleteTareaButton = view.findViewById(R.id.buttonDelete);

            // Marcar tarea como hecha
            doTareaButton.setOnClickListener(v -> doTarea(getAdapterPosition()));
            // Ver detalles de la tarea
            verDetailsButton.setOnClickListener(v -> seeDetails(getAdapterPosition()));
            // Eliminar tarea
            deleteTareaButton.setOnClickListener(v -> deleteTarea(getAdapterPosition()));
        }
    }
    private void doTarea(int position) {
        Tarea tarea = taskList.get(position);
        tarea.setDone(true);

        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "tareas")
                .allowMainThreadQueries().build();
        db.tareaDao().update(tarea);

        notifyItemChanged(position);
    }
    private void seeDetails(int position) {
        Tarea tarea = taskList.get(position);

        Intent intent = new Intent(context, TareaDetallesActivity.class);
        intent.putExtra("name", tarea.getName());
        context.startActivity(intent);
    }

    private void deleteTarea(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_msg)
                .setTitle(R.string.remove_tarea_msg)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "tareas")
                            .allowMainThreadQueries().build();
                    Tarea tarea = taskList.get(position);
                    db.tareaDao().delete(tarea);

                    taskList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
