package com.example.listapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<TODOTask> tasks;
    private ListView listView;
    private ArrayAdapter<TODOTask> listAdapter;
    private Button buttonAdd;
    private EditText inputTask;

    private RESTService restService;
    private  FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        String email = "mamma@mia.com";
        String password = "password";
        //signUp(email, password);
        login(email, password);

        buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = inputTask.getText().toString();
                TODOTask task = new TODOTask(description);
                listAdapter.add(task);
                inputTask.setText("");
                listView.setSelection(listAdapter.getCount() - 1);

                addTask(task);
            }
        });

        inputTask = findViewById(R.id.input_task);
        listView = findViewById(R.id.tasklist);

        /*
        TODOTask task = new TODOTask("Hello");
        TODOTask task2 = new TODOTask("World");
        task2.setCompleted(true);

        tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);
        */

        //listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        tasks = new ArrayList<>();
        listAdapter = new CustomArrayAdapter(this, tasks);
        listView.setAdapter(listAdapter);

        setupServer();
        loadTasks();
    }

    private void setupServer() {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://jsonplaceholder.typicode.com")
                .baseUrl("http://22f8115d.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        restService = retrofit.create(RESTService.class);
    }

    public void loadTasks() {
        Call<List<TODOTask>> call = restService.getTasks();
        call.enqueue(new Callback<List<TODOTask>>() {
            @Override
            public void onResponse(Call<List<TODOTask>> call, Response<List<TODOTask>> response) {
                List<TODOTask> tasks = response.body();
                /*for (int i = 0; i < tasks.size(); i++) {
                    TODOTask task = tasks.get(i);
                    listAdapter.add(task);
                }*/
                if (tasks != null && tasks.size() > 0) {
                    for (TODOTask task : tasks) {
                        listAdapter.add(task);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<TODOTask>> call, Throwable t) {
                Log.d("ListApp", t.getMessage());
            }
        });
    }

    public void addTask(TODOTask task) {
        Call<TODOTask> call = restService.addTask(task);
        call.enqueue(new Callback<TODOTask>() {
            @Override
            public void onResponse(Call<TODOTask> call, Response<TODOTask> response) {
                Toast.makeText(getApplicationContext(), "Task added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TODOTask> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Task adding failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateTask(int id) {
        Call<TODOTask> call = restService.updateTask(id);
        call.enqueue(new Callback<TODOTask>() {
            @Override
            public void onResponse(Call<TODOTask> call, Response<TODOTask> response) {
                Toast.makeText(getApplicationContext(), "Task updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TODOTask> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Task update failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteTask(int id) {
        Call<TODOTask> call = restService.deleteTask(id);
        call.enqueue(new Callback<TODOTask>() {
            @Override
            public void onResponse(Call<TODOTask> call, Response<TODOTask> response) {
                Toast.makeText(getApplicationContext(), "Task deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TODOTask> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Task deletion failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signUp(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Account creation failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public  void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                        loadAllTasks();
                    }
                });
    }

    public void loadAllTasks() {
        database.child("tasts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TODOTask task = dataSnapshot.getValue(TODOTask.class);
                listAdapter.add(task);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void createTask(TODOTask task) {
        String key = database.child("tasks").push().getKey();
        database.child("tasks").child(key).setValue(task);
    }

    public void updateTask(TODOTask task) {
        database.child("tasks").child(task.getKey()).setValue(task);
    }

    public void deleteTask(TODOTask task) {
        database.child("tasks").child(task.getKey()).setValue(null);
    }
}
