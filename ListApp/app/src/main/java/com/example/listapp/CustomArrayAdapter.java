package com.example.listapp;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<TODOTask> {

    private MainActivity mainActivity;

    public CustomArrayAdapter(MainActivity mainActivity, List<TODOTask> tasks) {
        super(mainActivity.getApplicationContext(), 0, tasks);
        this.mainActivity = mainActivity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TaskViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);

            viewHolder = new TaskViewHolder();
            viewHolder.id = convertView.findViewById(R.id.text_id);
            viewHolder.description = convertView.findViewById(R.id.text_description);
            viewHolder.date = convertView.findViewById(R.id.text_date);
            viewHolder.completed = convertView.findViewById(R.id.text_completed);
            viewHolder.delete = convertView.findViewById(R.id.text_delete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (TaskViewHolder) convertView.getTag();
        }

        Log.d("TEST", "" + position);
        final TODOTask task = getItem(position);
        if (task != null) {
            //viewHolder.id.setText("" + task.getId());
            viewHolder.id.setText(task.getKey());
            viewHolder.description.setText(task.getDescription());
            viewHolder.date.setText(DateFormat.format("dd-MM-yyyy", task.getCreatedDate()).toString());
            viewHolder.completed.setChecked(task.getCompleted());

            final TextView textId = convertView.findViewById(R.id.text_id);
            //textId.setText("" + task.getId());
            textId.setText(task.getKey());

            TextView textDescription = convertView.findViewById(R.id.text_description);
            textDescription.setText(task.getDescription());

            TextView textDate = convertView.findViewById(R.id.text_date);
            textDate.setText(DateFormat.format("dd-MM-yyyy", task.getCreatedDate()).toString());

            CheckBox completed = convertView.findViewById(R.id.text_completed);
            completed.setChecked(task.getCompleted());
            viewHolder.completed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.updateTask(textId.getId());
                }
            });

            viewHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(),"TODOTask deleted: " + position, Toast.LENGTH_SHORT).show();
                    remove(getItem(position));
                    mainActivity.deleteTask(textId.getId());
                }
            });
        }

        return convertView;
    }

    public class TaskViewHolder {
        public TextView id;
        public TextView date;
        public TextView description;
        public CheckBox completed;
        public Button delete;
    }
}
