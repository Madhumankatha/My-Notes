package com.madhumankatha.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    private EditText edMsg;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edMsg = findViewById(R.id.edNote);
        btnAdd = findViewById(R.id.btnAdd);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();

        btnAdd.setOnClickListener(view -> {
            String msg = edMsg.getText().toString();

            Notes notes = new Notes(0,msg);
            AppDatabase.getInstance(getApplicationContext()).notesDao().insertNote(notes);

            loadData();
        });
    }

    private void loadData() {
        ArrayList<Notes> notesArrayList = (ArrayList<Notes>) AppDatabase.getInstance(getApplicationContext())
                .notesDao().getAllNotes();

        //AppDatabase.getInstance(getApplicationContext()).notesDao().deleteNote(notesArrayList.get(2));

        myAdapter = new MyAdapter(notesArrayList);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }
}